package com.github.julioevencio.apimongodbuserpost.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apimongodbuserpost.domain.User;
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
									array = @ArraySchema(schema = @Schema(implementation = User.class))
							)
					)
			}
	)
	public ResponseEntity<List<User>> findAll() {
		List<User> response = service.findAll();

		return ResponseEntity.ok().body(response);
	}

}
