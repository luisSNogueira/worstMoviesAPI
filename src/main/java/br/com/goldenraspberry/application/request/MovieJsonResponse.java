package br.com.goldenraspberry.application.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "year", "title", "studios", "producers", "winner"})
public class MovieJsonResponse implements Serializable {
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 4148390417887834311L;

	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String year;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private boolean winner;
	
	@JsonProperty
	private List<String> studios;
	
	@JsonProperty
	private List<String> producers;

	public Long getId() {
		return id;
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

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

}
