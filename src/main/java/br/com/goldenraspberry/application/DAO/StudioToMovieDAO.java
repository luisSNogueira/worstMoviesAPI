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
import br.com.goldenraspberry.application.model.Studio;
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
	
	public List<Studio> getStudiosByMovieId(Long movieId) throws Exception {
		return jdbcTemplate.query("select s.id, s.name from studiotomovie stm " + 
				"inner join studio s " + 
				"on stm.studioid = s.id " + 
				"where stm.movieid = ?", new Object[] {
						movieId
				}, new ResultSetExtractor<List<Studio>>() {

					@Override
					public List<Studio> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Studio> result = new ArrayList<Studio>();
						while(rs.next()) {
							result.add(new Studio(rs.getLong("ID"), rs.getString("NAME")));
						}
						return result;
					}
			
		});
	}
	
	public int delete(Long id) throws Exception {
		return jdbcTemplate.update("delete from studiotomovie where movieid = ?", new Object[] {
				id
		});
	}

}
