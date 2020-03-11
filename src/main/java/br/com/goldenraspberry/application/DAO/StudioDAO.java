package br.com.goldenraspberry.application.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
			PreparedStatement ps = connection.prepareStatement("insert into studio (name) values(?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, studio.getName());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Override
	public DTO getById(Long id) throws Exception {
		return (DTO)jdbcTemplate.query("select * from studio where id=?", new Object[] {
				id
		}, new BeanPropertyRowMapper<Studio>(Studio.class));
	}
	
	public DTO getByName(String name) throws Exception {
		return jdbcTemplate.query("select * from studio where name=?", new Object[] {
			name	
		}, new ResultSetExtractor<Studio>() {

			@Override
			public Studio extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? new Studio(rs.getLong("ID"), rs.getString("NAME")) : null;
			}
			
		});
	}

}
