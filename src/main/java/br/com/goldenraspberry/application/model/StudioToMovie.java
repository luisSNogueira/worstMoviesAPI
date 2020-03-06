package br.com.goldenraspberry.application.model;

public class StudioToMovie implements DTO {
	
	private Long movieId;
	
	private Long studioId;
	
	public StudioToMovie() {
		
	}
	
	public StudioToMovie(Long movieId, Long studioId) {
		this.movieId = movieId;
		this.studioId = studioId;
	}
 
	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
