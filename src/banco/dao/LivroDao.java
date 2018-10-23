package banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.modelo.Livro;

import banco.dao.AutorDao;

public class LivroDao implements Dao<Livro> {
	
	private AutorDao autorDao;
	
	private static final String GET_BY_ID = "SELECT * FROM livros WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM livros";
	private static final String INSERT = "INSERT INTO livros (titulo, anoPublicacao, editora, autor) "
			+ "VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE livros SET titulo = ?, anoPublicacao = ?, editora = ?, "
			+ "autor = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM livros WHERE id = ?";
	
	public LivroDao() {
		try {
			createTable();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar tabela no banco.", e);
			//e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS livros"
	            + "  (id INTEGER,"
	            + "   titulo VARCHAR(50),"
	            + "   anoPublicacao INTEGER,"
	            + "   editora VARCHAR(50),"
	            + "   autor INTEGER,";
	    
	    Connection conn = DbConnection.getConnection();


	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	    
	    close(conn, stmt, null);
	}
	
	
	private Livro getLivroFromRS(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
			
		livro.setId( rs.getInt("id") );
		livro.setTitulo( rs.getString("titulo") );
		livro.setAnoPublicacao( rs.getInt("anoPublicacao") );
		livro.setEditora( rs.getString("editora") );
		livro.setAutor( autorDao.getByKey(rs.getInt("autor")));
	
		return livro;
    }
	
	@Override
	public Livro getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		
		Livro livro = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				livro = getLivroFromRS(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter livro pela chave.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return livro;
	}

	@Override
	public List<Livro> getAll() {
		Connection conn = DbConnection.getConnection();
		
		List<Livro> livros = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				livros.add(getLivroFromRS(rs));
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter todos os livros.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return livros;
	}

	@Override
	public void insert(Livro livro) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getAnoPublicacao());
			stmt.setString(3, livro.getEditora());
			stmt.setInt(4, livro.getAutor().getId());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				livro.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir livro.", e);
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
			throw new RuntimeException("Erro ao remover livro.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Livro livro) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getAnoPublicacao());
			stmt.setString(3, livro.getEditora());
			stmt.setInt(4, livro.getAutor().getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar livro.", e);
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
