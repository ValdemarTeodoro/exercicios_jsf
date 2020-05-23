package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.EditoraDaoValdemar;
import br.com.faculdadedelta.modelo.EditoraValdemar;

@ManagedBean
@SessionScoped
public class EditoraControllerValdemar {

	private String CADASTRO_EDITORA = "cadastroEditora.xhtml";
	private String LISTA_EDITORA = "listaEditora.xhtml";
	
	private EditoraValdemar editoraValdemar = new EditoraValdemar();
	private EditoraDaoValdemar dao = new EditoraDaoValdemar();
	
	
	public EditoraValdemar getEditoraValdemar() {
		return editoraValdemar;
	}

	public void setEditoraValdemar(EditoraValdemar editoraValdemar) {
		this.editoraValdemar = editoraValdemar;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		editoraValdemar = new EditoraValdemar();
	}
	
	public String salvar() {
		try {
			if(editoraValdemar.getId()==null) {
				dao.incluir(editoraValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(editoraValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_EDITORA;
	}
	public String editar() {
		return CADASTRO_EDITORA;
	}
	public String excluir() {
		try {
			dao.excluir(editoraValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_EDITORA;
	}
	public List<EditoraValdemar> getLista(){
		List<EditoraValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
