import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String productName;
    private int unitsInStock;
    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    public Product() {
    }

    public Product(String productName, int unitsInStock) {
        this.productName = productName;
        this.unitsInStock = unitsInStock;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
