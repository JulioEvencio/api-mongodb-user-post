package com.github.julioevencio.apimongodbuserpost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.julioevencio.apimongodbuserpost.domain.User;
import com.github.julioevencio.apimongodbuserpost.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

}
