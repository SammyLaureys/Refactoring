package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Game {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @SequenceGenerator(name = "game_generator", sequenceName = "game_seq", initialValue = 0, allocationSize = 1)
    @Id
    private int id;
    private String titelGame;
    private String pictureGame;
    @ManyToOne(fetch = FetchType.LAZY)
    private Knuffel knuffel;

    public Game() {
    }

    public Game(int id, String titelGame, String pictureGame, Knuffel knuffel) {
        this.id = id;
        this.titelGame = titelGame;
        this.pictureGame = pictureGame;
        this.knuffel = knuffel;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitelGame() {
        return titelGame;
    }

    public void setTitelGame(String titelGame) {
        this.titelGame = titelGame;
    }


    public String getPictureGame() {
        return pictureGame;
    }

    public void setPictureGame(String pictureGame) {
        this.pictureGame = pictureGame;
    }


    public Knuffel getKnuffel() {
        return knuffel;
    }

    public void setKnuffel(Knuffel knuffel) {
        this.knuffel = knuffel;
    }

}








