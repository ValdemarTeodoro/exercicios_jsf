package br.com.faculdadedelta.modelo;

public class UFValdemar {
	private Long id;
	private String nomeUf;
	private String siglaUf;
	private String codigoUf;
	private int idPais;
	
	private PaisValdemar paisValdemar;

	public UFValdemar() {
		super();
	}

	public UFValdemar(Long id, String nomeUf, String siglaUf, String codigoUf, int idPais,
			br.com.faculdadedelta.modelo.PaisValdemar paisValdemar) {
		super();
		this.id = id;
		this.nomeUf = nomeUf;
		this.siglaUf = siglaUf;
		this.codigoUf = codigoUf;
		this.idPais = idPais;
		this.paisValdemar = paisValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUf() {
		return nomeUf;
	}

	public void setNomeUf(String nomeUf) {
		this.nomeUf = nomeUf;
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}

	public String getCodigoUf() {
		return codigoUf;
	}

	public void setCodigoUf(String codigoUf) {
		this.codigoUf = codigoUf;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public PaisValdemar getPaisValdemar() {
		return paisValdemar;
	}

	public void setPaisValdemar(PaisValdemar paisValdemar) {
		this.paisValdemar = paisValdemar;
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
		UFValdemar other = (UFValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
