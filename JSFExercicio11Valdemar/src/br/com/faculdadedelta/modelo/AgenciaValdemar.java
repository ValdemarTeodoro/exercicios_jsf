package br.com.faculdadedelta.modelo;

public class AgenciaValdemar {
	private Long id;
	private String nomeAgencia;
	private String codigoAgencia;
	private String digitoVerificador;
	private int idBanco;
	
	private BancoValdemar bancoValdemar;

	public AgenciaValdemar() {
		super();
	}

	public AgenciaValdemar(Long id, String nomeAgencia, String codigoAgencia, String digitoVerificador, int idBanco,
			BancoValdemar bancoValdemar) {
		super();
		this.id = id;
		this.nomeAgencia = nomeAgencia;
		this.codigoAgencia = codigoAgencia;
		this.digitoVerificador = digitoVerificador;
		this.idBanco = idBanco;
		this.bancoValdemar = bancoValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public BancoValdemar getBancoValdemar() {
		return bancoValdemar;
	}

	public void setBancoValdemar(BancoValdemar bancoValdemar) {
		this.bancoValdemar = bancoValdemar;
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
		AgenciaValdemar other = (AgenciaValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
