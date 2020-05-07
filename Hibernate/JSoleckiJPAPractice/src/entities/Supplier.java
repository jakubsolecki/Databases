package entities;

import entities.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier extends Company{
    private String bankAccountNumber;
//    @OneToMany(mappedBy = "supplier")
//    private Set<Product> products;

    public Supplier() {
        super();
    }

    public Supplier(String bankAccountNumber, String companyName, String street, String city, String zip) {
        super(companyName, street, city, zip);
        this.bankAccountNumber = bankAccountNumber;
//        this.products = new HashSet<>();
    }

//    public void addProduct(Product product) {
//        this.products.add(product);
//    }
}
