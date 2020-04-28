import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product1 = new Product("Gotuj z TurboPascalem", 20);
            Product product2 = new Product("Biblia C++", 20);
            Product product3 = new Product("Hibernate dla opornych", 20);
            Supplier supplier = new Supplier("Helion", "Biliotekarska 3", "Kraków");
            supplier.addProduct(product1);
            supplier.addProduct(product2);
            supplier.addProduct(product3);
            session.save(supplier);
            session.save(product1);
            session.save(product2);
            session.save(product3);
        } finally {
            tx.commit();
            session.close();
        }
    }
}
