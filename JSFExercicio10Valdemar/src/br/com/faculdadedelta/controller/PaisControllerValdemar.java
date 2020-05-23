package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.PaisDaoValdemar;
import br.com.faculdadedelta.modelo.PaisValdemar;

@ManagedBean
@SessionScoped
public class PaisControllerValdemar {
	private String CADASTRO_PAIS = "cadastroPais.xhtml";
	private String LISTA_PAIS = "listaPais.xhtml";
	
	private PaisValdemar paisValdemar = new PaisValdemar();
	private PaisDaoValdemar dao = new PaisDaoValdemar();
	public PaisValdemar getPaisValdemar() {
		return paisValdemar;
	}
	public void setPaisValdemar(PaisValdemar paisValdemar) {
		this.paisValdemar = paisValdemar;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		paisValdemar = new PaisValdemar();
	}
	public String salvar() {
		try {
			if(paisValdemar.getId()==null) {
				dao.incluir(paisValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(paisValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação");
		}
		return CADASTRO_PAIS;
	}
	public String editar() {
		return CADASTRO_PAIS;
	}
	public String excluir() {
		try {
			dao.excluir(paisValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação");
		}
		return LISTA_PAIS;
	}
	public List<PaisValdemar> getLista(){
		List<PaisValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
}
