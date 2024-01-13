package ci.aho.demo.models.repositories;

import ci.aho.demo.models.entities.Equipement;
import ci.aho.demo.models.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class EquipementRepository implements Repository<Equipement> {
    @Override
    public void create(Equipement equipement) throws SQLException {
        Transaction tx = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            tx = session.beginTransaction();
            session.save(equipement);
            tx.commit();

            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Equipement getById(int equipementId) {
        Transaction tx = null;
        Equipement equipement = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            tx = session.beginTransaction();
            equipement = session.get(Equipement.class, equipementId);
            tx.commit();

            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return equipement;
    }

    @Override
    public List<Equipement> getAll() {
        Transaction tx = null;
        List<Equipement> equipements = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            tx = session.beginTransaction();
            equipements = session.createQuery("from Equipement", Equipement.class).list();
            tx.commit();

            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return equipements;
    }

    @Override
    public boolean delete(Long equipementId) throws SQLException {
        Transaction tx = null;
        Equipement equipement = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            tx = session.beginTransaction();
            if (equipement != null) {
                session.delete(equipement);
                System.out.println("equipement is deleted");
                return true;
            }
            tx.commit();

            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Equipement equipement) throws SQLException {
        Transaction tx = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {

            // start a tx
            tx = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(equipement);
            // commit tx
            tx.commit();

            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
