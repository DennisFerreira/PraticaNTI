package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.MovieDAO;
import dto.YearsDTO;
import entities.Movie;

@RestController
@RequestMapping("/movies")
public class ControllerMovie {
	
	private MovieDAO movieDAO = new MovieDAO();
	
	@GetMapping
	public List<Movie> list(){
		return movieDAO.listMovie();
	}
	
	@GetMapping("/years")
	public List<YearsDTO> listYears(){
		return movieDAO.listYears();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable int id){
		boolean aux = false;
		for(Movie movie : movieDAO.listMovie()){
			if(movie.getId() == id) {
				aux = true;
				break;
			}
		}
		if(!aux) {
			if(movieDAO.deleteMovie(id)) 
				return ResponseEntity.ok().build();
			else 
				return ResponseEntity.notFound().build();
		}
		else 
			return ResponseEntity.notFound().build();
		
		
	}

}
