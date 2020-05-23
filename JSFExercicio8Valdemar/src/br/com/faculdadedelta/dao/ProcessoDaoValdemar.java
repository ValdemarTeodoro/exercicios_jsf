package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ProcessoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ProcessoDaoValdemar {

	public void incluir(ProcessoValdemar processo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO processos(numero_processo, descricao_processo, valor_processo) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, processo.getNumeroProcesso().trim());
			ps.setString(2, processo.getDescricaoProcesso().trim());
			ps.setDouble(3, processo.getValorProcesso());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(ProcessoValdemar processo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE processos SET numero_processo=?, descricao_processo=?, valor_processo=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, processo.getNumeroProcesso().trim());
			ps.setString(2, processo.getDescricaoProcesso().trim());
			ps.setDouble(3, processo.getValorProcesso());
			ps.setLong(4, processo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(ProcessoValdemar processo) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM processos WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, processo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<ProcessoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<ProcessoValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, numero_processo, descricao_processo, valor_processo FROM processos";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProcessoValdemar processo = new ProcessoValdemar();
				processo.setId(rs.getLong("id"));
				processo.setNumeroProcesso(rs.getString("numero_processo").trim());
				processo.setDescricaoProcesso(rs.getString("descricao_processo").trim());
				processo.setValorProcesso(rs.getDouble("valor_processo"));
				
				listaRetorno.add(processo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public ProcessoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, numero_processo, descricao_processo, valor_processo FROM processos WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProcessoValdemar retorno = new ProcessoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNumeroProcesso(rs.getString("numero_processo"));
				retorno.setDescricaoProcesso(rs.getString("descricao_processo"));
				retorno.setValorProcesso(rs.getDouble("valor_processo"));
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
