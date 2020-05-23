package br.com.faculdadedelta.modelo;

public class VendaValdemar {
	private Long id;
	private String Cliente;
	private Double valor;
	private ProdutoValdemar produtoValdemar;
	public VendaValdemar() {
		super();
	}
	public VendaValdemar(Long id, String cliente, Double valor, ProdutoValdemar produtoValdemar) {
		super();
		this.id = id;
		Cliente = cliente;
		this.valor = valor;
		this.produtoValdemar = produtoValdemar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return Cliente;
	}
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public ProdutoValdemar getProdutoValdemar() {
		return produtoValdemar;
	}
	public void setProdutoValdemar(ProdutoValdemar produtoValdemar) {
		this.produtoValdemar = produtoValdemar;
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
		VendaValdemar other = (VendaValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
