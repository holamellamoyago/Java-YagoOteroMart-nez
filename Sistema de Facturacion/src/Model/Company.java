package Model;

import java.io.Serializable;

public class Company implements Serializable{
    private String adress, city, companyCode, name;
    private String postal, phoneNumber;

    public Company(String adress, String city, String companyCode, String name, String postal, String phoneNumber) {
        this.adress = adress;
        this.city = city;
        this.companyCode = companyCode;
        this.name = name;
        this.postal = postal;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }


    

    

    
}
