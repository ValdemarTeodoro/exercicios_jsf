package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InfracaoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class InfracaoDaoValdemar {
	public void incluir(InfracaoValdemar infracaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO infracoes(gravidade_infracao, descricao_infracao, valor_infracao) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, infracaoValdemar.getGravidade().trim());
			ps.setString(2, infracaoValdemar.getDescricao().trim());
			ps.setDouble(3, infracaoValdemar.getValor());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(InfracaoValdemar infracaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="UPDATE infracoes SET gravidade_infracao=?, descricao_infracao=?, valor_infracao=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, infracaoValdemar.getGravidade().trim());
			ps.setString(2, infracaoValdemar.getDescricao().trim());
			ps.setDouble(3, infracaoValdemar.getValor());
			ps.setLong(4, infracaoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(InfracaoValdemar infracaoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM infracoes WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, infracaoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<InfracaoValdemar> lista() throws ClassNotFoundException, SQLException{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, gravidade_infracao, descricao_infracao, valor_infracao FROM infracoes";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<InfracaoValdemar> listaRetorno = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				InfracaoValdemar infracao = new InfracaoValdemar();
				infracao.setId(rs.getLong("id"));
				infracao.setGravidade(rs.getString("gravidade_infracao").trim());
				infracao.setDescricao(rs.getString("descricao_infracao").trim());
				infracao.setValor(rs.getDouble("valor_infracao"));
				
				listaRetorno.add(infracao);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public InfracaoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, gravidade_infracao, descricao_infracao, valor_infracao FROM infracoes WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		InfracaoValdemar retorno = new InfracaoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setGravidade(rs.getString("gravidade_infracao").trim());
				retorno.setDescricao(rs.getString("descricao_infracao").trim());
				retorno.setValor(rs.getDouble("valor_infracao"));
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
