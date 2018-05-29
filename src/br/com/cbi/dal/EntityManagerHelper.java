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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

/**
 *
 * @author Tiago D. Teixeira
 */
public class EntityManagerHelper {

    private final Map<String, ThreadLocal<EntityManager>> sessions = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(EntityManagerHelper.class);
    private static final Map<String, String> propMap = new HashMap();

    public EntityManagerHelper() {
    }

    public boolean getOperation(OPERATION_TYPE operation_type, Object object, PERSISTENCE_UNIT persistence_unit) {
        EntityManager session = getSession(persistence_unit);
        try {
            session.getTransaction().begin();
            switch (operation_type) {
                case SAVE:
                    LOG.info("Salvando registro no banco de dados");
                    //session.getTransaction().begin();
                    session.persist(object);
                    session.getTransaction().commit();
                    //JOptionPane.showMessageDialog(null, "Registro salvo com sucesso", "Registro Salvo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case UPDATE:
                    LOG.info("Atualizando registro no banco de dados");
                    //session.getTransaction().begin();
                    session.merge(object);
                    session.getTransaction().commit();
                    //JOptionPane.showMessageDialog(null, "Registro alterado com sucesso", "Registro Alterado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case DELETE:
                    LOG.info("Deletando registro no banco de dados");
                    //session.getTransaction().begin();
                    session.remove(session.merge(object));
                    session.getTransaction().commit();
                    //JOptionPane.showMessageDialog(null, "Registro deletado com sucesso", "Registro Deletado", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
            this.closeSession(persistence_unit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //session.getTransaction().rollback();
            this.closeSession(persistence_unit);
            return false;
        }
    }

    private EntityManager getSession(PERSISTENCE_UNIT persistence_unit) {
        EntityManager session = null;
        if (sessions.isEmpty()) {
            sessions.put(persistence_unit.toString(), new ThreadLocal<>());
            session = sessions.get(persistence_unit.toString()).get();
            session = session == null ? EntityManagerFactoryService.getEntityManagerFactory(persistence_unit.toString(), propMap).createEntityManager() : session;
        } else {
            session = sessions.get(persistence_unit.toString()).get();
            session = session == null ? EntityManagerFactoryService.getEntityManagerFactory(persistence_unit.toString(), propMap).createEntityManager() : session;
        }
        return session;
    }

    private void closeSession(PERSISTENCE_UNIT persistence_unit) {
        EntityManager session = null;
        if (!sessions.isEmpty()) {
            session = sessions.get(persistence_unit.toString()).get();
            LOG.info("Encerrando sessão do banco de dados");
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        LOG.debug("Removendo Entity Manager desta sessão");
        sessions.remove(persistence_unit.toString());
    }

    public void closeAll() {
        LOG.info("Encerrando todas as sessões");
        sessions.clear();
    }

    public Connection getConnection(PERSISTENCE_UNIT persistence_unit) {
        try {
            EntityManager entityManager = getSession(persistence_unit);
            Connection conn = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<?> getObjectList(String strHQL, PERSISTENCE_UNIT persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<?> getObjectList(String strHQL, String strParam, Object valor, PERSISTENCE_UNIT persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<?> getObjectListNamedQuery(Class<?> classType, String namedQuery, String[] strParam, Object[] valor, PERSISTENCE_UNIT persistence_unit) {
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
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<?> getObjectNamedQuery(Class<?> classType, String namedQuery, String strParam, Object valor, PERSISTENCE_UNIT persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createNamedQuery(namedQuery, classType);
            int cont = 0;
            if (strParam != null) {
                query.setParameter(strParam, valor);
            }
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public List<?> getObjectList(String strHQL, String strParam, Boolean valor, PERSISTENCE_UNIT persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public Object getObject(String strHQL, PERSISTENCE_UNIT persistence_unit) {
        try {
            Object temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            temp = objects.get(0);
            return temp;
        } catch (Exception e) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public Object getObject(String strHQL, String strParam, Object valor, PERSISTENCE_UNIT persistence_unit) {
        try {
            Object temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            query.setParameter(strParam, valor);
            List<?> objects = query.getResultList();
            this.closeSession(persistence_unit);
            temp = objects.get(0);
            return temp;
        } catch (Exception ex) {
            this.closeSession(persistence_unit);
            return null;
        }
    }

    public Object getObject(String strHQL, String[] strParam, String[] valor, PERSISTENCE_UNIT persistence_unit) {
        try {
            Object temp;
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createQuery(strHQL);
            for (int i = 0; i < strParam.length; i++) {
                if (strParam[i] != null) {
                    query.setParameter(strParam[i], valor[i]);
                }
            }
            Object objects = query.getSingleResult();
            this.closeSession(persistence_unit);
            temp = objects;
            return temp;
        } catch (Exception ex) {
            this.closeSession(persistence_unit);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static enum OPERATION_TYPE {
        SAVE, UPDATE, DELETE
    }

    public static enum PERSISTENCE_UNIT {
        DERBYDB_PU, ORACLE11G_PU
    }
}
