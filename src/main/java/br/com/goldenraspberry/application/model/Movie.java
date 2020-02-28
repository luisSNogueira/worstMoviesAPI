package br.com.goldenraspberry.application.model;

import java.util.List;

public class Movie implements DTO {
	
	private Long id;
	
	private String year;
	
	private String title;
	
	private boolean winner;
	
	private List<ProducerToMovie> producers;
	
	private List<StudioToMovie> studios;

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

	public List<ProducerToMovie> getProducers() {
		return producers;
	}

	public void setProducers(List<ProducerToMovie> producers) {
		this.producers = producers;
	}

	public List<StudioToMovie> getStudios() {
		return studios;
	}

	public void setStudios(List<StudioToMovie> studios) {
		this.studios = studios;
	}

}
