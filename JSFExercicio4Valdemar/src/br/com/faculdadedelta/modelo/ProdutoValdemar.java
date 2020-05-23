package br.com.faculdadedelta.modelo;

public class ProdutoValdemar {
	private Long id;
	private String produto;
	private int idFornecedor;
	private double valor;
	private FornecedorValdemar fornecedorValdemar;
	public ProdutoValdemar() {
		super();
	}
	
	public ProdutoValdemar(Long id, String produto, int idFornecedor, double valor,
			FornecedorValdemar fornecedorValdemar) {
		super();
		this.id = id;
		this.produto = produto;
		this.idFornecedor = idFornecedor;
		this.valor = valor;
		this.fornecedorValdemar = fornecedorValdemar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public FornecedorValdemar getFornecedorValdemar() {
		return fornecedorValdemar;
	}

	public void setFornecedorValdemar(FornecedorValdemar fornecedorValdemar) {
		this.fornecedorValdemar = fornecedorValdemar;
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
		ProdutoValdemar other = (ProdutoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
