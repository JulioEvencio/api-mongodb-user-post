package com.github.julioevencio.apimongodbuserpost.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.julioevencio.apimongodbuserpost.domain.Post;
import com.github.julioevencio.apimongodbuserpost.domain.User;
import com.github.julioevencio.apimongodbuserpost.dto.UserDTO;
import com.github.julioevencio.apimongodbuserpost.resource.exceptions.StandardError;
import com.github.julioevencio.apimongodbuserpost.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "Endpoints for users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Return all users",
			description = "Return all users",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Return all users",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))
							)
					)
			}
	)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> response = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Return an user by id",
			description = "Return an user by id",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Return an user by id",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = UserDTO.class)
							)
					),
					@ApiResponse(
							responseCode = "404",
							description = "User not found",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = StandardError.class)
							)
					)
			}
	)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		UserDTO response = new UserDTO(user);

		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Insert an user",
			description = "Insert an user",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "Insert an user"
					)
			}
	)
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
		User user = service.fromDTO(dto);
		
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Delete an user",
			description = "Delete an user",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "204",
							description = "Delete an user"
					)
			}
	)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Update an user by id",
			description = "Update an user by id",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "204",
							description = "Update an user by id"
					),
					@ApiResponse(
							responseCode = "404",
							description = "User not found",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = StandardError.class)
							)
					)
			}
	)
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO dto) {
		User user = service.fromDTO(dto);
		
		user.setId(id);
		user = service.update(user);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "return all posts for user id",
			description = "return all posts for user id",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "return all posts for user id",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = Post.class)
							)
					),
					@ApiResponse(
							responseCode = "404",
							description = "User not found",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = StandardError.class)
							)
					)
			}
	)
	public ResponseEntity<List<Post>> findAllPosts(@PathVariable String id) {
		User user = service.findById(id);		
		List<Post> response = user.getPosts();
		
		return ResponseEntity.ok().body(response);
	}

}
