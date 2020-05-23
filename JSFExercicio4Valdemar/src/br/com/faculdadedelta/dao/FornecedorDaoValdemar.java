package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.FornecedorValdemar;
import br.com.faculdadedelta.util.Conexao;

public class FornecedorDaoValdemar {
	public void incluir(FornecedorValdemar fornecedor) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO fornecedores(desc_fornecedor) VALUES (?)";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, fornecedor.getFornecedor().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(FornecedorValdemar fornecedor) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE fornecedores SET desc_fornecedor=? WHERE id_fornecedor=?";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, fornecedor.getFornecedor().trim());
			ps.setLong(2, fornecedor.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(FornecedorValdemar fornecedor) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM fornecedores WHERE id_fornecedor=?";
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setLong(1, fornecedor.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<FornecedorValdemar> lista() throws ClassNotFoundException, SQLException{
		List<FornecedorValdemar> listaRetono = new ArrayList<>();
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_fornecedor, desc_fornecedor FROM fornecedores";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				FornecedorValdemar fornecedor = new FornecedorValdemar();
				fornecedor.setId(rs.getLong("id_fornecedor"));
				fornecedor.setFornecedor(rs.getString("desc_fornecedor").trim());
				
				listaRetono.add(fornecedor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetono;
	}
	
	public FornecedorValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_fornecedor, desc_fornecedor FROM fornecedores WHERE id_fornecedor=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		FornecedorValdemar retorno = new FornecedorValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_fornecedor"));
				retorno.setFornecedor(rs.getString("desc_fornecedor").trim());
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
