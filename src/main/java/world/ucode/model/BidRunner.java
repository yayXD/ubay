package world.ucode.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.ucode.pojo.Bid;
import world.ucode.pojo.Registration;

import java.util.List;

public class BidRunner {
    private static SessionFactory sessionFactory;

    public void createBid() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE  if not exists Bid(id INTEGER RPIMARY KEY AUTOINCREMENT," +
                    "id INT, itemLot INT, customNumber TEXT, bid INT);");
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void addBid(int id, int itemLot, String customerLogin, int bid) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Bid bids = new Bid(id, itemLot, customerLogin, bid);
            session.save(bids);
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public static void getSessionFactory() {
        try {
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable tr) {
            System.out.println("Enitial SessionFactory creation failed:: " + tr);
        }
    }

    public List<Bid> getBid(int itemLot) {
        List<Bid> bid = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Bid WHERE itemLot = :itemLot";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("itemLot", itemLot);
            bid = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return bid != null ? (bid.size() != 0 ? bid : null) : null;
    }
}