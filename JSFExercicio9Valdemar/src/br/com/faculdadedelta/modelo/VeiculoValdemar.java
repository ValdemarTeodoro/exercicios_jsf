package br.com.faculdadedelta.modelo;

public class VeiculoValdemar {
	private Long id;
	private String gravidadeVeiculo;
	private String descricaoVeiculo;
	
	public VeiculoValdemar() {
	}
	public VeiculoValdemar(Long id, String gravidadeVeiculo, String descricaoVeiculo) {
		super();
		this.id = id;
		this.gravidadeVeiculo = gravidadeVeiculo;
		this.descricaoVeiculo = descricaoVeiculo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGravidadeVeiculo() {
		return gravidadeVeiculo;
	}
	public void setGravidadeVeiculo(String gravidadeVeiculo) {
		this.gravidadeVeiculo = gravidadeVeiculo;
	}
	public String getDescricaoVeiculo() {
		return descricaoVeiculo;
	}
	public void setDescricaoVeiculo(String descricaoVeiculo) {
		this.descricaoVeiculo = descricaoVeiculo;
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
		VeiculoValdemar other = (VeiculoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
