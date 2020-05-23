package br.com.faculdadedelta.modelo;

public class BemValdemar {
	private Long id;
	private String nomeBem;
	private String especificacaoBem;
	private int idDepartamento;
	private Double valorBem;
	private DepartamentoValdemar departamentoValdemar;
	
	
	public BemValdemar() {
		super();
	}

	public BemValdemar(Long id, String nomeBem, String especificacaoBem, int idDepartamento, Double valorBem,
			DepartamentoValdemar departamentoValdemar) {
		super();
		this.id = id;
		this.nomeBem = nomeBem;
		this.especificacaoBem = especificacaoBem;
		this.idDepartamento = idDepartamento;
		this.valorBem = valorBem;
		this.departamentoValdemar = departamentoValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeBem() {
		return nomeBem;
	}

	public void setNomeBem(String nomeBem) {
		this.nomeBem = nomeBem;
	}

	public String getEspecificacaoBem() {
		return especificacaoBem;
	}

	public void setEspecificacaoBem(String especificacaoBem) {
		this.especificacaoBem = especificacaoBem;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Double getValorBem() {
		return valorBem;
	}

	public void setValorBem(Double valorBem) {
		this.valorBem = valorBem;
	}

	public DepartamentoValdemar getDepartamentoValdemar() {
		return departamentoValdemar;
	}

	public void setDepartamentoValdemar(DepartamentoValdemar departamentoValdemar) {
		this.departamentoValdemar = departamentoValdemar;
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
		BemValdemar other = (BemValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
