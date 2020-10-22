package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Knuffel {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knuffel_generator")
    @SequenceGenerator(name = "knuffel_generator", sequenceName = "knuffel_seq", initialValue = 0, allocationSize = 1)
    @Id
    private Integer id;
    private String animalName;
    private String animalPrice;
    private String animalPic;
    private String animalDescription;


    public Knuffel(Integer id, String animalName, String animalPrice, String animalPic, String animalDescription) {
        this.id = id;
        this.animalName = animalName;
        this.animalPrice = animalPrice;
        this.animalPic = animalPic;
        this.animalDescription = animalDescription;
    }

    public Knuffel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalPrice() {
        return animalPrice;
    }

    public void setAnimalPrice(String animalPrice) {
        this.animalPrice = animalPrice;
    }

    public String getAnimalPic() {
        return animalPic;
    }

    public void setAnimalPic(String animalPic) {
        this.animalPic = animalPic;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }
}
