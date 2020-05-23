package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.VeiculoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class VeiculoDaoValdemar {
	public void incluir(VeiculoValdemar veiculoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO veiculos(gravidade_veiculo, descricao_veiculo) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, veiculoValdemar.getGravidadeVeiculo().trim());
			ps.setString(2, veiculoValdemar.getDescricaoVeiculo().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(VeiculoValdemar veiculoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="UPDATE veiculos SET gravidade_veiculo=?, descricao_veiculo=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, veiculoValdemar.getGravidadeVeiculo().trim());
			ps.setString(2, veiculoValdemar.getDescricaoVeiculo().trim());
			ps.setLong(3, veiculoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(VeiculoValdemar veiculoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM veiculos WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, veiculoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<VeiculoValdemar> lista() throws ClassNotFoundException, SQLException{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, gravidade_veiculo, descricao_veiculo FROM veiculos";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VeiculoValdemar> listaRetorno = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				VeiculoValdemar veiculo = new VeiculoValdemar();
				veiculo.setId(rs.getLong("id"));
				veiculo.setGravidadeVeiculo(rs.getString("gravidade_veiculo").trim());
				veiculo.setDescricaoVeiculo(rs.getString("descricao_veiculo").trim());
				
				listaRetorno.add(veiculo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public VeiculoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, gravidade_veiculo, descricao_veiculo FROM veiculos WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		VeiculoValdemar retorno = new VeiculoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setGravidadeVeiculo(rs.getString("gravidade_veiculo").trim());
				retorno.setDescricaoVeiculo(rs.getString("descricao_veiculo").trim());
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
