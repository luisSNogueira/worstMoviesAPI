package br.com.goldenraspberry.application.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.goldenraspberry.application.DAO.MovieDAO;
import br.com.goldenraspberry.application.DAO.ProducerDAO;
import br.com.goldenraspberry.application.DAO.ProducerToMovieDAO;
import br.com.goldenraspberry.application.DAO.StudioDAO;
import br.com.goldenraspberry.application.DAO.StudioToMovieDAO;
import br.com.goldenraspberry.application.model.Movie;
import br.com.goldenraspberry.application.model.Producer;
import br.com.goldenraspberry.application.model.ProducerToMovie;
import br.com.goldenraspberry.application.model.Studio;
import br.com.goldenraspberry.application.model.StudioToMovie;

@Service
@Transactional
public class MovieBusiness {
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private StudioDAO studioDAO;
	
	@Autowired
	private ProducerDAO producerDAO;
	
	@Autowired
	private StudioToMovieDAO studioToMovieDAO;
	
	@Autowired
	private ProducerToMovieDAO producerToMovieDAO;
	
	List<String> erros;
	
	private boolean validateMovieData(Movie movie) {
		if(movie.getTitle() == null || movie.getTitle().equals("")) {
			erros.add("o campo title não pode ser nulo.");
		}
		if(movie.getYear() == null || movie.getYear().equals("")) {
			erros.add("o campo year não pode ser nulo.");
		}
		if(movie.getStudios().isEmpty() || movie.getStudios().get(0).equals("")) {
			erros.add("o campo studios deve conter ao menos um registro.");
		}
		if(movie.getProducers().isEmpty() || movie.getProducers().get(0).equals("")) {
			erros.add("o campo producers deve conter ao menos um registro.");
		}
		if(erros.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public Movie insertMovie(Movie movie) throws Exception {
		erros = new ArrayList<String>();
		if(validateMovieData(movie)) {
			Long movieID = movieDAO.insert(movie);
			movie.setId(movieID);
			for(String studioName : movie.getStudios()) {
				Studio studio = getStudioByName(studioName);
				if(studio == null) {
					studio = new Studio(studioName);
					Long studioId = studioDAO.insert(studio);
					studio.setId(studioId);
				}
				studioToMovieDAO.insert(new StudioToMovie(movie.getId(), studio.getId()));
			}
			for(String producerName : movie.getProducers()) {
				Producer producer = getProducerByName(producerName);
				if(producer == null) {
					producer = new Producer(producerName);
					Long producerId = producerDAO.insert(producer);
					producer.setId(producerId);
				}
				producerToMovieDAO.insert(new ProducerToMovie(movie.getId(), producer.getId()));
			}
		} else {
			String msgErro = "Os seguintes erros ocorreram na validação dos dados: ";
			StringBuffer sb = new StringBuffer(msgErro);
			erros.forEach(temp -> sb.append(temp + " "));
			msgErro = sb.toString();
			throw new Exception(msgErro);
		}
		return movie;
	}
	
	public Movie deleteMovie(Long id) throws Exception {
		Movie movie = (Movie)movieDAO.getById(id);
		if(movie == null) {
			throw new Exception("Não foi encontrado registro com o id informado.");
		}
		movieDAO.delete(id);
		return movie;
	}
	
	private Studio getStudioByName(String name) throws Exception {
		return (Studio)studioDAO.getByName(name);
	}
	
	private Producer getProducerByName(String name) throws Exception {
		return (Producer)producerDAO.getByName(name);
	}

}
