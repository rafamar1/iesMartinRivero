/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Departamentos;
import DTO.Noticias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RafaMar
 */
public class NoticiasJpaController implements Serializable {

    public NoticiasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Noticias noticias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos codigoDpto = noticias.getCodigoDpto();
            if (codigoDpto != null) {
                codigoDpto = em.getReference(codigoDpto.getClass(), codigoDpto.getCodigo());
                noticias.setCodigoDpto(codigoDpto);
            }
            em.persist(noticias);
            if (codigoDpto != null) {
                codigoDpto.getNoticiasList().add(noticias);
                codigoDpto = em.merge(codigoDpto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Noticias noticias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Noticias persistentNoticias = em.find(Noticias.class, noticias.getCodigo());
            Departamentos codigoDptoOld = persistentNoticias.getCodigoDpto();
            Departamentos codigoDptoNew = noticias.getCodigoDpto();
            if (codigoDptoNew != null) {
                codigoDptoNew = em.getReference(codigoDptoNew.getClass(), codigoDptoNew.getCodigo());
                noticias.setCodigoDpto(codigoDptoNew);
            }
            noticias = em.merge(noticias);
            if (codigoDptoOld != null && !codigoDptoOld.equals(codigoDptoNew)) {
                codigoDptoOld.getNoticiasList().remove(noticias);
                codigoDptoOld = em.merge(codigoDptoOld);
            }
            if (codigoDptoNew != null && !codigoDptoNew.equals(codigoDptoOld)) {
                codigoDptoNew.getNoticiasList().add(noticias);
                codigoDptoNew = em.merge(codigoDptoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = noticias.getCodigo();
                if (findNoticias(id) == null) {
                    throw new NonexistentEntityException("The noticias with id " + id + " no longer exists.");
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
            Noticias noticias;
            try {
                noticias = em.getReference(Noticias.class, id);
                noticias.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The noticias with id " + id + " no longer exists.", enfe);
            }
            Departamentos codigoDpto = noticias.getCodigoDpto();
            if (codigoDpto != null) {
                codigoDpto.getNoticiasList().remove(noticias);
                codigoDpto = em.merge(codigoDpto);
            }
            em.remove(noticias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Noticias> findNoticiasEntities() {
        return findNoticiasEntities(true, -1, -1);
    }

    public List<Noticias> findNoticiasEntities(int maxResults, int firstResult) {
        return findNoticiasEntities(false, maxResults, firstResult);
    }

    private List<Noticias> findNoticiasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Noticias.class));
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

    public Noticias findNoticias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Noticias.class, id);
        } finally {
            em.close();
        }
    }

    public int getNoticiasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Noticias> rt = cq.from(Noticias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
