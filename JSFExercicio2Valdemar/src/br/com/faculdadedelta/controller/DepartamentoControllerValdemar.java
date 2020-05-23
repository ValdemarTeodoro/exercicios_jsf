package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.DepartamentoDaoValdemar;
import br.com.faculdadedelta.modelo.DepartamentoValdemar;

@ManagedBean
@SessionScoped
public class DepartamentoControllerValdemar {
	private String CADASTRO_DEPARTAMENTO = "cadastroDepartamento.xhtml";
	private String LISTA_DEPARTAMENTO = "listaDepartamento.xhtml";
	
	private DepartamentoValdemar departamentoValdemar = new DepartamentoValdemar();
	private DepartamentoDaoValdemar dao = new DepartamentoDaoValdemar();
	
	public DepartamentoValdemar getDepartamentoValdemar() {
		return departamentoValdemar;
	}
	public void setDepartamentoValdemar(DepartamentoValdemar departamentoValdemar) {
		this.departamentoValdemar = departamentoValdemar;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		departamentoValdemar = new DepartamentoValdemar();
	}
	
	public String salvar() {
		try {
			if(departamentoValdemar.getId()==null) {
				dao.incluir(departamentoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(departamentoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_DEPARTAMENTO;
	}
	public String editar() {
		return CADASTRO_DEPARTAMENTO;
	}
	public String excluir() {
		try {
			dao.excluir(departamentoValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_DEPARTAMENTO;
	}
	public List<DepartamentoValdemar> getLista(){
		List<DepartamentoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
