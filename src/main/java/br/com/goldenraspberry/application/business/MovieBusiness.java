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
import br.com.goldenraspberry.application.request.MovieJsonRequest;
import br.com.goldenraspberry.application.request.MovieJsonResponse;

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
	
	private boolean validateMovieData(MovieJsonRequest movieJsonRequest) {
		if(movieJsonRequest.getTitle() == null || movieJsonRequest.getTitle().equals("")) {
			erros.add("o campo title não pode ser nulo.");
		}
		if(movieJsonRequest.getYear() == null || movieJsonRequest.getYear().equals("")) {
			erros.add("o campo year não pode ser nulo.");
		}
		if(movieJsonRequest.getStudios().isEmpty() || movieJsonRequest.getStudios().get(0).equals("")) {
			erros.add("o campo studios deve conter ao menos um registro.");
		}
		if(movieJsonRequest.getProducers().isEmpty() || movieJsonRequest.getProducers().get(0).equals("")) {
			erros.add("o campo producers deve conter ao menos um registro.");
		}
		if(erros.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public MovieJsonResponse insertMovie(MovieJsonRequest movieJsonRequest) throws Exception {
		erros = new ArrayList<String>();
		MovieJsonResponse movieJsonResponse = null;
		if(validateMovieData(movieJsonRequest)) {
			Movie movie = new Movie(movieJsonRequest.getYear(), movieJsonRequest.getTitle(), movieJsonRequest.isWinner());
			movie.setId(movieDAO.insert(movie));
			for(String studioName : movieJsonRequest.getStudios()) {
				Studio studio = getStudioByName(studioName);
				if(studio == null) {
					studio = new Studio(studioName);
					Long studioId = studioDAO.insert(studio);
					studio.setId(studioId);
				}
				studioToMovieDAO.insert(new StudioToMovie(movie.getId(), studio.getId()));
			}
			for(String producerName : movieJsonRequest.getProducers()) {
				Producer producer = getProducerByName(producerName);
				if(producer == null) {
					producer = new Producer(producerName);
					Long producerId = producerDAO.insert(producer);
					producer.setId(producerId);
				}
				producerToMovieDAO.insert(new ProducerToMovie(movie.getId(), producer.getId()));
			}
			movieJsonResponse = createMovieJsonResponse(movie);
		} else {
			String msgErro = "Os seguintes erros ocorreram na validação dos dados: ";
			StringBuffer sb = new StringBuffer(msgErro);
			erros.forEach(temp -> sb.append(temp + " "));
			msgErro = sb.toString();
			throw new Exception(msgErro);
		}
		return movieJsonResponse;
	}
	
	public MovieJsonResponse deleteMovie(Long id) throws Exception {
		Movie movie = getMovieById(id);
		if(movie == null) {
			throw new Exception("Não foi encontrado registro com o id informado.");
		}
		studioToMovieDAO.delete(id);
		producerToMovieDAO.delete(id);
		movieDAO.delete(id);
		return createMovieJsonResponse(movie);
	}
	
	private Studio getStudioByName(String name) throws Exception {
		return (Studio)studioDAO.getByName(name);
	}
	
	private Producer getProducerByName(String name) throws Exception {
		return (Producer)producerDAO.getByName(name);
	}
	
	private Movie getMovieById(Long id) throws Exception {
		Movie movie = (Movie)movieDAO.getById(id);
		movie.setStudios(studioToMovieDAO.getStudiosByMovieId(movie.getId()));
		movie.setProducers(producerToMovieDAO.getProducersByMovieId(movie.getId()));
		return movie;
	}
	
	private MovieJsonResponse createMovieJsonResponse(Movie movie) {
		MovieJsonResponse response = new MovieJsonResponse();
		response.setYear(movie.getYear());
		response.setTitle(movie.getTitle());
		response.setWinner(movie.isWinner());
		List<String> studios = new ArrayList<String>();
		movie.getStudios().forEach(studio -> studios.add(studio.getName()));
		List<String> producers = new ArrayList<String>();
		movie.getProducers().forEach(producer -> producers.add(producer.getName()));
		return response;
	}

}
