package br.com.goldenraspberry.application.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.ProducerToMovie;

@Repository
public class ProducerToMovieDAO implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long insert(DTO dto) throws Exception {
		ProducerToMovie producerToMovie = (ProducerToMovie)dto;
		return (long) jdbcTemplate.update("insert into producertomovie (movieid, producerid) values (?,?)", new Object[] {
			producerToMovie.getMovieId(), producerToMovie.getProducerId()
		});
	}

	@Override
	public DTO getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
