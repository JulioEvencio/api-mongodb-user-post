package com.github.julioevencio.apimongodbuserpost.configs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.julioevencio.apimongodbuserpost.domain.Post;
import com.github.julioevencio.apimongodbuserpost.domain.User;
import com.github.julioevencio.apimongodbuserpost.dto.AuthorDTO;
import com.github.julioevencio.apimongodbuserpost.repositories.PostRepository;
import com.github.julioevencio.apimongodbuserpost.repositories.UserRepository;

@Configuration
public class InstantiationConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		User fulano = new User(null, "fulano", "fulano@fulano");
		User ciclano = new User(null, "ciclano", "ciclano@ciclano");
		User beltrano = new User(null, "beltrano", "beltrano@beltrano");
		
		userRepository.saveAll(Arrays.asList(fulano, ciclano, beltrano));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(fulano));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(fulano));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		fulano.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(fulano);
	}

}
