/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.incade.poo.mozo.repository;

import com.incade.poo.mozo.model.Item;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.incade.poo.mozo.model.Pedido;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ItemJpaController implements Serializable {
    
    public ItemJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public ItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = item.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getId());
                item.setPedido(pedido);
            }
            em.persist(item);
            if (pedido != null) {
                pedido.getItems().add(item);
                pedido = em.merge(pedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Item item) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getId());
            Pedido pedidoOld = persistentItem.getPedido();
            Pedido pedidoNew = item.getPedido();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getId());
                item.setPedido(pedidoNew);
            }
            item = em.merge(item);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getItems().remove(item);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getItems().add(item);
                pedidoNew = em.merge(pedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = item.getId();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            Pedido pedido = item.getPedido();
            if (pedido != null) {
                pedido.getItems().remove(item);
                pedido = em.merge(pedido);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
