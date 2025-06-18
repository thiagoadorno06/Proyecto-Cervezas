package com.incade.poo.mozo.repository;

import com.incade.poo.mozo.model.Cerveza;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CervezaJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public CervezaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public CervezaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cerveza cerveza) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cerveza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cerveza cerveza) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            cerveza = em.merge(cerveza);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Error al editar la cerveza: " + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cerveza cerveza;
            try {
                cerveza = em.getReference(Cerveza.class, id);
                cerveza.getId(); // Asegura que existe
            } catch (Exception e) {
                throw new Exception("La cerveza con id " + id + " no existe.", e);
            }
            em.remove(cerveza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
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

    public List<Cerveza> findCervezaEntities() {
        return findCervezaEntities(true, -1, -1);
    }

    public List<Cerveza> findCervezaEntities(int maxResults, int firstResult) {
        return findCervezaEntities(false, maxResults, firstResult);
    }

    private List<Cerveza> findCervezaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
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

    public int getCervezaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<Cerveza> rt = cq.from(Cerveza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}