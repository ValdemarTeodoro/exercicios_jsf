package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.MunicipioValdemar;
import br.com.faculdadedelta.modelo.UFValdemar;
import br.com.faculdadedelta.util.Conexao;

public class MunicipioDaoValdemar {
	public void incluir(MunicipioValdemar municipio) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO municipios(nome_municipio, cnpj_municipio, codigo_municipio, id_uf) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, municipio.getNomeMunicipio().trim());
			ps.setString(2, municipio.getCnpjMunicipio().trim());
			ps.setString(3, municipio.getCodigoMunicipio().trim());
			ps.setLong(4, municipio.getUfValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(MunicipioValdemar municipio) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE municipios SET id_municipio=?, nome_municipio=?, cnpj_municipio=?, codigo_municipio=?, id_uf=? WHERE id_municipio=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, municipio.getNomeMunicipio().trim());
			ps.setString(2, municipio.getCnpjMunicipio().trim());
			ps.setString(3, municipio.getCodigoMunicipio().trim());
			ps.setLong(4, municipio.getUfValdemar().getId());
			ps.setLong(5, municipio.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(MunicipioValdemar municipio) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM municipios WHERE id_municipio=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, municipio.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<MunicipioValdemar> lista() throws ClassNotFoundException, SQLException{
		List<MunicipioValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT m.id_municipio AS municipioId, m.nome_municipio AS municipioNome, m.cnpj_municipio AS municipioCnpj, m.codigo_municipio AS municipioCodigo, u.id_uf AS ufId, u.nome_uf AS ufNome, u.sigla_uf AS ufSigla, u.codigo_uf AS ufCodigo, u.id_pais AS paisIdUf FROM municipios m INNER JOIN ufs u ON u.id_uf = m.id_uf";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MunicipioValdemar municipio = new MunicipioValdemar();
				municipio.setId(rs.getLong("municipioId"));
				municipio.setNomeMunicipio(rs.getString("municipioNome").trim());
				municipio.setCnpjMunicipio(rs.getString("municipioCnpj").trim());
				municipio.setCodigoMunicipio(rs.getString("municipioCodigo").trim());
				
				UFValdemar uf = new UFValdemar();
				uf.setId(rs.getLong("ufId"));
				uf.setNomeUf(rs.getString("ufNome").trim());
				uf.setSiglaUf(rs.getString("ufSigla").trim());
				uf.setCodigoUf(rs.getString("ufCodigo").trim());
				uf.setIdPais(rs.getInt("paisIdUf"));
				
				municipio.setUfValdemar(uf);
				listaRetorno.add(municipio);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public MunicipioValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_municipio, nome_municipio, cnpj_municipio, codigo_municipio, id_uf FROM municipios WHERE id_municipio=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MunicipioValdemar retorno = new MunicipioValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id_municipio"));
				retorno.setNomeMunicipio(rs.getString("nome_municipio").trim());
				retorno.setCnpjMunicipio(rs.getString("cnpj_municipio").trim());
				retorno.setCodigoMunicipio(rs.getString("codigo_municipio").trim());
				retorno.setIdUF(rs.getInt("id_uf"));
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
