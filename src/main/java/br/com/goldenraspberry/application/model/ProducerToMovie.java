package br.com.goldenraspberry.application.model;

public class ProducerToMovie implements DTO {
	
	private Long movieId;
	
	private Long producerId;
	
	public ProducerToMovie() {
		
	}
	
	public ProducerToMovie(Long movieId, Long producerId) {
		this.movieId = movieId;
		this.producerId = producerId;
	}

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

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
