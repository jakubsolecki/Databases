package jpa;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

//        Product pascal = new Product("Gotuj z TurboPascalem", 20);
//        Product bible = new Product("Biblia C++", 20);
//        Product cyberpunk = new Product("Cyberpunk 2077", 0);
//        Product witcher = new Product("Wiedźmin 3: Dziki Zgon", 100);
        Supplier helion = new Supplier("7476845067904878", "Helion", "Bibliotekarska 3","Kraków",  "30-130");
        Supplier cdProj = new Supplier("0202342032132032", "CD Projekt Red", "Jagiellońska 74", "Warszawa", "12-590");
        Customer tesco = new Customer("Tesco", "Biedna 3", "Sosnowiec", "90-112", 30);
        Customer empik = new Customer("Empik", "Czytelnicza 5", "Krynica Morska", "12-266", 50);

        em.persist(helion);
        em.persist(cdProj);
        em.persist(tesco);
        em.persist(empik);
//        Category books = new Category("Książki");
//        Category games = new Category("Gry");
//        Invoice invoice1 = new Invoice(1, 3);
//        Invoice invoice2 = new Invoice(2, 2);
//        pascal.setCategory(books);
//        bible.setCategory(books);
//        witcher.setCategory(games);
//        cyberpunk.setCategory(games);
//        invoice1.addProduct(pascal);
//        invoice1.addProduct(bible);
//        invoice1.addProduct(cyberpunk);
//        invoice2.addProduct(witcher);
//        invoice2.addProduct(cyberpunk);

//        helion.addProduct(bible);
//        helion.addProduct(pascal);
//        cdProj.addProduct(witcher);
//        cdProj.addProduct(cyberpunk);

//        em.persist(helion);
//        em.persist(cdProj);
//        em.persist(pascal);
//        em.persist(bible);
//        em.persist(cyberpunk);
//        em.persist(witcher);
//        em.persist(books);
//        em.persist(games);
//        em.persist(invoice1);
//        em.persist(invoice2);

//        List<Object[]> result1 = em.createNativeQuery("select * from Company where DTYPE = 'Supplier'").getResultList();
//        for (Object[] next: result1 )
//            System.out.println(next[0]+" --- "+next[1]+" --- "+next[2]+" --- "+next[3]+" --- "+next[4]
//                    +" --- "+next[5]+" --- "+next[6]);

        etx.commit();
        em.close();
    }
}
