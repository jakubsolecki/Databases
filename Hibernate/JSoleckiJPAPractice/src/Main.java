import entities.Category;
import entities.Invoice;
import entities.Product;
import entities.Supplier;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import java.util.Collection;

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
//            Product pascal = new Product("Gotuj z TurboPascalem", 20);
//            Product bible = new Product("Biblia C++", 20);
//            Product cyberpunk = new Product("Cyberpunk 2077", 0);
//            Product witcher = new Product("Wiedźmin 3: Dziki Zgon", 100);
//            Supplier helion = new Supplier("Helion", "Bibliotekarska 3", "Kraków");
//            Supplier cdProj = new Supplier("CD Projekt Red", "Jagiellońska 74", "Warszawa");
//            Category books = new Category("Książki");
//            Category games = new Category("Gry");
//            Invoice invoice1 = new Invoice(1, 3);
//            Invoice invoice2 = new Invoice(2, 2);
//            invoice1.addProduct(pascal);
//            invoice1.addProduct(bible);
//            invoice1.addProduct(cyberpunk);
//            invoice2.addProduct(witcher);
//            invoice2.addProduct(cyberpunk);
//            pascal.setCategory(books);
//            bible.setCategory(books);
//            witcher.setCategory(games);
//            cyberpunk.setCategory(games);
//            helion.addProduct(pascal);
//            helion.addProduct(bible);
//            cdProj.addProduct(cyberpunk);
//            cdProj.addProduct(witcher);
//
//            session.save(pascal);
//            session.save(bible);
//            session.save(cyberpunk);
//            session.save(witcher);
//            session.save(helion);
//            session.save(cdProj);
//            session.save(books);
//            session.save(games);
//            session.save(invoice1);
//            session.save(invoice2);

            Invoice invoice = session.get(Invoice.class, 9);
            Query productQuery = session.createQuery("select P from Product P " +
                    "join P.invoices i where i.invoiceID = :invID").setParameter("invID", invoice.getInvoiceID());
            Product product = session.get(Product.class, 3);
            Query invoiceQuery = session.createQuery("select I from Invoice I " +
                    "join I.products p where p.productID = :prodID").setParameter("prodID", product.getProductID());

            System.out.println("Products with Invoice " + invoice.getInvoiceNumber() + ":");
            for(Object o : productQuery.getResultList()) {
                Product prod = (Product) o;
                System.out.println(prod.getProductName());
            }

            System.out.println("\nInvoices for product " + product.getProductName() + ":");
            for(Object o : invoiceQuery.getResultList()) {
                Invoice inv = (Invoice) o;
                System.out.println("Invoice number: " + inv.getInvoiceNumber());
            }

        } finally {
            tx.commit();
            session.close();
        }
    }
}
