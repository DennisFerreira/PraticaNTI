package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection obterConexao() {

		Connection conexao = null;

		try {

			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:~/test";
			String username = "sa";
			String password = "";
			conexao = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return conexao;

	}

}
