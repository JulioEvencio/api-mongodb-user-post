package com.github.julioevencio.apimongodbuserpost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apimongodbuserpost.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
