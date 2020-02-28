package br.com.goldenraspberry.application.DAO;

import br.com.goldenraspberry.application.model.DTO;

public interface DAO {
	
	public Long insert(DTO dto) throws Exception;
	
	public DTO getById(Long id) throws Exception;

}
