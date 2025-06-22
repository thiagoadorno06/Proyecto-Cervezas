/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.incade.poo.mozo.repository;

import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.incade.poo.mozo.model.Mozo;
import com.incade.poo.mozo.model.Item;
import com.incade.poo.mozo.model.Pedido;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PedidoJpaController implements Serializable {
    
    public PedidoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getItems() == null) {
            pedido.setItems(new ArrayList<Item>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mozo mozo = pedido.getMozo();
            if (mozo != null) {
                mozo = em.getReference(mozo.getClass(), mozo.getId());
                pedido.setMozo(mozo);
            }
            List<Item> attachedItems = new ArrayList<Item>();
            for (Item itemsItemToAttach : pedido.getItems()) {
                itemsItemToAttach = em.getReference(itemsItemToAttach.getClass(), itemsItemToAttach.getId());
                attachedItems.add(itemsItemToAttach);
            }
            pedido.setItems(attachedItems);
            em.persist(pedido);
            if (mozo != null) {
                mozo.getPedidos().add(pedido);
                mozo = em.merge(mozo);
            }
            for (Item itemsItem : pedido.getItems()) {
                Pedido oldPedidoOfItemsItem = itemsItem.getPedido();
                itemsItem.setPedido(pedido);
                itemsItem = em.merge(itemsItem);
                if (oldPedidoOfItemsItem != null) {
                    oldPedidoOfItemsItem.getItems().remove(itemsItem);
                    oldPedidoOfItemsItem = em.merge(oldPedidoOfItemsItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getId());
            Mozo mozoOld = persistentPedido.getMozo();
            Mozo mozoNew = pedido.getMozo();
            List<Item> itemsOld = persistentPedido.getItems();
            List<Item> itemsNew = pedido.getItems();
            if (mozoNew != null) {
                mozoNew = em.getReference(mozoNew.getClass(), mozoNew.getId());
                pedido.setMozo(mozoNew);
            }
            List<Item> attachedItemsNew = new ArrayList<Item>();
            for (Item itemsNewItemToAttach : itemsNew) {
                itemsNewItemToAttach = em.getReference(itemsNewItemToAttach.getClass(), itemsNewItemToAttach.getId());
                attachedItemsNew.add(itemsNewItemToAttach);
            }
            itemsNew = attachedItemsNew;
            pedido.setItems(itemsNew);
            pedido = em.merge(pedido);
            if (mozoOld != null && !mozoOld.equals(mozoNew)) {
                mozoOld.getPedidos().remove(pedido);
                mozoOld = em.merge(mozoOld);
            }
            if (mozoNew != null && !mozoNew.equals(mozoOld)) {
                mozoNew.getPedidos().add(pedido);
                mozoNew = em.merge(mozoNew);
            }
            for (Item itemsOldItem : itemsOld) {
                if (!itemsNew.contains(itemsOldItem)) {
                    itemsOldItem.setPedido(null);
                    itemsOldItem = em.merge(itemsOldItem);
                }
            }
            for (Item itemsNewItem : itemsNew) {
                if (!itemsOld.contains(itemsNewItem)) {
                    Pedido oldPedidoOfItemsNewItem = itemsNewItem.getPedido();
                    itemsNewItem.setPedido(pedido);
                    itemsNewItem = em.merge(itemsNewItem);
                    if (oldPedidoOfItemsNewItem != null && !oldPedidoOfItemsNewItem.equals(pedido)) {
                        oldPedidoOfItemsNewItem.getItems().remove(itemsNewItem);
                        oldPedidoOfItemsNewItem = em.merge(oldPedidoOfItemsNewItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pedido.getId();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Mozo mozo = pedido.getMozo();
            if (mozo != null) {
                mozo.getPedidos().remove(pedido);
                mozo = em.merge(mozo);
            }
            List<Item> items = pedido.getItems();
            for (Item itemsItem : items) {
                itemsItem.setPedido(null);
                itemsItem = em.merge(itemsItem);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
