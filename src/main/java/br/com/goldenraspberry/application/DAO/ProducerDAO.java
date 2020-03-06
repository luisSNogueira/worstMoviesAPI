package br.com.goldenraspberry.application.DAO;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.goldenraspberry.application.model.DTO;
import br.com.goldenraspberry.application.model.Producer;

@Repository
public class ProducerDAO implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long insert(DTO dto) throws Exception {
		Producer producer = (Producer)dto;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into producer (name) values(?)");
			ps.setString(1, producer.getName());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Override
	public DTO getById(Long id) throws Exception {
		return (DTO)jdbcTemplate.queryForObject("select * from movie where id=?", new Object[] {
				id
		}, new BeanPropertyRowMapper<Producer>(Producer.class));
	}
	
	public DTO getByName(String name) throws Exception {
		return (DTO)jdbcTemplate.query("select * from movie where name=?", new Object[] {
				name
		}, new BeanPropertyRowMapper<Producer>(Producer.class));
	}

}
