package com.github.julioevencio.apimongodbuserpost.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apimongodbuserpost.domain.Post;
import com.github.julioevencio.apimongodbuserpost.resource.exceptions.StandardError;
import com.github.julioevencio.apimongodbuserpost.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/posts")
@Tag(name = "Posts", description = "Endpoints for posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Return a post by id",
			description = "Return a post by id",
			tags = {"Posts"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Return a post by id",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = Post.class)
							)
					),
					@ApiResponse(
							responseCode = "404",
							description = "Post not found",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = StandardError.class)
							)
					)
			}
	)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post response = service.findById(id);

		return ResponseEntity.ok().body(response);
	}
	
}
