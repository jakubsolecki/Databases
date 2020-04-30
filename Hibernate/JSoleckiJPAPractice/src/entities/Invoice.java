package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceID;
    private int invoiceNumber;
    private int quantity;
    @ManyToMany(mappedBy = "invoices")
    private Set<Product> products;

    public Invoice() {
    }

    public Invoice(int invoiceNumber, int quantity) {
        this.invoiceNumber = invoiceNumber;
        this.quantity = quantity;
        this.products = new HashSet<>();
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.addInvoice(this);
    }

    public Set<Product> getProducts() {
        return products;
    }
}
