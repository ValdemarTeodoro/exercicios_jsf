package br.com.faculdadedelta.modelo;

import java.util.Date;

public class ContratoValdemar {

	private Long id;
	private String numeroContrato;
	private String descricaoContrato;
	private double valorContrato;
	private Date dataInicio;
	private Date dataFim;
	private int idProcesso;
	private ProcessoValdemar processoValdemar;
	public ContratoValdemar() {
		super();
	}
	public ContratoValdemar(Long id, String numeroContrato, String descricaoContrato, double valorContrato,
			Date dataInicio, Date dataFim, int idProcesso, ProcessoValdemar processoValdemar) {
		super();
		this.id = id;
		this.numeroContrato = numeroContrato;
		this.descricaoContrato = descricaoContrato;
		this.valorContrato = valorContrato;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idProcesso = idProcesso;
		this.processoValdemar = processoValdemar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getDescricaoContrato() {
		return descricaoContrato;
	}
	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}
	public double getValorContrato() {
		return valorContrato;
	}
	public void setValorContrato(double valorContrato) {
		this.valorContrato = valorContrato;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public int getIdProcesso() {
		return idProcesso;
	}
	public void setIdProcesso(int idProcesso) {
		this.idProcesso = idProcesso;
	}
	public ProcessoValdemar getProcessoValdemar() {
		return processoValdemar;
	}
	public void setProcessoValdemar(ProcessoValdemar processoValdemar) {
		this.processoValdemar = processoValdemar;
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
		ContratoValdemar other = (ContratoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
