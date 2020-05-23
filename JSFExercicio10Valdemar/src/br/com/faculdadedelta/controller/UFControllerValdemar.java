package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.UFDaoValdemar;
import br.com.faculdadedelta.modelo.PaisValdemar;
import br.com.faculdadedelta.modelo.UFValdemar;

@ManagedBean
@SessionScoped
public class UFControllerValdemar {
	
	private String CADASTRO_UF ="cadastroUf.xhtml";
	private String LISTA_UF ="listaUf.xhtml";
	
	private UFValdemar ufValdemar = new UFValdemar();
	private UFDaoValdemar dao = new UFDaoValdemar();
	private PaisValdemar paisSelecionado = new PaisValdemar();
	public UFValdemar getUfValdemar() {
		return ufValdemar;
	}
	public void setUfValdemar(UFValdemar ufValdemar) {
		this.ufValdemar = ufValdemar;
	}
	public PaisValdemar getPaisSelecionado() {
		return paisSelecionado;
	}
	public void setPaisSelecionado(PaisValdemar paisSelecionado) {
		this.paisSelecionado = paisSelecionado;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		paisSelecionado = new PaisValdemar();
		ufValdemar = new UFValdemar();
	}
	public String salvar() {
		try {
			if(ufValdemar.getId()==null) {
				ufValdemar.setPaisValdemar(paisSelecionado);
				dao.incluir(ufValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				ufValdemar.setPaisValdemar(paisSelecionado);
				dao.altarar(ufValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação ! " + e.getMessage());
		}
		return CADASTRO_UF;
	}
	public String editar() {
		paisSelecionado = ufValdemar.getPaisValdemar();
		return CADASTRO_UF;
	}
	public String excluir() {
		try {
			dao.excluir(ufValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação ! " + e.getMessage());
		}
		return LISTA_UF;
	}
	public List<UFValdemar> getLista(){
		List<UFValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			exibirMensagem("Erro ao realizar a operação ! " + e.getMessage());
		}
		return listaRetorno;
	}
}
