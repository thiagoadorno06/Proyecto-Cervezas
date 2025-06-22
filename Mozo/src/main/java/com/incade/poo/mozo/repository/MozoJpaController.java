/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.incade.poo.mozo.repository;

import com.incade.poo.mozo.model.Mozo;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MozoJpaController implements Serializable {
    
    public MozoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("repositoryPU"); // nombre de unidad de persistencia
    }

    public MozoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mozo mozo) {
        if (mozo.getPedidos() == null) {
            mozo.setPedidos(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidos = new ArrayList<Pedido>();
            for (Pedido pedidosPedidoToAttach : mozo.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            mozo.setPedidos(attachedPedidos);
            em.persist(mozo);
            for (Pedido pedidosPedido : mozo.getPedidos()) {
                Mozo oldMozoOfPedidosPedido = pedidosPedido.getMozo();
                pedidosPedido.setMozo(mozo);
                pedidosPedido = em.merge(pedidosPedido);
                if (oldMozoOfPedidosPedido != null) {
                    oldMozoOfPedidosPedido.getPedidos().remove(pedidosPedido);
                    oldMozoOfPedidosPedido = em.merge(oldMozoOfPedidosPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mozo mozo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mozo persistentMozo = em.find(Mozo.class, mozo.getId());
            List<Pedido> pedidosOld = persistentMozo.getPedidos();
            List<Pedido> pedidosNew = mozo.getPedidos();
            List<Pedido> attachedPedidosNew = new ArrayList<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            mozo.setPedidos(pedidosNew);
            mozo = em.merge(mozo);
            for (Pedido pedidosOldPedido : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedido)) {
                    pedidosOldPedido.setMozo(null);
                    pedidosOldPedido = em.merge(pedidosOldPedido);
                }
            }
            for (Pedido pedidosNewPedido : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedido)) {
                    Mozo oldMozoOfPedidosNewPedido = pedidosNewPedido.getMozo();
                    pedidosNewPedido.setMozo(mozo);
                    pedidosNewPedido = em.merge(pedidosNewPedido);
                    if (oldMozoOfPedidosNewPedido != null && !oldMozoOfPedidosNewPedido.equals(mozo)) {
                        oldMozoOfPedidosNewPedido.getPedidos().remove(pedidosNewPedido);
                        oldMozoOfPedidosNewPedido = em.merge(oldMozoOfPedidosNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mozo.getId();
                if (findMozo(id) == null) {
                    throw new NonexistentEntityException("The mozo with id " + id + " no longer exists.");
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
            Mozo mozo;
            try {
                mozo = em.getReference(Mozo.class, id);
                mozo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mozo with id " + id + " no longer exists.", enfe);
            }
            List<Pedido> pedidos = mozo.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.setMozo(null);
                pedidosPedido = em.merge(pedidosPedido);
            }
            em.remove(mozo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mozo> findMozoEntities() {
        return findMozoEntities(true, -1, -1);
    }

    public List<Mozo> findMozoEntities(int maxResults, int firstResult) {
        return findMozoEntities(false, maxResults, firstResult);
    }

    private List<Mozo> findMozoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mozo.class));
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

    public Mozo findMozo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mozo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMozoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mozo> rt = cq.from(Mozo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
