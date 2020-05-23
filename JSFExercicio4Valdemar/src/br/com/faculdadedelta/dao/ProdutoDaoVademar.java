package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.FornecedorValdemar;
import br.com.faculdadedelta.modelo.ProdutoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ProdutoDaoVademar {
	
	public void incluir(ProdutoValdemar produto) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO produtos(desc_produto, id_fornecedor, valor_produto) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produto.getProduto().trim());
			ps.setLong(2, produto.getFornecedorValdemar().getId());
			ps.setDouble(3, produto.getValor());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(ProdutoValdemar produto) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE produtos SET desc_produto=?, id_fornecedor=?, valor_produto=? WHERE id_produto=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produto.getProduto().trim());
			ps.setLong(2, produto.getFornecedorValdemar().getId());
			ps.setDouble(3, produto.getValor());
			ps.setLong(4, produto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(ProdutoValdemar produto) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM produtos WHERE id_produto=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, produto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<ProdutoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<ProdutoValdemar> listaRetrono = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT p.id_produto AS idProduto, p.desc_produto AS produto, f.id_fornecedor AS idFornecedor, f.desc_fornecedor AS fornecedor, p.valor_produto AS valor FROM produtos p INNER JOIN fornecedores f ON f.id_fornecedor = p.id_fornecedor";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProdutoValdemar produto = new ProdutoValdemar();
				produto.setId(rs.getLong("idProduto"));
				produto.setProduto(rs.getString("produto"));
				produto.setValor(rs.getDouble("valor"));
				
				FornecedorValdemar fornecedor = new FornecedorValdemar();
				fornecedor.setId(rs.getLong("idFornecedor"));
				fornecedor.setFornecedor(rs.getString("fornecedor"));
				
				produto.setFornecedorValdemar(fornecedor);
				
				listaRetrono.add(produto);
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
