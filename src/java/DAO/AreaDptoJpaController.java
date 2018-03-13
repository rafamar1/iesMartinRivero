/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.AreaDpto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Departamentos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RafaMar
 */
public class AreaDptoJpaController implements Serializable {

    public AreaDptoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaDpto areaDpto) {
        if (areaDpto.getDepartamentosList() == null) {
            areaDpto.setDepartamentosList(new ArrayList<Departamentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Departamentos> attachedDepartamentosList = new ArrayList<Departamentos>();
            for (Departamentos departamentosListDepartamentosToAttach : areaDpto.getDepartamentosList()) {
                departamentosListDepartamentosToAttach = em.getReference(departamentosListDepartamentosToAttach.getClass(), departamentosListDepartamentosToAttach.getCodigo());
                attachedDepartamentosList.add(departamentosListDepartamentosToAttach);
            }
            areaDpto.setDepartamentosList(attachedDepartamentosList);
            em.persist(areaDpto);
            for (Departamentos departamentosListDepartamentos : areaDpto.getDepartamentosList()) {
                AreaDpto oldCodigoAreaOfDepartamentosListDepartamentos = departamentosListDepartamentos.getCodigoArea();
                departamentosListDepartamentos.setCodigoArea(areaDpto);
                departamentosListDepartamentos = em.merge(departamentosListDepartamentos);
                if (oldCodigoAreaOfDepartamentosListDepartamentos != null) {
                    oldCodigoAreaOfDepartamentosListDepartamentos.getDepartamentosList().remove(departamentosListDepartamentos);
                    oldCodigoAreaOfDepartamentosListDepartamentos = em.merge(oldCodigoAreaOfDepartamentosListDepartamentos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaDpto areaDpto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaDpto persistentAreaDpto = em.find(AreaDpto.class, areaDpto.getCodigo());
            List<Departamentos> departamentosListOld = persistentAreaDpto.getDepartamentosList();
            List<Departamentos> departamentosListNew = areaDpto.getDepartamentosList();
            List<String> illegalOrphanMessages = null;
            for (Departamentos departamentosListOldDepartamentos : departamentosListOld) {
                if (!departamentosListNew.contains(departamentosListOldDepartamentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Departamentos " + departamentosListOldDepartamentos + " since its codigoArea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Departamentos> attachedDepartamentosListNew = new ArrayList<Departamentos>();
            for (Departamentos departamentosListNewDepartamentosToAttach : departamentosListNew) {
                departamentosListNewDepartamentosToAttach = em.getReference(departamentosListNewDepartamentosToAttach.getClass(), departamentosListNewDepartamentosToAttach.getCodigo());
                attachedDepartamentosListNew.add(departamentosListNewDepartamentosToAttach);
            }
            departamentosListNew = attachedDepartamentosListNew;
            areaDpto.setDepartamentosList(departamentosListNew);
            areaDpto = em.merge(areaDpto);
            for (Departamentos departamentosListNewDepartamentos : departamentosListNew) {
                if (!departamentosListOld.contains(departamentosListNewDepartamentos)) {
                    AreaDpto oldCodigoAreaOfDepartamentosListNewDepartamentos = departamentosListNewDepartamentos.getCodigoArea();
                    departamentosListNewDepartamentos.setCodigoArea(areaDpto);
                    departamentosListNewDepartamentos = em.merge(departamentosListNewDepartamentos);
                    if (oldCodigoAreaOfDepartamentosListNewDepartamentos != null && !oldCodigoAreaOfDepartamentosListNewDepartamentos.equals(areaDpto)) {
                        oldCodigoAreaOfDepartamentosListNewDepartamentos.getDepartamentosList().remove(departamentosListNewDepartamentos);
                        oldCodigoAreaOfDepartamentosListNewDepartamentos = em.merge(oldCodigoAreaOfDepartamentosListNewDepartamentos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaDpto.getCodigo();
                if (findAreaDpto(id) == null) {
                    throw new NonexistentEntityException("The areaDpto with id " + id + " no longer exists.");
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
            AreaDpto areaDpto;
            try {
                areaDpto = em.getReference(AreaDpto.class, id);
                areaDpto.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaDpto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Departamentos> departamentosListOrphanCheck = areaDpto.getDepartamentosList();
            for (Departamentos departamentosListOrphanCheckDepartamentos : departamentosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AreaDpto (" + areaDpto + ") cannot be destroyed since the Departamentos " + departamentosListOrphanCheckDepartamentos + " in its departamentosList field has a non-nullable codigoArea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(areaDpto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaDpto> findAreaDptoEntities() {
        return findAreaDptoEntities(true, -1, -1);
    }

    public List<AreaDpto> findAreaDptoEntities(int maxResults, int firstResult) {
        return findAreaDptoEntities(false, maxResults, firstResult);
    }

    private List<AreaDpto> findAreaDptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaDpto.class));
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

    public AreaDpto findAreaDpto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaDpto.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaDptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaDpto> rt = cq.from(AreaDpto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
