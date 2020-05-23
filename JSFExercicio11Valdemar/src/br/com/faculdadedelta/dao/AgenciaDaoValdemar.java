package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.AgenciaValdemar;
import br.com.faculdadedelta.modelo.BancoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class AgenciaDaoValdemar {
	public void incluir(AgenciaValdemar agenciaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO agencias(nome_agencia, codigo_agencia, digito_verificador, id_banco) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, agenciaValdemar.getNomeAgencia().trim());
			ps.setString(2, agenciaValdemar.getCodigoAgencia().trim());
			ps.setString(3, agenciaValdemar.getDigitoVerificador());
			ps.setLong(4, agenciaValdemar.getBancoValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(AgenciaValdemar agenciaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE agencias SET nome_agencia=?, codigo_agencia=?, digito_verificador=?, id_banco=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, agenciaValdemar.getNomeAgencia().trim());
			ps.setString(2, agenciaValdemar.getCodigoAgencia().trim());
			ps.setString(3, agenciaValdemar.getDigitoVerificador());
			ps.setLong(4, agenciaValdemar.getBancoValdemar().getId());
			ps.setLong(5, agenciaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(AgenciaValdemar agenciaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM agencias WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, agenciaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<AgenciaValdemar> lista() throws ClassNotFoundException, SQLException{
		List<AgenciaValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT a.id AS agenciaId, a.nome_agencia AS agenciaNome, a.codigo_agencia AS codigoAgencia, a.digito_verificador AS digitoVerificadorAgencia, a.id_banco AS agenciaBancoId, b.nome_banco AS nomeBanco FROM agencias a INNER JOIN bancos b on b.id = a.id_banco";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				AgenciaValdemar agencia = new AgenciaValdemar();
				agencia.setId(rs.getLong("agenciaId"));
				agencia.setNomeAgencia(rs.getString("agenciaNome").trim());
				agencia.setCodigoAgencia(rs.getString("codigoAgencia").trim());
				agencia.setDigitoVerificador(rs.getString("digitoVerificadorAgencia").trim());
				
				BancoValdemar banco = new BancoValdemar();
				banco.setId(rs.getLong("agenciaBancoId"));
				banco.setNomeBanco(rs.getString("nomeBanco").trim());
				
				agencia.setBancoValdemar(banco);
				
				listaRetorno.add(agencia);
				
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
