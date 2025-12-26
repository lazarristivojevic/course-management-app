package crud;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.LR56323Kurs;
import model.LR56323KursPolaznik;
import model.LR56323Polaznik;
import utils.PersistenceUtil;

public class KursPolaznikCrud {

    public LR56323KursPolaznik insertKursPolaznik(LR56323KursPolaznik kp) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;
        LR56323KursPolaznik retVal = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(kp);
            em.flush();
            et.commit();
            retVal = kp;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null && et.isActive())
                et.rollback();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }

        return retVal;
    }

    public List<LR56323Polaznik> findPolazniciZaKurs(long kursId) {
        EntityManager em = PersistenceUtil.getEntityManager();
        List<LR56323Polaznik> lista = null;
        try {
            lista = em.createQuery(
                    "SELECT kp.LR56323Polaznik FROM LR56323KursPolaznik kp WHERE kp.LR56323Kurs.kursId = :id",
                    LR56323Polaznik.class)
                    .setParameter("id", kursId)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
        return lista;
    }
    
    public void poveziKursIPolaznika(LR56323Kurs kurs, LR56323Polaznik polaznik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            
            kurs = em.merge(kurs);
            polaznik = em.merge(polaznik);

            //proveravamo da li postoji veza
            Long count = em.createQuery(
                    "SELECT COUNT(kp) FROM LR56323KursPolaznik kp " +
                    "WHERE kp.LR56323Kurs.kursId = :kursId AND kp.LR56323Polaznik.polaznikId = :polaznikId",
                    Long.class)
                    .setParameter("kursId", kurs.getKursId())
                    .setParameter("polaznikId", polaznik.getPolaznikId())
                    .getSingleResult();

            if (count == 0) {
                //ako veza ne postoji, kreiramo je
                LR56323KursPolaznik kp = new LR56323KursPolaznik();
                kp.setLR56323Kurs(kurs);
                kp.setLR56323Polaznik(polaznik);

                em.persist(kp);
            }

            em.flush();
            et.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null) et.rollback();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    
}
