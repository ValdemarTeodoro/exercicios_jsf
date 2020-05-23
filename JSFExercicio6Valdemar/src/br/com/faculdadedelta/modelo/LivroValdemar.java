package br.com.faculdadedelta.modelo;

public class LivroValdemar {
	private Long id;
	private String nomeLivro;
	private int idEditora;
	private double valor;
	private EditoraValdemar editoraValdemar;
	public LivroValdemar() {
		super();
	}
	public LivroValdemar(Long id, String nomeLivro, int idEditora, double valor, EditoraValdemar editoraValdemar) {
		super();
		this.id = id;
		this.nomeLivro = nomeLivro;
		this.idEditora = idEditora;
		this.valor = valor;
		this.editoraValdemar = editoraValdemar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public int getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public EditoraValdemar getEditoraValdemar() {
		return editoraValdemar;
	}
	public void setEditoraValdemar(EditoraValdemar editoraValdemar) {
		this.editoraValdemar = editoraValdemar;
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
		LivroValdemar other = (LivroValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
