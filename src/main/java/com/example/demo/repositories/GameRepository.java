package com.example.demo.repositories;

import com.example.demo.model.Game;
import com.example.demo.model.Knuffel;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    Iterable<Game> findGameByKnuffel(Knuffel knuffel);

}

