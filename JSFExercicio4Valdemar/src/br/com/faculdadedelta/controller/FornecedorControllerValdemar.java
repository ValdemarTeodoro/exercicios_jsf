package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.FornecedorDaoValdemar;
import br.com.faculdadedelta.modelo.FornecedorValdemar;

@ManagedBean
@SessionScoped
public class FornecedorControllerValdemar {
	private String CADASTRO_FORNECEDOR = "cadastroFornecedor.xhtml";
	private String LISTA_FORNECEDOR = "listaFornecedor.xhtml";
	
	private FornecedorValdemar fornecedor = new FornecedorValdemar();
	private FornecedorDaoValdemar dao = new FornecedorDaoValdemar();
	public FornecedorValdemar getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(FornecedorValdemar fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		fornecedor = new FornecedorValdemar();
	}
	
	public String salvar() {
		try {
			if(fornecedor.getId()==null) {
				dao.incluir(fornecedor);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(fornecedor);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_FORNECEDOR;
	}
	public String editar() {
		return CADASTRO_FORNECEDOR;
	}
	public String excluir() {
		try {
			dao.excluir(fornecedor);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_FORNECEDOR;
	}
	public List<FornecedorValdemar> getLista(){
		List<FornecedorValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
