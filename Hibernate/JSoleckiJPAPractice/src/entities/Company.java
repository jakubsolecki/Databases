package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String companyName;
    private String street;
    private String city;
    private String zip;

    public Company() {
    }

    public Company(String companyName, String street, String city, String zip) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
