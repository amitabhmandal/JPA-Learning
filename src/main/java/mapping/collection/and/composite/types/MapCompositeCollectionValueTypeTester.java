package mapping.collection.and.composite.types;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class MapCompositeCollectionValueTypeTester {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory()
            .openSession();

        try {
            Transaction transaction = session.beginTransaction();

            // session.save();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory()
                .close();
        }

    }

}
