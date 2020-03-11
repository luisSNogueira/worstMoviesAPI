package br.com.goldenraspberry.application.model;

import java.util.List;

public class Studio implements DTO {
	
	private Long id;
	
	private String name;
	
	private List<StudioToMovie> movies;
	
	public Studio() {
		
	}
	
	public Studio(String name) {
		this.name = name;
	}
	
	public Studio(Long id, String name) {
		this.id = id;
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

	public List<StudioToMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<StudioToMovie> movies) {
		this.movies = movies;
	}

}
