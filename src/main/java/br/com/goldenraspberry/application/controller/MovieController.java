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
import br.com.goldenraspberry.application.model.Movie;

@RestController
public class MovieController {
	
	@Autowired
	private MovieBusiness movieBusiness;
	
	@PostMapping(value = "/movie")
	public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {
		try {
			Movie result = movieBusiness.insertMovie(movie);
			return new ResponseEntity<Movie>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
		try {
			Movie result = movieBusiness.deleteMovie(id);
			return ResponseEntity.ok().body(result);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
