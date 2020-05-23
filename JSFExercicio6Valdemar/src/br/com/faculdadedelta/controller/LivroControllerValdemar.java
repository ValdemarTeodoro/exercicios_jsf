package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.LivroDaoValdemar;
import br.com.faculdadedelta.modelo.EditoraValdemar;
import br.com.faculdadedelta.modelo.LivroValdemar;


@ManagedBean
@SessionScoped
public class LivroControllerValdemar {

	private String CADASTRO_LIVRO = "cadastroLivro.xhtml";
	private String LISTA_LIVRO = "listaLivro.xhtml";
	private LivroValdemar livroValdemar = new LivroValdemar();
	private LivroDaoValdemar dao = new LivroDaoValdemar();
	private EditoraValdemar editoraSelecionada = new EditoraValdemar();
	
	
	public LivroValdemar getLivroValdemar() {
		return livroValdemar;
	}
	public void setLivroValdemar(LivroValdemar livroValdemar) {
		this.livroValdemar = livroValdemar;
	}
	public EditoraValdemar getEditoraSelecionada() {
		return editoraSelecionada;
	}
	public void setEditoraSelecionada(EditoraValdemar editoraSelecionada) {
		this.editoraSelecionada = editoraSelecionada;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		livroValdemar = new LivroValdemar();
		editoraSelecionada = new EditoraValdemar();
	}	
	
	public String salvar() {
		try {
			if(livroValdemar.getId()==null) {
				livroValdemar.setEditoraValdemar(editoraSelecionada);
				dao.incluir(livroValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				livroValdemar.setEditoraValdemar(editoraSelecionada);
				dao.alterar(livroValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_LIVRO;
	}
	
	public String editar() {
		editoraSelecionada = livroValdemar.getEditoraValdemar();
		return CADASTRO_LIVRO;
	}
	
	public String excluir() {
		try {
			dao.excluir(livroValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_LIVRO;
	}
	public List<LivroValdemar> getLista(){
		List<LivroValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
