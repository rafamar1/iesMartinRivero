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
import DTO.AreaDpto;
import DTO.Noticias;
import java.util.ArrayList;
import java.util.List;
import DTO.Asignaturas;
import DTO.Departamentos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RafaMar
 */
public class DepartamentosJpaController implements Serializable {

    public DepartamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamentos departamentos) {
        if (departamentos.getNoticiasList() == null) {
            departamentos.setNoticiasList(new ArrayList<Noticias>());
        }
        if (departamentos.getAsignaturasList() == null) {
            departamentos.setAsignaturasList(new ArrayList<Asignaturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaDpto codigoArea = departamentos.getCodigoArea();
            if (codigoArea != null) {
                codigoArea = em.getReference(codigoArea.getClass(), codigoArea.getCodigo());
                departamentos.setCodigoArea(codigoArea);
            }
            List<Noticias> attachedNoticiasList = new ArrayList<Noticias>();
            for (Noticias noticiasListNoticiasToAttach : departamentos.getNoticiasList()) {
                noticiasListNoticiasToAttach = em.getReference(noticiasListNoticiasToAttach.getClass(), noticiasListNoticiasToAttach.getCodigo());
                attachedNoticiasList.add(noticiasListNoticiasToAttach);
            }
            departamentos.setNoticiasList(attachedNoticiasList);
            List<Asignaturas> attachedAsignaturasList = new ArrayList<Asignaturas>();
            for (Asignaturas asignaturasListAsignaturasToAttach : departamentos.getAsignaturasList()) {
                asignaturasListAsignaturasToAttach = em.getReference(asignaturasListAsignaturasToAttach.getClass(), asignaturasListAsignaturasToAttach.getCodigo());
                attachedAsignaturasList.add(asignaturasListAsignaturasToAttach);
            }
            departamentos.setAsignaturasList(attachedAsignaturasList);
            em.persist(departamentos);
            if (codigoArea != null) {
                codigoArea.getDepartamentosList().add(departamentos);
                codigoArea = em.merge(codigoArea);
            }
            for (Noticias noticiasListNoticias : departamentos.getNoticiasList()) {
                Departamentos oldCodigoDptoOfNoticiasListNoticias = noticiasListNoticias.getCodigoDpto();
                noticiasListNoticias.setCodigoDpto(departamentos);
                noticiasListNoticias = em.merge(noticiasListNoticias);
                if (oldCodigoDptoOfNoticiasListNoticias != null) {
                    oldCodigoDptoOfNoticiasListNoticias.getNoticiasList().remove(noticiasListNoticias);
                    oldCodigoDptoOfNoticiasListNoticias = em.merge(oldCodigoDptoOfNoticiasListNoticias);
                }
            }
            for (Asignaturas asignaturasListAsignaturas : departamentos.getAsignaturasList()) {
                Departamentos oldDepartamentoOfAsignaturasListAsignaturas = asignaturasListAsignaturas.getDepartamento();
                asignaturasListAsignaturas.setDepartamento(departamentos);
                asignaturasListAsignaturas = em.merge(asignaturasListAsignaturas);
                if (oldDepartamentoOfAsignaturasListAsignaturas != null) {
                    oldDepartamentoOfAsignaturasListAsignaturas.getAsignaturasList().remove(asignaturasListAsignaturas);
                    oldDepartamentoOfAsignaturasListAsignaturas = em.merge(oldDepartamentoOfAsignaturasListAsignaturas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamentos departamentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos persistentDepartamentos = em.find(Departamentos.class, departamentos.getCodigo());
            AreaDpto codigoAreaOld = persistentDepartamentos.getCodigoArea();
            AreaDpto codigoAreaNew = departamentos.getCodigoArea();
            List<Noticias> noticiasListOld = persistentDepartamentos.getNoticiasList();
            List<Noticias> noticiasListNew = departamentos.getNoticiasList();
            List<Asignaturas> asignaturasListOld = persistentDepartamentos.getAsignaturasList();
            List<Asignaturas> asignaturasListNew = departamentos.getAsignaturasList();
            List<String> illegalOrphanMessages = null;
            for (Noticias noticiasListOldNoticias : noticiasListOld) {
                if (!noticiasListNew.contains(noticiasListOldNoticias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Noticias " + noticiasListOldNoticias + " since its codigoDpto field is not nullable.");
                }
            }
            for (Asignaturas asignaturasListOldAsignaturas : asignaturasListOld) {
                if (!asignaturasListNew.contains(asignaturasListOldAsignaturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignaturas " + asignaturasListOldAsignaturas + " since its departamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigoAreaNew != null) {
                codigoAreaNew = em.getReference(codigoAreaNew.getClass(), codigoAreaNew.getCodigo());
                departamentos.setCodigoArea(codigoAreaNew);
            }
            List<Noticias> attachedNoticiasListNew = new ArrayList<Noticias>();
            for (Noticias noticiasListNewNoticiasToAttach : noticiasListNew) {
                noticiasListNewNoticiasToAttach = em.getReference(noticiasListNewNoticiasToAttach.getClass(), noticiasListNewNoticiasToAttach.getCodigo());
                attachedNoticiasListNew.add(noticiasListNewNoticiasToAttach);
            }
            noticiasListNew = attachedNoticiasListNew;
            departamentos.setNoticiasList(noticiasListNew);
            List<Asignaturas> attachedAsignaturasListNew = new ArrayList<Asignaturas>();
            for (Asignaturas asignaturasListNewAsignaturasToAttach : asignaturasListNew) {
                asignaturasListNewAsignaturasToAttach = em.getReference(asignaturasListNewAsignaturasToAttach.getClass(), asignaturasListNewAsignaturasToAttach.getCodigo());
                attachedAsignaturasListNew.add(asignaturasListNewAsignaturasToAttach);
            }
            asignaturasListNew = attachedAsignaturasListNew;
            departamentos.setAsignaturasList(asignaturasListNew);
            departamentos = em.merge(departamentos);
            if (codigoAreaOld != null && !codigoAreaOld.equals(codigoAreaNew)) {
                codigoAreaOld.getDepartamentosList().remove(departamentos);
                codigoAreaOld = em.merge(codigoAreaOld);
            }
            if (codigoAreaNew != null && !codigoAreaNew.equals(codigoAreaOld)) {
                codigoAreaNew.getDepartamentosList().add(departamentos);
                codigoAreaNew = em.merge(codigoAreaNew);
            }
            for (Noticias noticiasListNewNoticias : noticiasListNew) {
                if (!noticiasListOld.contains(noticiasListNewNoticias)) {
                    Departamentos oldCodigoDptoOfNoticiasListNewNoticias = noticiasListNewNoticias.getCodigoDpto();
                    noticiasListNewNoticias.setCodigoDpto(departamentos);
                    noticiasListNewNoticias = em.merge(noticiasListNewNoticias);
                    if (oldCodigoDptoOfNoticiasListNewNoticias != null && !oldCodigoDptoOfNoticiasListNewNoticias.equals(departamentos)) {
                        oldCodigoDptoOfNoticiasListNewNoticias.getNoticiasList().remove(noticiasListNewNoticias);
                        oldCodigoDptoOfNoticiasListNewNoticias = em.merge(oldCodigoDptoOfNoticiasListNewNoticias);
                    }
                }
            }
            for (Asignaturas asignaturasListNewAsignaturas : asignaturasListNew) {
                if (!asignaturasListOld.contains(asignaturasListNewAsignaturas)) {
                    Departamentos oldDepartamentoOfAsignaturasListNewAsignaturas = asignaturasListNewAsignaturas.getDepartamento();
                    asignaturasListNewAsignaturas.setDepartamento(departamentos);
                    asignaturasListNewAsignaturas = em.merge(asignaturasListNewAsignaturas);
                    if (oldDepartamentoOfAsignaturasListNewAsignaturas != null && !oldDepartamentoOfAsignaturasListNewAsignaturas.equals(departamentos)) {
                        oldDepartamentoOfAsignaturasListNewAsignaturas.getAsignaturasList().remove(asignaturasListNewAsignaturas);
                        oldDepartamentoOfAsignaturasListNewAsignaturas = em.merge(oldDepartamentoOfAsignaturasListNewAsignaturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentos.getCodigo();
                if (findDepartamentos(id) == null) {
                    throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.");
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
            Departamentos departamentos;
            try {
                departamentos = em.getReference(Departamentos.class, id);
                departamentos.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Noticias> noticiasListOrphanCheck = departamentos.getNoticiasList();
            for (Noticias noticiasListOrphanCheckNoticias : noticiasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamentos (" + departamentos + ") cannot be destroyed since the Noticias " + noticiasListOrphanCheckNoticias + " in its noticiasList field has a non-nullable codigoDpto field.");
            }
            List<Asignaturas> asignaturasListOrphanCheck = departamentos.getAsignaturasList();
            for (Asignaturas asignaturasListOrphanCheckAsignaturas : asignaturasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamentos (" + departamentos + ") cannot be destroyed since the Asignaturas " + asignaturasListOrphanCheckAsignaturas + " in its asignaturasList field has a non-nullable departamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AreaDpto codigoArea = departamentos.getCodigoArea();
            if (codigoArea != null) {
                codigoArea.getDepartamentosList().remove(departamentos);
                codigoArea = em.merge(codigoArea);
            }
            em.remove(departamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamentos> findDepartamentosEntities() {
        return findDepartamentosEntities(true, -1, -1);
    }

    public List<Departamentos> findDepartamentosEntities(int maxResults, int firstResult) {
        return findDepartamentosEntities(false, maxResults, firstResult);
    }

    private List<Departamentos> findDepartamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamentos.class));
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

    public Departamentos findDepartamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamentos> rt = cq.from(Departamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
