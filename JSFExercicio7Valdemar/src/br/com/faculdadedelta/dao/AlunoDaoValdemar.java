package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.AlunoValdemar;
import br.com.faculdadedelta.modelo.GrauInstrucaoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class AlunoDaoValdemar {

	public void incluir(AlunoValdemar aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO alunos(nome, idade, datadenascimento, idgraudeinstrucao) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getNomeAluno().trim());
			ps.setInt(2, aluno.getIdade());
			ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setLong(4, aluno.getGrauInstrucaoValdemar().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(AlunoValdemar aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE alunos SET nome=?, idade=?, datadenascimento=?, idgraudeinstrucao=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getNomeAluno().trim());
			ps.setInt(2, aluno.getIdade());
			ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setLong(4, aluno.getGrauInstrucaoValdemar().getId());
			ps.setLong(5, aluno.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(AlunoValdemar aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM alunos WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, aluno.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<AlunoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<AlunoValdemar> listaRetrono = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT a.id AS idAluno, a.nome AS nomeAluno, a.idade AS idadeAluno, a.datadenascimento AS dataNascimentoAluno, a.idgraudeinstrucao AS idGraduacaoInstrucao, g.nome AS nomeInstrucao, g.id AS id FROM alunos a INNER JOIN graudeinstrucao G ON a.idgraudeinstrucao = g.id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				AlunoValdemar aluno = new AlunoValdemar();
				aluno.setId(rs.getLong("idAluno"));
				aluno.setNomeAluno(rs.getString("nomeAluno").trim());
				aluno.setIdade(rs.getInt("idadeAluno"));
				aluno.setDataNascimento(rs.getDate("dataNascimentoAluno"));
				
				GrauInstrucaoValdemar grau = new GrauInstrucaoValdemar();
				grau.setId(rs.getLong("id"));
				grau.setGrauInstrucao(rs.getString("nomeInstrucao").trim());
				
				aluno.setGrauInstrucaoValdemar(grau);
				
				listaRetrono.add(aluno);
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
