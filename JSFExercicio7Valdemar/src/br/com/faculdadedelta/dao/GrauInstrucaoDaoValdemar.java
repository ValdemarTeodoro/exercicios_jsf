package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.GrauInstrucaoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class GrauInstrucaoDaoValdemar {

	public void incluir(GrauInstrucaoValdemar grauInstrucaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO graudeinstrucao(nome) VALUES (?)";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, grauInstrucaoValdemar.getGrauInstrucao().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(GrauInstrucaoValdemar grauInstrucaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE graudeinstrucao SET nome=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, grauInstrucaoValdemar.getGrauInstrucao().trim());
			ps.setLong(2, grauInstrucaoValdemar.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(GrauInstrucaoValdemar grauInstrucaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM graudeinstrucao WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setLong(1, grauInstrucaoValdemar.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<GrauInstrucaoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<GrauInstrucaoValdemar> listaRetono = new ArrayList<>();
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome FROM graudeinstrucao";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				GrauInstrucaoValdemar grauInstrucaoValdemar = new GrauInstrucaoValdemar();
				grauInstrucaoValdemar.setId(rs.getLong("id"));
				grauInstrucaoValdemar.setGrauInstrucao(rs.getString("nome".trim()));
				listaRetono.add(grauInstrucaoValdemar);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetono;
	}
	
	public GrauInstrucaoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome FROM graudeinstrucao WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		GrauInstrucaoValdemar retorno = new GrauInstrucaoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setGrauInstrucao(rs.getString("nome").trim());
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
