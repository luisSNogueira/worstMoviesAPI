package br.com.goldenraspberry.application.DAO;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
			PreparedStatement ps = connection.prepareStatement("insert into movie (year, title, winner) values(?, ?, ?)");
			ps.setString(1, movie.getYear());
			ps.setString(2, movie.getTitle());
			ps.setBoolean(3, movie.isWinner());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Override
	public DTO getById(Long id) throws Exception {
		return (DTO)jdbcTemplate.queryForObject("select * from movie where id=?", new Object[] {
				id
		}, new BeanPropertyRowMapper<Movie>(Movie.class));
	}

}
