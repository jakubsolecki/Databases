package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends Company{
    private int discount;

    public Customer() {
        super();
    }

    public Customer(String companyName, String street, String city, String zip, int discount) {
        super(companyName, street, city, zip);
        this.discount = discount;
    }
}
