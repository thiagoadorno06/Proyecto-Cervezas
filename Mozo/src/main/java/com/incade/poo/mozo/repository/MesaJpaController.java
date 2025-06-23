/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.incade.poo.mozo.repository;

import com.incade.poo.mozo.model.Mesa;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MesaJpaController implements Serializable {
    
    public MesaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public MesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mesa mesa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mesa mesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mesa = em.merge(mesa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mesa.getId();
                if (findMesa(id) == null) {
                    throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesa mesa;
            try {
                mesa = em.getReference(Mesa.class, id);
                mesa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.", enfe);
            }
            em.remove(mesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mesa> findMesaEntities() {
        return findMesaEntities(true, -1, -1);
    }

    public List<Mesa> findMesaEntities(int maxResults, int firstResult) {
        return findMesaEntities(false, maxResults, firstResult);
    }

    private List<Mesa> findMesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mesa.class));
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

    public Mesa findMesa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mesa.class, id);
        } finally {
            em.close();
        }
    }
    
    public Mesa findMesaByNumero(Long numero) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM mesa m WHERE m.numero = :numero");
            query.setParameter("numero", numero);
            return (Mesa) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No cerveza found with that name
        } finally {
            em.close();
        }
    }

    public int getMesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mesa> rt = cq.from(Mesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
