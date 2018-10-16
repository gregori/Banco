package banco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	public static final String URL = "jdbc:sqlite:banco.db";
	
	private static Connection conn = null;
	
	/**
	 * Obtém uma conexão com o banco
	 * @return objeto Connection
	 */
	public static Connection getConnection() {
		
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(URL);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro Conectando ao banco de dados", ex);
		}
	
		return conn;
	}
}
