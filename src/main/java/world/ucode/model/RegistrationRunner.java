package world.ucode.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.ucode.pojo.Registration;

import java.util.List;

public class RegistrationRunner {
    private static SessionFactory sessionFactory;

    public void createUser() {
        try {
            Session session = sessionFactory.openSession();
            //SQLQuery createIt = null;
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE  if not exists registration(id INTEGER RPIMARY KEY AUTOINCREMENT," +
                    "id INT, login TEXT, password TEXT, role TEXT, state TEXT, IPadress TEXT, email TEXT, hash INT);");
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public Registration getPass(String login2) {
        List<Registration> registrat = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "FROM Registration WHERE login = :login2";
            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("login2", login2);
            registrat = query.list();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
        return registrat != null ? (registrat.size() != 0 ? registrat.get(0) : null) : null;
    }

    public boolean addUser(int id, String login, String password, String role, String state, String IPadress,
                           String email, int hash) {
        if (getPass(login) != null)
            return false;
        else {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = null;
                transaction = session.beginTransaction();
                Registration registration = new Registration(id, login, password, role, state, IPadress, email, hash);
                session.save(registration);
                transaction.commit();
                session.close();
            } catch (Throwable tr) {
                System.out.println("session is bad");
            }
        }
        return true;
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

    public void updateStatus(String login, String IPadress, int hash, String state) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Registration SET IPadress = :IPadress , state = :state, hash = :hash  WHERE  login = :login";

            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("state", state);
            query.setParameter("IPadress", IPadress);
            query.setParameter("hash", hash);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }

    public void changePassword(String login, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            String hql = "UPDATE Registration SET password = :password  WHERE  login = :login";

            org.hibernate.Query query = session.createQuery(hql);
            String st = "activ";
            query.setParameter("login", login);
            query.setParameter("password", password);
            int result = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Throwable tr) {
            System.out.println("session is bad");
        }
    }
}

