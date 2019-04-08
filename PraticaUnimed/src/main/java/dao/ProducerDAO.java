package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.ProducerDTO;
import entities.Producer;

public class ProducerDAO {

	public boolean save(Producer producer) {

		Connection conexao = Conexao.obterConexao();
		CallableStatement myStmt;

		try {
			myStmt = conexao.prepareCall("insert into producer (producer_pk,name) values (?,?)");
			myStmt.setInt(1, producer.getId());
			myStmt.setString(2, producer.getName());
			myStmt.execute();
			System.out.println("Inserção realizada com sucesso!");
			conexao.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<ProducerDTO> listProducer() {
		Connection conexao = Conexao.obterConexao();
		ArrayList<ProducerDTO> producers = new ArrayList<ProducerDTO>();
		Statement myStmt;
		ProducerDTO producerDTO;
		try {
			myStmt = conexao.createStatement();
			String query = "select name, previous, following, interval from (select name, min(year) as previous, max(year) as following, (max(year) - min(year)) as interval\r\n" + 
					"from producer \r\n" + 
					"inner join movie_producer on producer_pk = producer_fk\r\n" + 
					"inner join movie on movie_fk = movie_pk\r\n" + 
					"where winner = 'Y'\r\n" + 
					"group by name\r\n" + 
					"having count(name) > 1)\r\n" + 
					"where interval = (select max(interval)  from (select name, min(year) as previous, max(year) as following, (max(year) - min(year)) as interval\r\n" + 
					"from producer \r\n" + 
					"inner join movie_producer on producer_pk = producer_fk\r\n" + 
					"inner join movie on movie_fk = movie_pk\r\n" + 
					"where winner = 'Y'\r\n" + 
					"group by name\r\n" + 
					"having count(name) > 1))\r\n" + 
					"group by name \r\n" + 
					"\r\n" + 
					"union all \r\n" + 
					"\r\n" + 
					"select name, previous, following, interval from (select name, min(year) as previous, max(year) as following, (max(year) - min(year)) as interval\r\n" + 
					"from producer \r\n" + 
					"inner join movie_producer on producer_pk = producer_fk\r\n" + 
					"inner join movie on movie_fk = movie_pk\r\n" + 
					"where winner = 'Y'\r\n" + 
					"group by name\r\n" + 
					"having count(name) > 1)\r\n" + 
					"where  rownum = 1 and interval = (select min(interval)  from (select name, min(year) as previous, max(year) as following, (max(year) - min(year)) as interval\r\n" + 
					"from producer \r\n" + 
					"inner join movie_producer on producer_pk = producer_fk\r\n" + 
					"inner join movie on movie_fk = movie_pk\r\n" + 
					"where winner = 'Y'\r\n" + 
					"group by name\r\n" + 
					"having count(name) > 1))\r\n" + 
					"group by name ";
			ResultSet resultSet = myStmt.executeQuery(query);
			while (resultSet.next()) {
				producerDTO = new ProducerDTO(resultSet.getString(1),resultSet.getInt(4),resultSet.getInt(2),resultSet.getInt(3));
				producers.add(producerDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producers;
	}

}
