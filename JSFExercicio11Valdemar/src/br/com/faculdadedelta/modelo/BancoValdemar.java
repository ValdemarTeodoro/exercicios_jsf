package br.com.faculdadedelta.modelo;

public class BancoValdemar {
	private Long id;
	private String nomeBanco;
	private String codgioBanco;
	private String cnpjBanco;
	public BancoValdemar() {
		super();
	}
	public BancoValdemar(Long id, String nomeBanco, String codgioBanco, String cnpjBanco) {
		super();
		this.id = id;
		this.nomeBanco = nomeBanco;
		this.codgioBanco = codgioBanco;
		this.cnpjBanco = cnpjBanco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public String getCodgioBanco() {
		return codgioBanco;
	}
	public void setCodgioBanco(String codgioBanco) {
		this.codgioBanco = codgioBanco;
	}
	public String getCnpjBanco() {
		return cnpjBanco;
	}
	public void setCnpjBanco(String cnpjBanco) {
		this.cnpjBanco = cnpjBanco;
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
		BancoValdemar other = (BancoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
