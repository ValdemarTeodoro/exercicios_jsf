package br.com.faculdadedelta.modelo;

public class OrdemServicoValdemar {
	private Long id;
	private int idCliente;
	private int idServico;
	private double valor;
	private int quantidade;
	private double desconto;
	private double valorTotal;
	private ClienteValdemar cliente;
	private ServicoValdemar servico;
	
	public OrdemServicoValdemar() {

	}
	
	public OrdemServicoValdemar(Long id, int idCliente, int idServico, double valor, int quantidade, double desconto,
			double valorTotal, ClienteValdemar cliente, ServicoValdemar servico) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idServico = idServico;
		this.valor = valor;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.servico = servico;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ClienteValdemar getCliente() {
		return cliente;
	}

	public void setCliente(ClienteValdemar cliente) {
		this.cliente = cliente;
	}

	public ServicoValdemar getServico() {
		return servico;
	}

	public void setServico(ServicoValdemar servico) {
		this.servico = servico;
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
		OrdemServicoValdemar other = (OrdemServicoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
