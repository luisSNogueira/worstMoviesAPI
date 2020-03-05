package br.com.goldenraspberry.application.model;

import java.util.List;

public class Producer implements DTO {
	
	private Long id;
	
	private String name;
	
	private List<ProducerToMovie> movies;
	
	public Producer() {
		
	}
	
	public Producer(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProducerToMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<ProducerToMovie> movies) {
		this.movies = movies;
	}

}
