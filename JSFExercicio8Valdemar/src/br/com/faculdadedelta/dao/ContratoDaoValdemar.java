package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ContratoValdemar;
import br.com.faculdadedelta.modelo.ProcessoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ContratoDaoValdemar {
	public void incluir(ContratoValdemar contratoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO contratos(numero_contrato, descricao_contrato, valor_contrato, data_inicio_contrato, data_fim_contrato, id_processo) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, contratoValdemar.getNumeroContrato().trim());
			ps.setString(2, contratoValdemar.getDescricaoContrato().trim());
			ps.setDouble(3, contratoValdemar.getValorContrato());
			ps.setDate(4, new java.sql.Date(contratoValdemar.getDataInicio().getTime()));
			ps.setDate(5, new java.sql.Date(contratoValdemar.getDataFim().getTime()));
			ps.setLong(6, contratoValdemar.getProcessoValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(ContratoValdemar contratoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE contratos SET numero_contrato=?, descricao_contrato=?, valor_contrato=?, data_inicio_contrato=?, data_fim_contrato=?, id_processo=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, contratoValdemar.getNumeroContrato().trim());
			ps.setString(2, contratoValdemar.getDescricaoContrato().trim());
			ps.setDouble(3, contratoValdemar.getValorContrato());
			ps.setDate(4, new java.sql.Date(contratoValdemar.getDataInicio().getTime()));
			ps.setDate(5, new java.sql.Date(contratoValdemar.getDataFim().getTime()));
			ps.setLong(6, contratoValdemar.getProcessoValdemar().getId());
			ps.setLong(7, contratoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(ContratoValdemar contratoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM contratos WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, contratoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<ContratoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<ContratoValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT c.id AS idContrato, c.numero_contrato AS contratoNumero, c.descricao_contrato AS descricaoContrato, c.valor_contrato AS valorContrato, c.data_inicio_contrato AS dataInicoContrato, c.data_fim_contrato AS dataFimContrato, c.id_processo AS idProcesso, p.numero_processo AS numeroProcesso, p.descricao_processo AS descricaoProcesso, p.valor_processo AS valorProcesso FROM contratos c INNER JOIN processos p ON c.id_processo = p.id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ContratoValdemar contrato = new ContratoValdemar();
				contrato.setId(rs.getLong("idContrato"));
				contrato.setNumeroContrato(rs.getString("contratoNumero").trim());
				contrato.setDescricaoContrato(rs.getString("descricaoContrato").trim());
				contrato.setValorContrato(rs.getDouble("valorContrato"));
				contrato.setDataInicio(rs.getDate("dataInicoContrato"));
				contrato.setDataFim(rs.getDate("dataFimContrato"));
				
				ProcessoValdemar processo = new ProcessoValdemar();
				processo.setId(rs.getLong("idProcesso"));
				processo.setNumeroProcesso(rs.getString("numeroProcesso"));
				processo.setDescricaoProcesso(rs.getString("descricaoProcesso"));
				processo.setValorProcesso(rs.getDouble("valorProcesso"));
				
				contrato.setProcessoValdemar(processo);
				
				listaRetorno.add(contrato);
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
