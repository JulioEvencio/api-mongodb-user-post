package com.github.julioevencio.apimongodbuserpost.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.julioevencio.apimongodbuserpost.domain.Post;
import com.github.julioevencio.apimongodbuserpost.repositories.PostRepository;
import com.github.julioevencio.apimongodbuserpost.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);

		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}