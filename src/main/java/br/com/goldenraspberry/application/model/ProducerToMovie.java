package br.com.goldenraspberry.application.model;

public class ProducerToMovie {
	
	private Long movieId;
	
	private Long producerId;

	
	public Long getProducerId() {
		return producerId;
	}

	public void setProducerId(Long producerId) {
		this.producerId = producerId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
