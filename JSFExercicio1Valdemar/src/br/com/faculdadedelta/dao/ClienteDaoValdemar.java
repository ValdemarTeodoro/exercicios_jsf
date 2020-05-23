package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ClienteValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ClienteDaoValdemar {
	public void incluir(ClienteValdemar cliente) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO clientes(nome_cliente, idade_cliente) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome().trim());
			ps.setInt(2, cliente.getIdade());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(ClienteValdemar cliente) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE public.clientes SET nome_cliente=?, idade_cliente=? WHERE id_cliente=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome().trim());
			ps.setInt(2, cliente.getIdade());
			ps.setLong(3, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(ClienteValdemar cliente) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM clientes WHERE id_cliente =?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<ClienteValdemar> lista() throws ClassNotFoundException, SQLException{
		List<ClienteValdemar> listaRetorno =  new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM clientes";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ClienteValdemar cliente = new ClienteValdemar();
				cliente.setId(rs.getLong("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente").trim());
				cliente.setIdade(rs.getInt("idade_cliente"));
				listaRetorno.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public ClienteValdemar PesquisadoPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM clientes WHERE id_cliente=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClienteValdemar retorno = new ClienteValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id_cliente"));
				retorno.setNome(rs.getString("nome_cliente").trim());
				retorno.setIdade(rs.getInt("idade_cliente"));
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
