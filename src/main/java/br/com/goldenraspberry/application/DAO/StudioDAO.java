package br.com.goldenraspberry.application.DAO;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.Studio;

@Repository
public class StudioDAO implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long insert(DTO dto) throws Exception {
		Studio studio = (Studio)dto;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into studio (name) values(?)");
			ps.setString(1, studio.getName());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}
	
	public DTO getByName(String name) throws Exception {
		return (DTO)jdbcTemplate.queryForObject("select * from studio where name=?", new Object[] {
				name
		}, new BeanPropertyRowMapper<Studio>(Studio.class));
	}

	@Override
	public DTO getById(Long id) throws Exception {
		return (DTO)jdbcTemplate.queryForObject("select * from studio where id=?", new Object[] {
				id
		}, new BeanPropertyRowMapper<Studio>(Studio.class));
	}

}
