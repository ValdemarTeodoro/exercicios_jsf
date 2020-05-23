package br.com.faculdadedelta.modelo;

import java.util.Date;

public class MultaValdemar {
	private Long id;
	private int idInfracao;
	private int idVeiculo;
	private int idMotorista;
	private Date dataMulta;
	
	private InfracaoValdemar infracaoValdemar;
	private VeiculoValdemar veiculoValdemar;
	private MotoristaValdemar motoristaValdemar;
	public MultaValdemar() {
		super();
	}
	public MultaValdemar(Long id, int idInfracao, int idVeiculo, int idMotorista, Date dataMulta,
			InfracaoValdemar infracaoValdemar, VeiculoValdemar veiculoValdemar, MotoristaValdemar motoristaValdemar) {
		super();
		this.id = id;
		this.idInfracao = idInfracao;
		this.idVeiculo = idVeiculo;
		this.idMotorista = idMotorista;
		this.dataMulta = dataMulta;
		this.infracaoValdemar = infracaoValdemar;
		this.veiculoValdemar = veiculoValdemar;
		this.motoristaValdemar = motoristaValdemar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIdInfracao() {
		return idInfracao;
	}
	public void setIdInfracao(int idInfracao) {
		this.idInfracao = idInfracao;
	}
	public int getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public int getIdMotorista() {
		return idMotorista;
	}
	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;
	}
	public Date getDataMulta() {
		return dataMulta;
	}
	public void setDataMulta(Date dataMulta) {
		this.dataMulta = dataMulta;
	}
	public InfracaoValdemar getInfracaoValdemar() {
		return infracaoValdemar;
	}
	public void setInfracaoValdemar(InfracaoValdemar infracaoValdemar) {
		this.infracaoValdemar = infracaoValdemar;
	}
	public VeiculoValdemar getVeiculoValdemar() {
		return veiculoValdemar;
	}
	public void setVeiculoValdemar(VeiculoValdemar veiculoValdemar) {
		this.veiculoValdemar = veiculoValdemar;
	}
	public MotoristaValdemar getMotoristaValdemar() {
		return motoristaValdemar;
	}
	public void setMotoristaValdemar(MotoristaValdemar motoristaValdemar) {
		this.motoristaValdemar = motoristaValdemar;
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
		MultaValdemar other = (MultaValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
