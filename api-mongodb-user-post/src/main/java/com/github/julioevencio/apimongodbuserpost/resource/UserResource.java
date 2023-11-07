package com.github.julioevencio.apimongodbuserpost.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apimongodbuserpost.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User fulano = new User("1", "Fulano", "fulano@fulano");
		User ciclano = new User("2", "Ciclano", "ciclano@ciclano");

		List<User> response = new ArrayList<>();

		response.add(fulano);
		response.add(ciclano);

		return ResponseEntity.ok().body(response);
	}

}
