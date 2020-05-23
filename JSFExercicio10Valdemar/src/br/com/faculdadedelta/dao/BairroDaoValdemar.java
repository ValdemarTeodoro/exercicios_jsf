package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BairroValdemar;
import br.com.faculdadedelta.modelo.MunicipioValdemar;
import br.com.faculdadedelta.util.Conexao;

public class BairroDaoValdemar {
	public void incluir(BairroValdemar bairroValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO bairros(nome_bairro, descricao_bairro, id_municipio) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bairroValdemar.getNomeBairro().trim());
			ps.setString(2, bairroValdemar.getDescricaoBairro().trim());
			ps.setLong(3, bairroValdemar.getMunicipioValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(BairroValdemar bairroValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="UPDATE bairros SET nome_bairro=?, descricao_bairro=?, id_municipio=? WHERE id_bairro=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bairroValdemar.getNomeBairro().trim());
			ps.setString(2, bairroValdemar.getDescricaoBairro().trim());
			ps.setLong(3, bairroValdemar.getMunicipioValdemar().getId());
			ps.setLong(4, bairroValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(BairroValdemar bairroValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM bairros WHERE id_bairro=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, bairroValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<BairroValdemar> lista() throws ClassNotFoundException, SQLException{
		List<BairroValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT b.id_bairro As bairroId, b.nome_bairro AS bairroNome, b.descricao_bairro AS bairroDescricao, mu.id_municipio AS municipioId, mu.nome_municipio AS municipioNome, mu.cnpj_municipio AS municipioCnpj, mu.codigo_municipio AS municipioCodigo, mu.id_uf AS ufIdmunicipio FROM bairros b INNER JOIN municipios mu on b.id_municipio = mu.id_municipio";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BairroValdemar bairroValdemar = new BairroValdemar();
				bairroValdemar.setId(rs.getLong("bairroId"));
				bairroValdemar.setNomeBairro(rs.getString("bairroNome").trim());
				bairroValdemar.setDescricaoBairro(rs.getString("bairroDescricao").trim());
				
				MunicipioValdemar municipio = new MunicipioValdemar();
				municipio.setId(rs.getLong("municipioId"));
				municipio.setNomeMunicipio(rs.getString("municipioNome").trim());
				municipio.setCnpjMunicipio(rs.getString("municipioCnpj").trim());
				municipio.setCodigoMunicipio(rs.getString("municipioCodigo").trim());
				municipio.setIdUF(rs.getInt("ufIdmunicipio"));
				
				bairroValdemar.setMunicipioValdemar(municipio);
				listaRetorno.add(bairroValdemar);
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
