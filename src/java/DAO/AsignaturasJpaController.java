/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Asignaturas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Cursos;
import DTO.Departamentos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RafaMar
 */
public class AsignaturasJpaController implements Serializable {

    public AsignaturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignaturas asignaturas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos curso = asignaturas.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getCodigo());
                asignaturas.setCurso(curso);
            }
            Departamentos departamento = asignaturas.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getCodigo());
                asignaturas.setDepartamento(departamento);
            }
            em.persist(asignaturas);
            if (curso != null) {
                curso.getAsignaturasList().add(asignaturas);
                curso = em.merge(curso);
            }
            if (departamento != null) {
                departamento.getAsignaturasList().add(asignaturas);
                departamento = em.merge(departamento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignaturas asignaturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignaturas persistentAsignaturas = em.find(Asignaturas.class, asignaturas.getCodigo());
            Cursos cursoOld = persistentAsignaturas.getCurso();
            Cursos cursoNew = asignaturas.getCurso();
            Departamentos departamentoOld = persistentAsignaturas.getDepartamento();
            Departamentos departamentoNew = asignaturas.getDepartamento();
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getCodigo());
                asignaturas.setCurso(cursoNew);
            }
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getCodigo());
                asignaturas.setDepartamento(departamentoNew);
            }
            asignaturas = em.merge(asignaturas);
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getAsignaturasList().remove(asignaturas);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getAsignaturasList().add(asignaturas);
                cursoNew = em.merge(cursoNew);
            }
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getAsignaturasList().remove(asignaturas);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getAsignaturasList().add(asignaturas);
                departamentoNew = em.merge(departamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignaturas.getCodigo();
                if (findAsignaturas(id) == null) {
                    throw new NonexistentEntityException("The asignaturas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignaturas asignaturas;
            try {
                asignaturas = em.getReference(Asignaturas.class, id);
                asignaturas.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturas with id " + id + " no longer exists.", enfe);
            }
            Cursos curso = asignaturas.getCurso();
            if (curso != null) {
                curso.getAsignaturasList().remove(asignaturas);
                curso = em.merge(curso);
            }
            Departamentos departamento = asignaturas.getDepartamento();
            if (departamento != null) {
                departamento.getAsignaturasList().remove(asignaturas);
                departamento = em.merge(departamento);
            }
            em.remove(asignaturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignaturas> findAsignaturasEntities() {
        return findAsignaturasEntities(true, -1, -1);
    }

    public List<Asignaturas> findAsignaturasEntities(int maxResults, int firstResult) {
        return findAsignaturasEntities(false, maxResults, firstResult);
    }

    private List<Asignaturas> findAsignaturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignaturas.class));
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

    public Asignaturas findAsignaturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignaturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignaturas> rt = cq.from(Asignaturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
