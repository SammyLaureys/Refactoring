package com.example.demo.repositories;

import com.example.demo.model.Knuffel;
import com.example.demo.model.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Integer> {

    Iterable<Video> findVideosByKnuffel(Knuffel knuffel);


}

