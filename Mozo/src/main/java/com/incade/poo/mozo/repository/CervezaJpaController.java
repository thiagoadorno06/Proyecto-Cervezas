/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.incade.poo.mozo.repository;

import com.incade.poo.mozo.model.Cerveza;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CervezaJpaController implements Serializable {
    
    public CervezaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public CervezaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cerveza cerveza) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cerveza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cerveza cerveza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cerveza = em.merge(cerveza);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cerveza.getId();
                if (findCerveza(id) == null) {
                    throw new NonexistentEntityException("The cerveza with id " + id + " no longer exists.");
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
            Cerveza cerveza;
            try {
                cerveza = em.getReference(Cerveza.class, id);
                cerveza.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cerveza with id " + id + " no longer exists.", enfe);
            }
            em.remove(cerveza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cerveza> findCervezaEntities() {
        return findCervezaEntities(true, -1, -1);
    }

    public List<Cerveza> findCervezaEntities(int maxResults, int firstResult) {
        return findCervezaEntities(false, maxResults, firstResult);
    }

    private List<Cerveza> findCervezaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cerveza.class));
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

    public Cerveza findCerveza(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cerveza.class, id);
        } finally {
            em.close();
        }
    }

    public int getCervezaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cerveza> rt = cq.from(Cerveza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
