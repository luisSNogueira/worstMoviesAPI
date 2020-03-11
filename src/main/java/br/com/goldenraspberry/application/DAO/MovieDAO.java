package br.com.goldenraspberry.application.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.Movie;

@Repository
public class MovieDAO implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long insert(DTO dto) throws Exception {
		Movie movie = (Movie)dto;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into movie (year, title, winner) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, movie.getYear());
			ps.setString(2, movie.getTitle());
			ps.setBoolean(3, movie.isWinner());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Override
	public DTO getById(Long id) throws Exception {
		return jdbcTemplate.query("select * from movie where id=?", new Object[] {
			id	
		}, new ResultSetExtractor<Movie>() {

			@Override
			public Movie extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? new Movie(rs.getString("YEAR"), rs.getString("TITLE"), rs.getBoolean("WINNER")) : null;
			}
			
		});
	}
	
	public int delete(Long id) throws Exception {
		return jdbcTemplate.update("delete from movie where id=?", new Object[] {
			id	
		});
	}

}
