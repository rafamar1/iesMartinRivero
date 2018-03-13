/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Asignaturas;
import DTO.Cursos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RafaMar
 */
public class CursosJpaController implements Serializable {

    public CursosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cursos cursos) {
        if (cursos.getAsignaturasList() == null) {
            cursos.setAsignaturasList(new ArrayList<Asignaturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignaturas> attachedAsignaturasList = new ArrayList<Asignaturas>();
            for (Asignaturas asignaturasListAsignaturasToAttach : cursos.getAsignaturasList()) {
                asignaturasListAsignaturasToAttach = em.getReference(asignaturasListAsignaturasToAttach.getClass(), asignaturasListAsignaturasToAttach.getCodigo());
                attachedAsignaturasList.add(asignaturasListAsignaturasToAttach);
            }
            cursos.setAsignaturasList(attachedAsignaturasList);
            em.persist(cursos);
            for (Asignaturas asignaturasListAsignaturas : cursos.getAsignaturasList()) {
                Cursos oldCursoOfAsignaturasListAsignaturas = asignaturasListAsignaturas.getCurso();
                asignaturasListAsignaturas.setCurso(cursos);
                asignaturasListAsignaturas = em.merge(asignaturasListAsignaturas);
                if (oldCursoOfAsignaturasListAsignaturas != null) {
                    oldCursoOfAsignaturasListAsignaturas.getAsignaturasList().remove(asignaturasListAsignaturas);
                    oldCursoOfAsignaturasListAsignaturas = em.merge(oldCursoOfAsignaturasListAsignaturas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cursos cursos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos persistentCursos = em.find(Cursos.class, cursos.getCodigo());
            List<Asignaturas> asignaturasListOld = persistentCursos.getAsignaturasList();
            List<Asignaturas> asignaturasListNew = cursos.getAsignaturasList();
            List<String> illegalOrphanMessages = null;
            for (Asignaturas asignaturasListOldAsignaturas : asignaturasListOld) {
                if (!asignaturasListNew.contains(asignaturasListOldAsignaturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignaturas " + asignaturasListOldAsignaturas + " since its curso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asignaturas> attachedAsignaturasListNew = new ArrayList<Asignaturas>();
            for (Asignaturas asignaturasListNewAsignaturasToAttach : asignaturasListNew) {
                asignaturasListNewAsignaturasToAttach = em.getReference(asignaturasListNewAsignaturasToAttach.getClass(), asignaturasListNewAsignaturasToAttach.getCodigo());
                attachedAsignaturasListNew.add(asignaturasListNewAsignaturasToAttach);
            }
            asignaturasListNew = attachedAsignaturasListNew;
            cursos.setAsignaturasList(asignaturasListNew);
            cursos = em.merge(cursos);
            for (Asignaturas asignaturasListNewAsignaturas : asignaturasListNew) {
                if (!asignaturasListOld.contains(asignaturasListNewAsignaturas)) {
                    Cursos oldCursoOfAsignaturasListNewAsignaturas = asignaturasListNewAsignaturas.getCurso();
                    asignaturasListNewAsignaturas.setCurso(cursos);
                    asignaturasListNewAsignaturas = em.merge(asignaturasListNewAsignaturas);
                    if (oldCursoOfAsignaturasListNewAsignaturas != null && !oldCursoOfAsignaturasListNewAsignaturas.equals(cursos)) {
                        oldCursoOfAsignaturasListNewAsignaturas.getAsignaturasList().remove(asignaturasListNewAsignaturas);
                        oldCursoOfAsignaturasListNewAsignaturas = em.merge(oldCursoOfAsignaturasListNewAsignaturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursos.getCodigo();
                if (findCursos(id) == null) {
                    throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos cursos;
            try {
                cursos = em.getReference(Cursos.class, id);
                cursos.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignaturas> asignaturasListOrphanCheck = cursos.getAsignaturasList();
            for (Asignaturas asignaturasListOrphanCheckAsignaturas : asignaturasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cursos (" + cursos + ") cannot be destroyed since the Asignaturas " + asignaturasListOrphanCheckAsignaturas + " in its asignaturasList field has a non-nullable curso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cursos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cursos> findCursosEntities() {
        return findCursosEntities(true, -1, -1);
    }

    public List<Cursos> findCursosEntities(int maxResults, int firstResult) {
        return findCursosEntities(false, maxResults, firstResult);
    }

    private List<Cursos> findCursosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cursos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cursos findCursos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cursos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cursos> rt = cq.from(Cursos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
