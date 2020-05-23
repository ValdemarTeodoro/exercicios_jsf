package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.MotoristaValdemar;
import br.com.faculdadedelta.util.Conexao;

public class MotoristaDaoValdemar {
	public void incluir(MotoristaValdemar motoristaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO motoristas(nome_motorista, cpf_motorista, cnh_motorista) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, motoristaValdemar.getNomeMotorista().trim());
			ps.setString(2, motoristaValdemar.getCpfMotorista().trim());
			ps.setString(3, motoristaValdemar.getCnhMotorista().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(MotoristaValdemar motoristaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="UPDATE motoristas SET nome_motorista=?, cpf_motorista=?, cnh_motorista=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, motoristaValdemar.getNomeMotorista().trim());
			ps.setString(2, motoristaValdemar.getCpfMotorista().trim());
			ps.setString(3, motoristaValdemar.getCnhMotorista().trim());
			ps.setLong(4, motoristaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(MotoristaValdemar motoristaValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM motoristas WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, motoristaValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<MotoristaValdemar> lista() throws ClassNotFoundException, SQLException{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, nome_motorista, cpf_motorista, cnh_motorista FROM motoristas";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MotoristaValdemar> listaRetorno = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MotoristaValdemar motorista = new MotoristaValdemar();
				motorista.setId(rs.getLong("id"));
				motorista.setNomeMotorista(rs.getString("nome_motorista").trim());
				motorista.setCpfMotorista(rs.getString("cpf_motorista").trim());
				motorista.setCnhMotorista(rs.getString("cnh_motorista").trim());
				
				listaRetorno.add(motorista);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public MotoristaValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id, nome_motorista, cpf_motorista, cnh_motorista FROM motoristas WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MotoristaValdemar retorno = new MotoristaValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNomeMotorista(rs.getString("nome_motorista").trim());
				retorno.setCpfMotorista(rs.getString("cpf_motorista").trim());
				retorno.setCnhMotorista(rs.getString("cnh_motorista").trim());
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
