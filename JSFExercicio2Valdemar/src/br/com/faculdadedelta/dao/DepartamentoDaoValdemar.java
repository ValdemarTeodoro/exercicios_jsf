package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.DepartamentoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class DepartamentoDaoValdemar {
	
	public void incluir(DepartamentoValdemar departamento) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO departamentos(desc_departamento) VALUES (?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, departamento.getDepartamento().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(DepartamentoValdemar departamento) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE departamentos SET desc_departamento=? WHERE id_departamento=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, departamento.getDepartamento().trim());
			ps.setLong(2, departamento.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(DepartamentoValdemar departamento) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM departamentos WHERE id_departamento=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, departamento.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<DepartamentoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<DepartamentoValdemar> listaRetrono = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_departamento, desc_departamento FROM departamentos";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				DepartamentoValdemar departamento = new DepartamentoValdemar();
				departamento.setId(rs.getLong("id_departamento"));
				departamento.setDepartamento(rs.getString("desc_departamento").trim());
				listaRetrono.add(departamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetrono;
	}
	public DepartamentoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_departamento, desc_departamento FROM departamentos WHERE id_departamento=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		DepartamentoValdemar retorno = new DepartamentoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id_departamento"));
				retorno.setDepartamento(rs.getString("desc_departamento").trim());
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
