package com.example.demo.repositories;

import com.example.demo.model.Knuffel;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByid(int id);
    Iterable<User> findUsersByKnuffel(Knuffel knuffel);
    Iterable<User> findAllUsersByPasswordNull();
}
