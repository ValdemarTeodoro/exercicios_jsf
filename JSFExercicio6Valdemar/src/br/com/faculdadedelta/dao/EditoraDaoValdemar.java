package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.EditoraValdemar;
import br.com.faculdadedelta.util.Conexao;

public class EditoraDaoValdemar {

	public void incluir(EditoraValdemar editora) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO editoras(desc_editora) VALUES (?)";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, editora.getEditora().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(EditoraValdemar editora) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE editoras SET desc_editora=? WHERE id_editora=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, editora.getEditora().trim());
			ps.setLong(2, editora.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(EditoraValdemar editora) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM editoras WHERE id_editora=?";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setLong(1, editora.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<EditoraValdemar> lista() throws ClassNotFoundException, SQLException{
		List<EditoraValdemar> listaRetono = new ArrayList<>();
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_editora, desc_editora FROM editoras";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				EditoraValdemar editora = new EditoraValdemar();
				editora.setId(rs.getLong("id_editora"));
				editora.setEditora(rs.getString("desc_editora").trim());
				listaRetono.add(editora);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetono;
	}
	
	public EditoraValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_editora, desc_editora FROM editoras WHERE id_editora=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		EditoraValdemar retorno = new EditoraValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_editora"));
				retorno.setEditora(rs.getString("desc_editora").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
}
