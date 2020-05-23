package br.com.faculdadedelta.modelo;

public class FornecedorValdemar {
	
	private Long id;
	private String fornecedor;
	public FornecedorValdemar() {
		super();
	}
	public FornecedorValdemar(Long id, String fornecedor) {
		super();
		this.id = id;
		this.fornecedor = fornecedor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
		FornecedorValdemar other = (FornecedorValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
