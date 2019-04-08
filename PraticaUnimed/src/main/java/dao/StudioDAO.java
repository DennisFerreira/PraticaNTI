package dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.StudioDTO;
import entities.Studio;

public class StudioDAO {
	
	
	
	public boolean save(Studio studio) {
		 Connection conexao = Conexao.obterConexao();
		 CallableStatement myStmt;
		


		try {
			myStmt = conexao.prepareCall("insert into studio (studio_pk,name) values (?,?)");
			myStmt.setInt(1, studio.getId());
			myStmt.setString(2, studio.getName());
			myStmt.execute();
			System.out.println("Inserção realizada com sucesso!");
			conexao.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public List<StudioDTO> listStudio() {
		Connection conexao = Conexao.obterConexao();
		ArrayList<StudioDTO> studios = new ArrayList<StudioDTO>();
		Statement myStmt;
		StudioDTO studioDTO;
		try {
			myStmt = conexao.createStatement();
			String query = "select  studio.name, count(*) as winnerCount from movie_studio inner join movie on movie_pk = movie_fk inner join studio on studio_pk = studio_fk where winner = 'Y' group by studio.name order by 2  desc;";
			ResultSet resultSet = myStmt.executeQuery(query);

			while (resultSet.next()) {
				studioDTO = new StudioDTO(resultSet.getString("name"),resultSet.getInt("winnerCount"));
				studios.add(studioDTO);
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studios;
	}

}
