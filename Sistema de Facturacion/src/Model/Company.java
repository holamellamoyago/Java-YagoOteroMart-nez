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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (companyCode == null) {
            if (other.companyCode != null)
                return false;
        } else if (!companyCode.equals(other.companyCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getName() {
        return name;
    }

    public String getPostal() {
        return postal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    

    


    

    

    
}
