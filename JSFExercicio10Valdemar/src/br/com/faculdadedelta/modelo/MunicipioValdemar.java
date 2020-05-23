package br.com.faculdadedelta.modelo;

public class MunicipioValdemar {
	private Long id;
	private String nomeMunicipio;
	private String cnpjMunicipio;
	private String codigoMunicipio;
	private int idUF;
	
	private UFValdemar ufValdemar;

	public MunicipioValdemar() {
		super();
	}

	public MunicipioValdemar(Long id, String nomeMunicipio, String cnpjMunicipio, String codigoMunicipio, int idUF,
			UFValdemar ufValdemar) {
		super();
		this.id = id;
		this.nomeMunicipio = nomeMunicipio;
		this.cnpjMunicipio = cnpjMunicipio;
		this.codigoMunicipio = codigoMunicipio;
		this.idUF = idUF;
		this.ufValdemar = ufValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getCnpjMunicipio() {
		return cnpjMunicipio;
	}

	public void setCnpjMunicipio(String cnpjMunicipio) {
		this.cnpjMunicipio = cnpjMunicipio;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public int getIdUF() {
		return idUF;
	}

	public void setIdUF(int idUF) {
		this.idUF = idUF;
	}

	public UFValdemar getUfValdemar() {
		return ufValdemar;
	}

	public void setUfValdemar(UFValdemar ufValdemar) {
		this.ufValdemar = ufValdemar;
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
		MunicipioValdemar other = (MunicipioValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
