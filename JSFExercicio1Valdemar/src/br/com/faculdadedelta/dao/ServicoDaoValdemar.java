package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ServicoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ServicoDaoValdemar {
	public void incluir(ServicoValdemar servicoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO servico (nome_servico, descricao_servico) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, servicoValdemar.getNomeServico().trim());
			ps.setString(2, servicoValdemar.getDescricaoServico().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(ServicoValdemar servicoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE servico SET nome_servico=?, descricao_servico=? WHERE id_servico=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, servicoValdemar.getNomeServico().trim());
			ps.setString(2, servicoValdemar.getDescricaoServico().trim());
			ps.setLong(3, servicoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(ServicoValdemar servicoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM servico WHERE id_servico=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, servicoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<ServicoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<ServicoValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_servico, nome_servico, descricao_servico FROM servico";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ServicoValdemar servicoValdemar = new ServicoValdemar();
				servicoValdemar.setId(rs.getLong("id_servico"));
				servicoValdemar.setNomeServico(rs.getString("nome_servico").trim());
				servicoValdemar.setDescricaoServico(rs.getString("descricao_servico"));
				listaRetorno.add(servicoValdemar);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
		
		return listaRetorno;
	}
	public ServicoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_servico, nome_servico, descricao_servico FROM servico WHERE id_servico=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicoValdemar retorno = new ServicoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id_servico"));
				retorno.setNomeServico(rs.getString("nome_servico").trim());
				retorno.setDescricaoServico(rs.getString("descricao_servico").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
		return retorno;
	}
}
