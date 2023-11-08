package com.github.julioevencio.apimongodbuserpost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apimongodbuserpost.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
