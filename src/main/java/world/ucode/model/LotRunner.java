package world.ucode.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.ucode.pojo.Lot;

import java.util.GregorianCalendar;
import java.util.List;

public class LotRunner {
    private static SessionFactory sessionFactory;

    public static void getSessionFactory() {
        try {
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable tr) {
            System.out.println("Enitial SessionFactory creation failed:: " + tr);
        }
    }

    public void createLot() {
        try {
            Session session = sessionFactory.openSession();
            //SQLQuery createIt = null;
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE  if not exists Lot(id INTEGER RPIMARY KEY AUTOINCREMENT," +
                    "id INT, itemNumber INT, name TEXT, type INT, loginSeller TEXT, description TEXT, price INT," +
                    "increase INT, startTime TEXT, duration TEXT, pictureName TEXT, status TEXT, winner TEXT, " +
                    "markCustomer INT,feedbackCustomer TEXT, finalprice INT);");
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void addLot(int id, int itemNumder, String name, int type, String loginSeller, String description,
                       int price, int increase, String startTime, String duration, String pictureName, String status, String winner,
                       int markCustomer, String feedbackCustomer, int finalprice) {
        try {
            if(pictureName == null)
                pictureName = "default.jpg";
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Lot lot = new Lot(id, itemNumder, name, type, loginSeller, description, price, increase, startTime,
                    duration, pictureName, "passive", null, 0, null, price);
            session.save(lot);
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public Lot getItemNumber(int itemNumber2) {
        List<Lot> lt = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE itemNumber = :itemNumber2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber2", itemNumber2);
            lt = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lt != null ? (lt.size() != 0 ? lt.get(0) : null) : null;
    }

    public void updateStatus(int itemNumber, String status) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Lot SET status = :status WHERE  itemNumber = :itemNumber";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber", itemNumber);
            query.setParameter("status", status);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void updateFinalprice(int itemNumber, int finalprice) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Lot SET finalprice = :finalprice WHERE itemNumder = :itemNumber";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber", itemNumber);
            query.setParameter("finalprice", finalprice);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void updateBiderFeedback(int itemNumber, int mark, String feedback) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Lot SET markCustomer = :mark, feedbackCustomer = :feedback WHERE  itemNumber = :itemNumber";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber", itemNumber);
            query.setParameter("mark", mark);
            query.setParameter("feedback", feedback);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void updateWinner(int itemLot, String winner) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Lot SET winner = :winner WHERE  itemNumber = :itemNumber";

            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber", itemLot);
            query.setParameter("winner", winner);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public List<Lot> getName(String name2) {
        List<Lot> lot = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE name = :name2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("name2", name2);
            lot = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lot != null ? (lot.size() != 0 ? lot : null) : null;
    }

    public List<Lot> getItemNumber2(int itemNumber2) {
        List<Lot> lt = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE itemNumber = :itemNumber2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemNumber2", itemNumber2);
            lt = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lt != null ? (lt.size() != 0 ? lt : null) : null;
    }

    public List<Lot> getLoginSeller(String loginSeller2) {
        List<Lot> lot = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE loginSeller = :loginSeller2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("loginSeller2", loginSeller2);
            lot = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lot != null ? (lot.size() != 0 ? lot : null) : null;
    }

    public List<Lot> getStatus(String status) {
        List<Lot> lot = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE status = :status";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("status", status);
            lot = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lot != null ? (lot.size() != 0 ? lot : null) : null;
    }

    public List<Lot> getStartTime(String startTime2) {
        List<Lot> lot = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot WHERE startTime = :startTime2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("startTime2", startTime2);
            lot = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lot != null ? (lot.size() != 0 ? lot : null) : null;
    }

    public List<Lot> getLastN(int N) {
        List<Lot> lot = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Lot ORDER BY id DESC";
            lot = session.createQuery(hql).setMaxResults(N).list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return lot != null ? (lot.size() != 0 ? lot : null) : null;
    }

}
