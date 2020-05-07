package entities;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Embeddable
public class Address {
    private String country;
    private String city;
    private String street;
    private String zip;

    public Address() {
    }

    public Address(String country, String city, String street, String zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

//    @Override
//    public String toString() {
//        return
//    }

}
