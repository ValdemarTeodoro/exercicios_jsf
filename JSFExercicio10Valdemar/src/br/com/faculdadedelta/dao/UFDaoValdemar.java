package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.PaisValdemar;
import br.com.faculdadedelta.modelo.UFValdemar;
import br.com.faculdadedelta.util.Conexao;

public class UFDaoValdemar {
	public void incluir(UFValdemar uf) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO ufs(nome_uf, sigla_uf, codigo_uf, id_pais) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uf.getNomeUf().trim());
			ps.setString(2, uf.getSiglaUf().trim());
			ps.setString(3, uf.getCodigoUf().trim());
			ps.setLong(4, uf.getPaisValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void altarar(UFValdemar uf) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE ufs SET nome_uf=?, sigla_uf=?, codigo_uf=?, id_pais=? WHERE id_uf=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uf.getNomeUf().trim());
			ps.setString(2, uf.getSiglaUf().trim());
			ps.setString(3, uf.getCodigoUf().trim());
			ps.setLong(4, uf.getPaisValdemar().getId());
			ps.setLong(5, uf.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(UFValdemar uf) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM ufs WHERE id_uf=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, uf.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<UFValdemar> lista() throws ClassNotFoundException, SQLException{
		List<UFValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT u.id_uf AS ufId, u.nome_uf AS ufNome, u.sigla_uf AS ufSigla, u.codigo_uf AS ufCodigo, p.id_pais AS paisId, p.nome_pais AS paisNome, p.codigo_pais AS paisCodigo FROM ufs u INNER JOIN pais p ON p.id_pais = u.id_pais";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				UFValdemar uf = new UFValdemar();
				uf.setId(rs.getLong("ufId"));
				uf.setNomeUf(rs.getString("ufNome").trim());
				uf.setSiglaUf(rs.getString("ufSigla").trim());
				uf.setCodigoUf(rs.getString("ufCodigo").trim());
				
				PaisValdemar pais = new PaisValdemar();
				pais.setId(rs.getLong("paisId"));
				pais.setNomePais(rs.getString("paisNome").trim());
				pais.setCodigoPais(rs.getString("paisCodigo").trim());
				
				uf.setPaisValdemar(pais);
				listaRetorno.add(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public UFValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_uf, nome_uf, sigla_uf, codigo_uf, id_pais FROM ufs WHERE id_uf=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		UFValdemar retrono = new UFValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retrono.setId(rs.getLong("id_uf"));
				retrono.setNomeUf(rs.getString("nome_uf").trim());
				retrono.setSiglaUf(rs.getString("sigla_uf").trim());
				retrono.setCodigoUf(rs.getString("codigo_uf").trim());
				retrono.setIdPais(rs.getInt("id_pais"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retrono;
	}
}
