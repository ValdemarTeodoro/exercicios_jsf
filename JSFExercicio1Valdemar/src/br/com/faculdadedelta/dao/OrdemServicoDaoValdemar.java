package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ClienteValdemar;
import br.com.faculdadedelta.modelo.OrdemServicoValdemar;
import br.com.faculdadedelta.modelo.ServicoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class OrdemServicoDaoValdemar {
	
	public void Incluir(OrdemServicoValdemar ordem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO ordem_servico(id_cliente, id_servico, valor_unitario_ordem_servico, qtde_ordem_servico, valor_desconto, valor_total_ordem_servico) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ordem.getCliente().getId());
			ps.setLong(2, ordem.getServico().getId());
			ps.setDouble(3, ordem.getValor());
			ps.setInt(4, ordem.getQuantidade());
			ps.setDouble(5, ordem.getDesconto());
			ordem.setValorTotal(ordem.getValor()*ordem.getQuantidade()-ordem.getDesconto());
			ps.setDouble(6, ordem.getValorTotal());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(OrdemServicoValdemar ordem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE ordem_servico SET id_cliente=?, id_servico=?, valor_unitario_ordem_servico=?, qtde_ordem_servico=?, valor_desconto=?, valor_total_ordem_servico=? WHERE id_ordem_servico=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ordem.getCliente().getId());
			ps.setLong(2, ordem.getServico().getId());
			ps.setDouble(3, ordem.getValor());
			ps.setInt(4, ordem.getQuantidade());
			ps.setDouble(5, ordem.getDesconto());
			ordem.setValorTotal(ordem.getValor()*ordem.getQuantidade()-ordem.getDesconto());
			ps.setDouble(6, ordem.getValorTotal());
			ps.setLong(7, ordem.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(OrdemServicoValdemar ordem) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM ordem_servico WHERE id_ordem_servico=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ordem.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<OrdemServicoValdemar> lista() throws ClassNotFoundException, SQLException{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT o.id_ordem_servico AS idOrdem, c.id_cliente AS idCliente, c.nome_cliente AS nomeCliente, c.idade_cliente AS idadeCliente, s.id_servico AS idServico, s.nome_servico AS nomeServico, s.descricao_servico AS descricaoServico, o.valor_unitario_ordem_servico As valorOrdem, o.qtde_ordem_servico AS quantidadeOrdem, o.valor_desconto AS descontoOrdem, o.valor_total_ordem_servico AS valorTotalOrdem FROM ordem_servico o INNER JOIN clientes c ON o.id_cliente = c.id_cliente INNER JOIN servico s ON o.id_servico = s.id_servico";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdemServicoValdemar> listaRetorno = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				OrdemServicoValdemar ordem = new OrdemServicoValdemar();
				ordem.setId(rs.getLong("idOrdem"));
				ordem.setValor(rs.getDouble("valorOrdem"));
				ordem.setQuantidade(rs.getInt("quantidadeOrdem"));
				ordem.setDesconto(rs.getDouble("descontoOrdem"));
				ordem.setValorTotal(rs.getDouble("valorTotalOrdem"));
				
				ClienteValdemar cliente = new ClienteValdemar();
				cliente.setId(rs.getLong("idCliente"));
				cliente.setNome(rs.getString("nomeCliente"));
				cliente.setIdade(rs.getInt("idadeCliente"));
				
				ordem.setCliente(cliente);
				
				ServicoValdemar servico = new ServicoValdemar();
				servico.setId(rs.getLong("idServico"));
				servico.setNomeServico(rs.getString("nomeServico"));
				servico.setDescricaoServico(rs.getString("descricaoServico"));
				
				ordem.setServico(servico);
				
				listaRetorno.add(ordem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
		return listaRetorno;
	}
}
