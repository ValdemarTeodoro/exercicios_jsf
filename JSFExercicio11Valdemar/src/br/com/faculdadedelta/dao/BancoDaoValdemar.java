package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BancoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class BancoDaoValdemar {
	public void incluir(BancoValdemar bancoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO bancos(nome_banco, codgio_banco, cnpj_banco) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bancoValdemar.getNomeBanco().trim());
			ps.setString(2, bancoValdemar.getCodgioBanco().trim());
			ps.setString(3, bancoValdemar.getCnpjBanco().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(BancoValdemar bancoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bancos SET nome_banco=?, codgio_banco=?, cnpj_banco=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bancoValdemar.getNomeBanco().trim());
			ps.setString(2, bancoValdemar.getCodgioBanco().trim());
			ps.setString(3, bancoValdemar.getCnpjBanco().trim());
			ps.setLong(4, bancoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(BancoValdemar bancoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM bancos WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, bancoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<BancoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<BancoValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome_banco, codgio_banco, cnpj_banco FROM bancos";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BancoValdemar banco = new BancoValdemar();
				banco.setId(rs.getLong("id"));
				banco.setNomeBanco(rs.getString("nome_banco").trim());
				banco.setCodgioBanco(rs.getString("codgio_banco").trim());
				banco.setCnpjBanco(rs.getString("cnpj_banco").trim());
				
				listaRetorno.add(banco);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		
		return listaRetorno;
	}
	public BancoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome_banco, codgio_banco, cnpj_banco FROM bancos WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		BancoValdemar retorno = new BancoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNomeBanco(rs.getString("nome_banco").trim());
				retorno.setCodgioBanco(rs.getString("codgio_banco").trim());
				retorno.setCnpjBanco(rs.getString("cnpj_banco").trim());
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
