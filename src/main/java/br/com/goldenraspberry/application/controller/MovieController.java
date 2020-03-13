package br.com.goldenraspberry.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberry.application.business.MovieBusiness;
import br.com.goldenraspberry.application.request.MovieJsonRequest;
import br.com.goldenraspberry.application.request.MovieJsonResponse;

@RestController
public class MovieController {
	
	@Autowired
	private MovieBusiness movieBusiness;
	
	@PostMapping(value = "/movie")
	public ResponseEntity<?> insertMovie(@RequestBody MovieJsonRequest movieJsonRequest) {
		try {
			MovieJsonResponse result = movieBusiness.insertMovie(movieJsonRequest);
			return new ResponseEntity<MovieJsonResponse>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
		try {
			MovieJsonResponse result = movieBusiness.deleteMovie(id);
			return ResponseEntity.ok().body(result);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
