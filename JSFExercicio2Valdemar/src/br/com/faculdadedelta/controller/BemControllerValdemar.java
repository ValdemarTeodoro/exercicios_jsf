package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BemDaoValdemar;
import br.com.faculdadedelta.modelo.BemValdemar;
import br.com.faculdadedelta.modelo.DepartamentoValdemar;

@ManagedBean
@SessionScoped
public class BemControllerValdemar {
	
	private String CADASTRO_BEM  = "cadastroBem.xhtml";
	private String LISTA_BEM = "listaBem.xhtml";
	
	private BemValdemar bem = new BemValdemar();
	private BemDaoValdemar dao = new BemDaoValdemar();
	private DepartamentoValdemar departamentoSelecionado = new DepartamentoValdemar();
	public BemValdemar getBem() {
		return bem;
	}
	public void setBem(BemValdemar bem) {
		this.bem = bem;
	}
	public DepartamentoValdemar getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}
	public void setDepartamentoSelecionado(DepartamentoValdemar departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		bem = new BemValdemar();
		departamentoSelecionado = new DepartamentoValdemar();
	}
	
	public String salvar() {
		try {
			if(bem.getId()==null) {
				bem.setDepartamentoValdemar(departamentoSelecionado);
				dao.incluir(bem);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(bem);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_BEM;
	}
	public String editar() {
		departamentoSelecionado = bem.getDepartamentoValdemar();
		return CADASTRO_BEM;
	}
	public String excluir() {
		
		try {
				dao.excluir(bem);
				exibirMensagem("Exclusão realizada com sucesso!");
				limparCampos();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
		return LISTA_BEM;
	}
	public List<BemValdemar> getLista(){
		List<BemValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	
	}
}
