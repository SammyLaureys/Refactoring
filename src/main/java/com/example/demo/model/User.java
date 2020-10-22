package com.example.demo.model;

import javax.persistence.*;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", initialValue = 0, allocationSize = 1)
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String adress;
    private String postcode;
    private String stadGemeente;
    @ManyToOne(fetch = FetchType.LAZY)
    private Knuffel knuffel;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Knuffel getKnuffel() {
        return knuffel;
    }

    public void setKnuffel(Knuffel knuffel) {
        this.knuffel = knuffel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    //    public void setKnuffel(Knuffel knuffel) {
//        this.knuffel = knuffel;
//    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStadGemeente() {
        return stadGemeente;
    }

    public void setStadGemeente(String stadGemeente) {
        this.stadGemeente = stadGemeente;
    }
}
