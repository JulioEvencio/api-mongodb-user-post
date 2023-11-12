package com.github.julioevencio.apimongodbuserpost.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apimongodbuserpost.domain.Post;
import com.github.julioevencio.apimongodbuserpost.resource.exceptions.StandardError;
import com.github.julioevencio.apimongodbuserpost.resource.utils.URL;
import com.github.julioevencio.apimongodbuserpost.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Return all posts by title",
			description = "Return all posts by title",
			tags = {"Posts"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Return all posts by title",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = Post.class))
							)
					)
			}
	)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String param) {
		String text = URL.decodeParam(param);
		List<Post> response = service.findByTitle(text);

		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Return all posts by title and date",
			description = "Return all posts by title and date",
			tags = {"Posts"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Return all posts by title and date",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = Post.class))
							)
					)
			}
	)
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value = "minDate", defaultValue = "") String minDate, @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(minDate, new Date(0L));
		
		title = URL.decodeParam(title);
		
		List<Post> response = service.fullSearch(title, min, max);

		return ResponseEntity.ok().body(response);
	}
	
}
