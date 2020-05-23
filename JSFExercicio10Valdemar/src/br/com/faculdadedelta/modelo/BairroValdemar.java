package br.com.faculdadedelta.modelo;

public class BairroValdemar {
	private Long id;
	private String nomeBairro;
	private String descricaoBairro;
	private int idMunicipio;
	
	private MunicipioValdemar municipioValdemar;

	public BairroValdemar() {
		super();
	}

	public BairroValdemar(Long id, String nomeBairro, String descricaoBairro, int idMunicipio,
			MunicipioValdemar municipioValdemar) {
		super();
		this.id = id;
		this.nomeBairro = nomeBairro;
		this.descricaoBairro = descricaoBairro;
		this.idMunicipio = idMunicipio;
		this.municipioValdemar = municipioValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public MunicipioValdemar getMunicipioValdemar() {
		return municipioValdemar;
	}

	public void setMunicipioValdemar(MunicipioValdemar municipioValdemar) {
		this.municipioValdemar = municipioValdemar;
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
		BairroValdemar other = (BairroValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
