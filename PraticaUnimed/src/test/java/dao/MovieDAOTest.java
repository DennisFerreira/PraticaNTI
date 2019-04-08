package dao;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import entities.Movie;
import main.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MovieDAOTest {
	
	private MovieDAO movieDAO = new MovieDAO();
	
	@Test
	public void testIdMovieDelete() {
		Movie movie = movieDAO.getById(6);
		assertThat(movie == null);
	}

}
