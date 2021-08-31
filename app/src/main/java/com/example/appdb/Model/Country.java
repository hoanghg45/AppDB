package com.example.appdb.Model;
import java.util.Date;
public class Country {
    private int    Id;
    private String Name;
    private String Add;
    private String DorF;
    private String Country;
    private int Zipcode;

    public Country(int id, String name, String add, String dorF, String country, int zipcode) {
        Id = id;
        Name = name;
        Add = add;
        DorF = dorF;
        Country = country;
        Zipcode = zipcode;
    }

    public int getId() {
        return Id;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }

    public String getDorF() {
        return DorF;
    }

    public void setDorF(String dorF) {
        DorF = dorF;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getZipcode() {
        return Zipcode;
    }

    public void setZipcode(int zipcode) {
        Zipcode = zipcode;
    }
}
