package br.com.goldenraspberry.application.model;

import java.util.List;

public class Movie implements DTO {
	
	private Long id;
	
	private String year;
	
	private String title;
	
	private boolean winner;
	
	private List<Producer> producers;
	
	private List<Studio> studios;
	
	public Movie() {

	}
	
	public Movie(Long id, String year, String title, boolean winner) {
		this.id = id;
		this.year = year;
		this.title = title;
		this.winner = winner;
	}
	
	public Movie(String year, String title, boolean winner) {
		this.year = year;
		this.title = title;
		this.winner = winner;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public List<Studio> getStudios() {
		return studios;
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

}
