package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.EditoraValdemar;
import br.com.faculdadedelta.modelo.LivroValdemar;
import br.com.faculdadedelta.util.Conexao;

public class LivroDaoValdemar {

	public void incluir(LivroValdemar livro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO livros(nome_livro, id_editora, valor_livro) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, livro.getNomeLivro().trim());
			ps.setLong(2, livro.getEditoraValdemar().getId());
			ps.setDouble(3, livro.getValor());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(LivroValdemar livro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE livros SET nome_livro=?, id_editora=?, valor_livro=? WHERE id_livro=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, livro.getNomeLivro().trim());
			ps.setLong(2, livro.getEditoraValdemar().getId());
			ps.setDouble(3, livro.getValor());
			ps.setLong(4, livro.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(LivroValdemar livro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM livros WHERE id_livro=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, livro.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<LivroValdemar> lista() throws ClassNotFoundException, SQLException{
		List<LivroValdemar> listaRetrono = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT l.id_livro AS livroId, l.nome_livro AS livroNome, e.id_editora AS editoraId, desc_editora AS editoraDesc, l.valor_livro AS livroValor FROM livros l INNER JOIN editoras e ON l.id_editora = e.id_editora";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				LivroValdemar livro = new LivroValdemar();
				livro.setId(rs.getLong("livroId"));
				livro.setNomeLivro(rs.getString("livroNome").trim());
				livro.setValor(rs.getDouble("livroValor"));
				
				EditoraValdemar editora = new EditoraValdemar();
				editora.setId(rs.getLong("editoraId"));
				editora.setEditora(rs.getString("editoraDesc").trim());
				
				livro.setEditoraValdemar(editora);
				
				listaRetrono.add(livro);
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
