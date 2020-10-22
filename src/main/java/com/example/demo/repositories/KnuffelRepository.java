package com.example.demo.repositories;

import com.example.demo.model.Game;
import com.example.demo.model.Knuffel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface KnuffelRepository extends CrudRepository<Knuffel, Integer>{

}
