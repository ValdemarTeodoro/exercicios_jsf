package br.com.faculdadedelta.modelo;

import java.util.Date;

public class AlunoValdemar {
	private Long id;
	private String nomeAluno;
	private int idade;
	private Date dataNascimento;
	private int idgraudeinstrucao;
	private GrauInstrucaoValdemar grauInstrucaoValdemar;
	public AlunoValdemar() {
		super();
	}
	public AlunoValdemar(Long id, String nomeAluno, int idade, Date dataNascimento, int idgraudeinstrucao,
			GrauInstrucaoValdemar grauInstrucaoValdemar) {
		super();
		this.id = id;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.idgraudeinstrucao = idgraudeinstrucao;
		this.grauInstrucaoValdemar = grauInstrucaoValdemar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdgraudeinstrucao() {
		return idgraudeinstrucao;
	}
	public void setIdgraudeinstrucao(int idgraudeinstrucao) {
		this.idgraudeinstrucao = idgraudeinstrucao;
	}
	public GrauInstrucaoValdemar getGrauInstrucaoValdemar() {
		return grauInstrucaoValdemar;
	}
	public void setGrauInstrucaoValdemar(GrauInstrucaoValdemar grauInstrucaoValdemar) {
		this.grauInstrucaoValdemar = grauInstrucaoValdemar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoValdemar other = (AlunoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
