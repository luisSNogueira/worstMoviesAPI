package br.com.goldenraspberry.application.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.StudioToMovie;

@Repository
public class StudioToMovieDAO implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long insert(DTO dto) throws Exception {
		StudioToMovie studioToMovie = (StudioToMovie)dto;
		return (long) jdbcTemplate.update("insert into studiotomovie (movieid, studioid) values (?,?)", new Object[] {
				studioToMovie.getMovieId(), studioToMovie.getStudioId()
		});
	}

	@Override
	public DTO getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
