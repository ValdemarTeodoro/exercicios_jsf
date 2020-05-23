package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ProdutoValdemar;
import br.com.faculdadedelta.modelo.VendaValdemar;
import br.com.faculdadedelta.util.Conexao;

public class VendasDaoValdemar {
	
	public void incluir(VendaValdemar venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO vendas(desc_produto, id_produto, valor_produto) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, venda.getCliente());
			ps.setLong(2, venda.getProdutoValdemar().getId());
			ps.setDouble(3, venda.getValor());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(VendaValdemar venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE vendas SET desc_produto=?, id_produto=?, valor_produto=? WHERE id_venda=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, venda.getCliente());
			ps.setLong(2, venda.getProdutoValdemar().getId());
			ps.setDouble(3, venda.getValor());
			ps.setLong(4, venda.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(VendaValdemar venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM vendas WHERE id_venda=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, venda.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<VendaValdemar> lista() throws ClassNotFoundException, SQLException{
		List<VendaValdemar> listaRetrono = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT v.id_venda AS idVenda, v.desc_produto AS cliente, p.id_produto AS idProduto, p.desc_produto AS descProduto, v.valor_produto AS valorProduto FROM vendas v INNER JOIN produtos p ON v.id_produto = p.id_produto";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				VendaValdemar venda = new VendaValdemar();
				venda.setId(rs.getLong("idVenda"));
				venda.setCliente(rs.getString("cliente"));
				venda.setValor(rs.getDouble("valorProduto"));
				
				ProdutoValdemar produto = new ProdutoValdemar();
				produto.setId(rs.getLong("idProduto"));
				produto.setDescricao(rs.getString("descProduto"));
				
				venda.setProdutoValdemar(produto);
				
				listaRetrono.add(venda);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetrono;
	}

}
