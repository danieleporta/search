package nl.xs4all.banaan.tst8.domain;

public class Address {
    private String name;
    private String street;
    private String city;
    
    public Address(String name, String street, String city) {
        this.name = name;
        this.street = street;
        this.city = city;
    }
    
    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }
    
    public String getCity() {
        return city;
    }
    
}
