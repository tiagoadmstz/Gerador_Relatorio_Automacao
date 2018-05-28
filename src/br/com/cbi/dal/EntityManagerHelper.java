/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.dal;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

/**
 *
 * @author Tiago D. Teixeira
 */
public class EntityManagerHelper<T> {

    private final Map<String, ThreadLocal<EntityManager>> sessions = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(EntityManagerHelper.class.getName());
    private static final Map<String, String> propMap = new HashMap();
    public static final int SAVE = 0, UPDATE = 1, DELETE = 2;
    public static final String DERBYDB_PU = "DERBYDB_PU", ORACLE11G_PU = "ORACLE11G_PU";

    public EntityManagerHelper() {
    }

    public void getOperation(int operation_type, Object object, String persistence_unit) {
        EntityManager session = getSession(persistence_unit);
        try {
            session.getTransaction().begin();
            switch (operation_type) {
                case SAVE:
                    LOG.info("Salvando registro no banco de dados");
                    session.persist(object);
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso", "Registro Salvo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case UPDATE:
                    LOG.info("Atualizando registro no banco de dados");
                    session.merge(object);
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso", "Registro Alterado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case DELETE:
                    LOG.info("Deletando registro no banco de dados");
                    session.remove(object);
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Registro deletado com sucesso", "Registro Deletado", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            this.closeSession(persistence_unit);
        }
    }

    private EntityManager getSession(String persistence_unit) {
        EntityManager session = null;
        if (sessions.isEmpty()) {
            sessions.put(persistence_unit, new ThreadLocal<>());
            session = sessions.get(persistence_unit).get();
            session = session == null ? EntityManagerFactoryService.getEntityManagerFactory(persistence_unit, propMap).createEntityManager() : session;
        }
        return session;
    }

    private void closeSession(String persistence_unit) {
        EntityManager session = null;
        if (!sessions.isEmpty()) {
            session = sessions.get(persistence_unit).get();
            LOG.info("Encerrando sessão do banco de dados");
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        LOG.info("Removendo Entity Manager desta sessão");
        sessions.remove(persistence_unit);
    }

    public void closeAll() {
        LOG.info("Encerrando todas as sessões");
        sessions.clear();
    }

    public Connection getConnection(String persistence_unit) {
        try {
            EntityManager entityManager = getSession(persistence_unit);
            Connection conn = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<T> getObjectList(String strHQL, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            List<T> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<T> getObjectList(String strHQL, String strParam, Object valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<T> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<T> getObjectListNamedQuery(Class<?> classType, String namedQuery, String[] strParam, Object[] valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createNamedQuery(namedQuery, classType);
            int cont = 0;
            if (strParam != null) {
                for (String p : strParam) {
                    query.setParameter(p, valor[cont++]);
                }
            }
            List<T> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<T> getObjectNamedQuery(Class<?> classType, String namedQuery, String strParam, Object valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createNamedQuery(namedQuery, classType);
            int cont = 0;
            if (strParam != null) {
                query.setParameter(strParam, valor);
            }
            List<T> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<T> getObjectList(String strHQL, String strParam, Boolean valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<T> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public T getObject(String strHQL, String persistence_unit) {
        try {
            T temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            List<T> objects = (List<T>) query.getResultList();
            this.closeSession(persistence_unit);
            temp = objects.get(0);
            return temp;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public T getObject(String strHQL, String strParam, Object valor, String persistence_unit) {
        try {
            T temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<T> objects = (List<T>) query.getResultList();
            this.closeSession(persistence_unit);
            temp = objects.get(0);
            return temp;
        } catch (Exception ex) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public T getObject(String strHQL, String[] strParam, String[] valor, String persistence_unit) {
        try {
            T temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            for (int i = 0; i < strParam.length; i++) {
                if (strParam[i] != null) {
                    query.setParameter(strParam[i], valor[i]);
                }
            }
            T objects = (T) query.getSingleResult();
            this.closeSession(persistence_unit);
            temp = objects;
            return temp;
        } catch (Exception ex) {
            this.closeSession(persistence_unit);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
}
