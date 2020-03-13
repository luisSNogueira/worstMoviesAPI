package br.com.goldenraspberry.application.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"year", "title", "studios", "producers", "winner"})
public class MovieJsonRequest implements Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1271235531324967980L;
	
	@JsonProperty
	private String year;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private List<String> studios;
	
	@JsonProperty
	private List<String> producers;
	
	@JsonProperty
	private boolean winner;

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

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
