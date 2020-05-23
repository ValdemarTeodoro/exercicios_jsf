package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProdutoDaoVademar;
import br.com.faculdadedelta.modelo.FornecedorValdemar;
import br.com.faculdadedelta.modelo.ProdutoValdemar;

@ManagedBean
@SessionScoped
public class ProdutoControllerValdemar {
	
	private String CADASTRO_PRODUTO = "cadastroProduto.xhtml";
	private String LISTA_PRODUTO = "listaProduto.xhtml";
	private ProdutoValdemar produto = new ProdutoValdemar();
	private ProdutoDaoVademar dao = new ProdutoDaoVademar();
	private FornecedorValdemar fornecedorSelecionado = new FornecedorValdemar();
	
	public ProdutoValdemar getProduto() {
		return produto;
	}
	
	public FornecedorValdemar getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(FornecedorValdemar fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public void setProduto(ProdutoValdemar produto) {
		this.produto = produto;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		produto = new ProdutoValdemar();
		fornecedorSelecionado = new FornecedorValdemar();
	}	
	
	public String salvar() {
		try {
			if(produto.getId()==null) {
				produto.setFornecedorValdemar(fornecedorSelecionado);;
				dao.incluir(produto);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				produto.setFornecedorValdemar(fornecedorSelecionado);;
				dao.alterar(produto);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_PRODUTO;
	}
	
	public String editar() {
		fornecedorSelecionado = produto.getFornecedorValdemar();
		return CADASTRO_PRODUTO;
	}
	
	public String excluir() {
		try {
			dao.excluir(produto);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_PRODUTO;
	}
	public List<ProdutoValdemar> getLista(){
		List<ProdutoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
