package banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.modelo.Autor;

public class AutorDao implements Dao<Autor> {
	
	private static final String GET_BY_ID = "SELECT * FROM autores WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM autores";
	private static final String INSERT = "INSERT INTO autores (nome, cpf) "
			+ "VALUES (?, ?)";
	private static final String UPDATE = "UPDATE autores SET nome = ?, cpf = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM autores WHERE id = ?";
	
	public AutorDao() {
		try {
			createTable();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar tabela no banco.", e);
			//e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS autores"
	            + "  (id INTEGER PRIMARY KEY,"
	            + "   nome VARCHAR(50),"
	            + "   cpf LONGINT)";
	    
	    Connection conn = DbConnection.getConnection();


	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	    
	    close(conn, stmt, null);
	}
	
	
	private Autor getAutorFromRS(ResultSet rs) throws SQLException {
		Autor autor = new Autor();
			
		autor.setId( rs.getInt("id") );
		autor.setNome( rs.getString("nome") );
		autor.setCpf( rs.getLong("cpf") );
	
		return autor;
    }
	
	@Override
	public Autor getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		
		Autor autor = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				autor = getAutorFromRS(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter autor pela chave.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return autor;
	}

	@Override
	public List<Autor> getAll() {
		Connection conn = DbConnection.getConnection();
		
		List<Autor> autores = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				autores.add(getAutorFromRS(rs));
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter todos os autores.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return autores;
	}

	@Override
	public void insert(Autor autor) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, autor.getNome());
			stmt.setLong(2, autor.getCpf());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				autor.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir autor.", e);
		}finally {
			close(conn, stmt, rs);
		}

	}

	@Override
	public void delete(int id) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(DELETE);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover autor.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Autor autor) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, autor.getNome());
			stmt.setLong(2, autor.getCpf());
			stmt.setInt(3, autor.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar autor.", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}
		
	}

}
