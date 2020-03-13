package br.com.goldenraspberry.application.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.Producer;
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
	
	public List<Producer> getProducersByMovieId(Long movieId) throws Exception {
		return jdbcTemplate.query("select p.id, p.name from producertomovie ptm " + 
				"inner join producer p " + 
				"on ptm.producerid = p.id " + 
				"where ptm.movieid = ?", new Object[] {
						movieId
				}, new ResultSetExtractor<List<Producer>>() {

					@Override
					public List<Producer> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Producer> result = new ArrayList<Producer>();
						while(rs.next()) {
							result.add(new Producer(rs.getLong("ID"), rs.getString("NAME")));
						}
						return result;
					}
			
		});
	}
	
	public int delete(Long id) throws Exception {
		return jdbcTemplate.update("delete from producertomovie where movieid = ?", new Object[] {
				id
		});
	}

}
