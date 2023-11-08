package com.github.julioevencio.apimongodbuserpost.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.julioevencio.apimongodbuserpost.domain.User;
import com.github.julioevencio.apimongodbuserpost.repositories.UserRepository;

@Configuration
public class InstantiationConfig implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		User fulano = new User(null, "fulano", "fulano@fulano");
		User ciclano = new User(null, "ciclano", "ciclano@ciclano");
		User beltrano = new User(null, "beltrano", "beltrano@beltrano");

		repository.saveAll(Arrays.asList(fulano, ciclano, beltrano));
	}

}
