package entities;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String productName;
    private int unitsInStock;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Set<Invoice> invoices;

    public Product() {
    }

    public Product(String productName, int unitsInStock) {
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.invoices = new HashSet<>();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.addProduct(this);
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }
}
