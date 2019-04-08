package main;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import dao.MovieDAO;
import dao.ProducerDAO;
import dao.StudioDAO;
import entities.Movie;
import entities.Producer;
import entities.Studio;

public class ReadFile {

	public static void main(String[] args) throws IOException {
		StudioDAO studioDAO = new StudioDAO();
		ProducerDAO producerDAO = new ProducerDAO();
		MovieDAO movieDAO = new MovieDAO();

		for (Studio studio : getStudio()) {
			studioDAO.save(studio);
		}
		for (Producer producer : getProducers()) {
			producerDAO.save(producer);
		}
		for (Movie movie : getMovies()) {
			movieDAO.save(movie);
		}
		
	}

	public static ArrayList<Producer> getProducers() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/movies.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		ArrayList<Producer> producers = new ArrayList<Producer>();
		Producer producer;
		Map<Integer, String> producersMap = new HashMap<Integer, String>();
		List<String[]> lines = csvReader.readAll();

		for (String[] line : lines) {
			if (producersMap.get(Integer.parseInt(line[4])) == null)
				producersMap.put(Integer.parseInt(line[4]), line[5]);
			if (producersMap.get(Integer.parseInt(line[6])) == null)
				producersMap.put(Integer.parseInt(line[6]), line[7]);
			if (producersMap.get(Integer.parseInt(line[8])) == null)
				producersMap.put(Integer.parseInt(line[8]), line[9]);
			if (!line[10].trim().equals("") && producersMap.get(Integer.parseInt(line[10])) == null)
				producersMap.put(Integer.parseInt(line[10]), line[11]);

		}

		for (Integer key : producersMap.keySet()) {
			producer = new Producer();
			producer.setId(key);
			producer.setName(producersMap.get(key));
			System.out.println(producer.toString());
			producers.add(producer);
		}

		return producers;

	}

	public static ArrayList<Studio> getStudio() throws IOException {

		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/movies.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		ArrayList<Studio> studios = new ArrayList<Studio>();
		Studio studio;
		Map<Integer, String> studiosMap = new HashMap<Integer, String>();
		List<String[]> lines = csvReader.readAll();

		for (String[] line : lines) {
			if (studiosMap.get(Integer.parseInt(line[12])) == null)
				studiosMap.put(Integer.parseInt(line[12]), line[13]);
			if (!line[14].trim().equals("") && studiosMap.get(Integer.parseInt(line[14])) == null)
				studiosMap.put(Integer.parseInt(line[14]), line[15]);
			if (!line[16].trim().equals("") && studiosMap.get(Integer.parseInt(line[16])) == null)
				studiosMap.put(Integer.parseInt(line[16]), line[17]);

		}

		for (Integer key : studiosMap.keySet()) {
			studio = new Studio();
			studio.setId(key);
			studio.setName(studiosMap.get(key));
			System.out.println(studio.toString());
			studios.add(studio);
		}

		return studios;

	}

	public static ArrayList<Movie> getMovies() throws IOException {

		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/movies.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		ArrayList<Studio> studios;
		ArrayList<Producer> producers;
		Movie movie;
		List<String[]> lines = csvReader.readAll();

		for (String[] line : lines) {
			movie = new Movie();
			producers = new ArrayList<Producer>();
			studios = new ArrayList<Studio>();
			movie.setId(Integer.parseInt(line[0]));
			movie.setTitle(line[1]);
			movie.setYear(Integer.parseInt(line[2]));
			movie.setWinner(line[3].equals("Y") ? true : false);
			producers.add(new Producer(Integer.parseInt(line[4]), line[5]));
			producers.add(new Producer(Integer.parseInt(line[6]), line[7]));
			producers.add(new Producer(Integer.parseInt(line[8]), line[9]));
			if (!line[10].trim().equals(""))
				producers.add(new Producer(Integer.parseInt(line[10]), line[11]));
			studios.add(new Studio(Integer.parseInt(line[12]), line[13]));
			if (!line[14].trim().equals(""))
				studios.add(new Studio(Integer.parseInt(line[14]), line[15]));
			if (!line[16].trim().equals(""))
				studios.add(new Studio(Integer.parseInt(line[16]), line[17]));
			movie.setProducers(producers);
			movie.setStudios(studios);
			System.out.println(movie.toString());
			movies.add(movie);

		}

		return movies;

	}

}
