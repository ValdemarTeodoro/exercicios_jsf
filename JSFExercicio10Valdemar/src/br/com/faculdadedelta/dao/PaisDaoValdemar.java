package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.PaisValdemar;
import br.com.faculdadedelta.util.Conexao;

public class PaisDaoValdemar {
	public void incluir(PaisValdemar pais) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO pais(nome_pais, codigo_pais) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pais.getNomePais().trim());
			ps.setString(2, pais.getCodigoPais().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(PaisValdemar pais) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE pais SET nome_pais=?, codigo_pais=? WHERE id_pais=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pais.getNomePais().trim());
			ps.setString(2, pais.getCodigoPais().trim());
			ps.setLong(3, pais.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(PaisValdemar pais) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM pais WHERE id_pais=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, pais.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<PaisValdemar> lista() throws ClassNotFoundException, SQLException{
		List<PaisValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_pais, nome_pais, codigo_pais FROM pais";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				PaisValdemar pais = new PaisValdemar();
				pais.setId(rs.getLong("id_pais"));
				pais.setNomePais(rs.getString("nome_pais").trim());
				pais.setCodigoPais(rs.getString("codigo_pais").trim());
				listaRetorno.add(pais);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public PaisValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_pais, nome_pais, codigo_pais FROM pais WHERE id_pais=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		PaisValdemar retrono = new PaisValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retrono.setId(rs.getLong("id_pais"));
				retrono.setNomePais(rs.getString("nome_pais").trim());
				retrono.setCodigoPais(rs.getString("codigo_pais").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retrono;
		
	}
}
