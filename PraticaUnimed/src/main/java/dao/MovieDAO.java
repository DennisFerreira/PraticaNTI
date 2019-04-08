package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.YearsDTO;
import entities.Movie;
import entities.Producer;
import entities.Studio;

public class MovieDAO {
	
	
	
	public boolean save(Movie movie) {
		Connection conexao = Conexao.obterConexao();
		CallableStatement myStmt;


		try {
			myStmt = conexao.prepareCall("insert into movie (movie_pk,title,year,winner) values (?,?,?,?);");
			myStmt.setInt(1, movie.getId());
			myStmt.setString(2, movie.getTitle());
			myStmt.setInt(3, movie.getYear());
			myStmt.setString(4, movie.isWinner() ? "Y" : "N");
			myStmt.execute();
			for(Producer producer : movie.getProducers()) {
				myStmt = conexao.prepareCall("insert into movie_producer (movie_fk,producer_fk) values (?,?);");
				myStmt.setInt(1, movie.getId());
				myStmt.setInt(2, producer.getId());
				myStmt.execute();
			}
			for(Studio studio : movie.getStudios()) {
				myStmt = conexao.prepareCall("insert into movie_studio (movie_fk,studio_fk) values (?,?);");
				myStmt.setInt(1, movie.getId());
				myStmt.setInt(2, studio.getId());
				myStmt.execute();
			}
			
			System.out.println("Inserção realizada com sucesso!");
			conexao.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		

	}
	
	public List<Movie> listMovie(){
		Connection conexao = Conexao.obterConexao();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		ArrayList<Studio> studios = null;
		ArrayList<Producer> producers = null;
		Statement myStmt;
		Movie movie = null;
		Studio studio;
		Producer producer;
		try {
			myStmt = conexao.createStatement();
			String query = "select * from movie where winner = 'Y';";
			String query1;
			String query2;
			ResultSet resultSet = myStmt.executeQuery(query);
			while (resultSet.next()) {
				movie = new Movie();
				studios = new ArrayList<Studio>();
				producers = new ArrayList<Producer>();
				movie.setId(resultSet.getInt(1));
				movie.setTitle(resultSet.getString(2));
				movie.setYear(resultSet.getInt(3));
				movie.setWinner(resultSet.getString(4).equals("Y") ? true : false);
				
				myStmt = conexao.createStatement();
				query1 = "select studio_pk, name from studio inner join movie_studio on studio_pk = studio_fk where movie_fk = "+ String.valueOf(movie.getId()) +";";
				ResultSet resultSet1 = myStmt.executeQuery(query1);
				while(resultSet1.next()) {
					studio = new Studio(resultSet1.getInt(1),resultSet1.getString(2));
					studios.add(studio);
				}
				myStmt = conexao.createStatement();
				query2 = "select producer_pk, name from producer inner join movie_producer on producer_pk = producer_fk where movie_fk = "+ String.valueOf(movie.getId()) +";";
				ResultSet resultSet2 = myStmt.executeQuery(query2);
				while(resultSet2.next()) {
					producer = new Producer(resultSet2.getInt(1),resultSet2.getString(2));
					producers.add(producer);
				}
				movie.setProducers(producers);
				movie.setStudios(studios);
				movies.add(movie);
				
			}
			
		conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return movies;
	}
	
	public List<YearsDTO> listYears(){
		
		Connection conexao = Conexao.obterConexao();
		ArrayList<YearsDTO> years = new ArrayList<YearsDTO>();
		Statement myStmt;
		YearsDTO movieDTO;
		try {
			myStmt = conexao.createStatement();
			String query = "select year, count(year) as winnerCount from movie where winner = 'Y'  group by year having count(year) > 1 ;";
			ResultSet resultSet = myStmt.executeQuery(query);

			while (resultSet.next()) {
				movieDTO = new YearsDTO(resultSet.getInt(1),resultSet.getInt(2));
				years.add(movieDTO);
			}
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return years;
		
	}
	
	public boolean deleteMovie(int movieId) {
		 Connection conexao = Conexao.obterConexao();
		 CallableStatement myStmt;
		 try {
			myStmt = conexao.prepareCall("delete from movie_studio where movie_fk = ?");
			myStmt.setInt(1, movieId);
			myStmt.execute();
			myStmt = conexao.prepareCall("delete from movie_producer where movie_fk = ?");
			myStmt.setInt(1, movieId);
			myStmt.execute();
			myStmt = conexao.prepareCall("delete from movie where movie_pk = ?");
			myStmt.setInt(1, movieId);
			myStmt.execute();
			
			conexao.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Movie getById(int movieId) {
		Connection conexao = Conexao.obterConexao();
		Statement myStmt;
		Movie movie = null;
		Studio studio;
		Producer producer;
		ArrayList<Studio> studios = null;
		ArrayList<Producer> producers = null;
		try {
			myStmt = conexao.createStatement();
			String query = "select * from movie where movie_pk = "+movieId+";";
			String query1;
			String query2;
			ResultSet resultSet = myStmt.executeQuery(query);
			while (resultSet.next()) {
				movie = new Movie();
				studios = new ArrayList<Studio>();
				producers = new ArrayList<Producer>();
				movie.setId(resultSet.getInt(1));
				movie.setTitle(resultSet.getString(2));
				movie.setYear(resultSet.getInt(3));
				movie.setWinner(resultSet.getString(4).equals("Y") ? true : false);
				
				myStmt = conexao.createStatement();
				query1 = "select studio_pk, name from studio inner join movie_studio on studio_pk = studio_fk where movie_fk = "+ String.valueOf(movie.getId()) +";";
				ResultSet resultSet1 = myStmt.executeQuery(query1);
				while(resultSet1.next()) {
					studio = new Studio(resultSet1.getInt(1),resultSet1.getString(2));
					studios.add(studio);
				}
				myStmt = conexao.createStatement();
				query2 = "select producer_pk, name from producer inner join movie_producer on producer_pk = producer_fk where movie_fk = "+ String.valueOf(movie.getId()) +";";
				ResultSet resultSet2 = myStmt.executeQuery(query2);
				while(resultSet2.next()) {
					producer = new Producer(resultSet2.getInt(1),resultSet2.getString(2));
					producers.add(producer);
				}
				movie.setProducers(producers);
				movie.setStudios(studios);
				conexao.close();
			}
			
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return movie;
	}
	
	
	

}
