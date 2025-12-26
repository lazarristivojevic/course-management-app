package crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.LR56323Kurs;
import utils.PersistenceUtil;

public class KursCrud {

    
    public LR56323Kurs insertKurs(LR56323Kurs k) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        LR56323Kurs retVal = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(k);
            em.flush();
            et.commit();
            retVal = k;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return retVal;
    }

    
    public LR56323Kurs findKurs(long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        LR56323Kurs retVal = null;

        try {
            retVal = em.find(LR56323Kurs.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return retVal;
    }

    
    public List<LR56323Kurs> listKursevi() {
        EntityManager em = PersistenceUtil.getEntityManager();
        List<LR56323Kurs> lista = null;

        try {
            lista = em.createQuery("SELECT k FROM LR56323Kurs k", LR56323Kurs.class)
                      .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return lista;
    }


    public boolean deleteKurs(long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        boolean retVal = false;

        try {
            et = em.getTransaction();
            et.begin();

            LR56323Kurs k = em.find(LR56323Kurs.class, id);
            if (k != null) {
                em.remove(k);
                em.flush();
                retVal = true;
            }

            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return retVal;
    }

    /*public LR56323Kurs updateKurs(LR56323Kurs k) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        LR56323Kurs retVal = null;

        try {
            et = em.getTransaction();
            et.begin();
            retVal = em.merge(k);
            em.flush();
            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return retVal;
    } */

    
}

