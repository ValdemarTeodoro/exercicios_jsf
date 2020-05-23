package br.com.faculdadedelta.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BemValdemar;
import br.com.faculdadedelta.modelo.DepartamentoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class BemDaoValdemar {
	public void incluir(BemValdemar bem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO bens(nome_bem, especificacao_bem, id_departamento, valor_bem) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bem.getNomeBem().trim());
			ps.setString(2, bem.getEspecificacaoBem().trim());
			ps.setLong(3, bem.getDepartamentoValdemar().getId());
			ps.setDouble(4, bem.getValorBem());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(BemValdemar bem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="UPDATE bens SET nome_bem=?, especificacao_bem=?, id_departamento=?, valor_bem=? WHERE id_bem=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bem.getNomeBem().trim());
			ps.setString(2, bem.getEspecificacaoBem().trim());
			ps.setLong(3, bem.getDepartamentoValdemar().getId());
			ps.setDouble(4, bem.getValorBem());
			ps.setLong(5, bem.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(BemValdemar bem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM bens WHERE id_bem=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, bem.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<BemValdemar> lista() throws ClassNotFoundException, SQLException{
		List<BemValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT b.id_bem AS idBem, b.nome_bem AS nomeBem, b.especificacao_bem AS especificacaoBem, d.id_departamento AS idDepartamento, d.desc_departamento AS descDepartamento, b.valor_bem AS valorBem FROM bens b INNER JOIN departamentos d ON d.id_departamento = b.id_departamento";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BemValdemar bem = new BemValdemar();
				bem.setId(rs.getLong("idBem"));
				bem.setNomeBem(rs.getString("nomeBem"));
				bem.setEspecificacaoBem(rs.getString("especificacaoBem"));
				bem.setValorBem(rs.getDouble("valorBem"));
				
				DepartamentoValdemar departamento = new DepartamentoValdemar();
				departamento.setId(rs.getLong("idDepartamento"));
				departamento.setDepartamento(rs.getString("descDepartamento"));
				
				bem.setDepartamentoValdemar(departamento);
				
				listaRetorno.add(bem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;		
	}
}
