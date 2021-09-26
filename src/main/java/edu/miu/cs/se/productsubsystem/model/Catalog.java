package edu.miu.cs.se.productsubsystem.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Catalog {
@Id
@GeneratedValue
private long id;
private String name;

    public Catalog() {

    }
    public Catalog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
