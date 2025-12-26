package crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.LR56323KursPolaznik;
import model.LR56323Polaznik;
import model.LR56323ProveraZnanja;
import utils.PersistenceUtil;

public class PolaznikCrud {

    public LR56323Polaznik insertPolaznik(LR56323Polaznik p) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        LR56323Polaznik retVal = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(p);
            em.flush();
            et.commit();
            retVal = p;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
        return retVal;
    }

    public LR56323Polaznik findPolaznik(long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        LR56323Polaznik retVal = null;
        try {
            retVal = em.find(LR56323Polaznik.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
        return retVal;
    }

    public List<LR56323Polaznik> listPolaznici() {
        EntityManager em = PersistenceUtil.getEntityManager();
        List<LR56323Polaznik> lista = null;
        try {
            lista = em.createQuery("SELECT p FROM LR56323Polaznik p", LR56323Polaznik.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
        return lista;
    }
    
    
    public boolean deletePolaznik(long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        boolean retVal = false;

        try {
            et = em.getTransaction();
            et.begin();

            LR56323Polaznik p = em.find(LR56323Polaznik.class, id);

            if (p != null) {
                
                List<LR56323KursPolaznik> upisi = em.createQuery(
                    "SELECT kp FROM LR56323KursPolaznik kp WHERE kp.LR56323Polaznik = :polaznik", 
                    LR56323KursPolaznik.class)
                    .setParameter("polaznik", p)
                    .getResultList();

                for (LR56323KursPolaznik kp : upisi) {
                    
                    List<LR56323ProveraZnanja> provere = em.createQuery(
                        "SELECT pr FROM LR56323ProveraZnanja pr WHERE pr.LR56323KursPolaznik = :kp",
                        LR56323ProveraZnanja.class)
                        .setParameter("kp", kp)
                        .getResultList();

                    for (LR56323ProveraZnanja pr : provere) {
                        em.remove(pr);
                    }

                    em.remove(kp);
                }

                em.remove(p);

                em.flush();
                et.commit();
                retVal = true;
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return retVal;
    }


    public LR56323Polaznik updatePolaznik(LR56323Polaznik p) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        LR56323Polaznik retVal = null;
        try {
            et = em.getTransaction();
            et.begin();
            retVal = em.merge(p);
            em.flush();
            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
        return retVal;
    }
}
