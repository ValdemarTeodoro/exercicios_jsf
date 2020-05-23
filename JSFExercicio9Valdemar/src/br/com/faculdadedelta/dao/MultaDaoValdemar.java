package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InfracaoValdemar;
import br.com.faculdadedelta.modelo.MotoristaValdemar;
import br.com.faculdadedelta.modelo.MultaValdemar;
import br.com.faculdadedelta.modelo.VeiculoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class MultaDaoValdemar {
	public void incluir(MultaValdemar multaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO multas(id_infracao, id_veiculo, id_motorista, data_multa) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, multaValdemar.getInfracaoValdemar().getId());
			ps.setLong(2, multaValdemar.getVeiculoValdemar().getId());
			ps.setLong(3, multaValdemar.getMotoristaValdemar().getId());
			ps.setDate(4, new java.sql.Date(multaValdemar.getDataMulta().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(MultaValdemar multaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE multas SET id_infracao=?, id_veiculo=?, id_motorista=?, data_multa=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, multaValdemar.getInfracaoValdemar().getId());
			ps.setLong(2, multaValdemar.getVeiculoValdemar().getId());
			ps.setLong(3, multaValdemar.getMotoristaValdemar().getId());
			ps.setDate(4, new java.sql.Date(multaValdemar.getDataMulta().getTime()));
			ps.setLong(5, multaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(MultaValdemar multaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM multas WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, multaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<MultaValdemar> lista() throws ClassNotFoundException, SQLException{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT mu.id AS multaId, mu.id_infracao AS infracaoId, i.gravidade_infracao AS gravidadeInfracao, mu.id_veiculo AS veiculoId, v.gravidade_veiculo AS gravidadeVeiculo, mu.id_motorista AS motoristaId, mo.nome_motorista AS nomeMotorista, mu.data_multa AS multaData FROM multas mu INNER JOIN infracoes i on mu.id_infracao = i.id INNER JOIN veiculos v on mu.id_veiculo = v.id INNER JOIN motoristas mo on mu.id_motorista = mo.id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MultaValdemar> listaRetorno = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MultaValdemar multa = new MultaValdemar();
				multa.setId(rs.getLong("multaId"));
				multa.setDataMulta(rs.getDate("multaData"));
				
				InfracaoValdemar infracao = new InfracaoValdemar();
				infracao.setId(rs.getLong("infracaoId"));
				infracao.setGravidade(rs.getString("gravidadeInfracao").trim());
				
				multa.setInfracaoValdemar(infracao);
				
				VeiculoValdemar veiculo = new VeiculoValdemar();
				veiculo.setId(rs.getLong("veiculoId"));
				veiculo.setGravidadeVeiculo(rs.getString("gravidadeVeiculo").trim());
				
				multa.setVeiculoValdemar(veiculo);
				
				MotoristaValdemar motorista = new MotoristaValdemar();
				motorista.setId(rs.getLong("motoristaId"));
				motorista.setNomeMotorista(rs.getString("nomeMotorista").trim());
				
				multa.setMotoristaValdemar(motorista);
				
				listaRetorno.add(multa);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
}
