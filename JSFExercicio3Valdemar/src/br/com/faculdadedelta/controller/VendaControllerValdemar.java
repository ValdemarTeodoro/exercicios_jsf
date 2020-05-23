package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VendasDaoValdemar;
import br.com.faculdadedelta.modelo.ProdutoValdemar;
import br.com.faculdadedelta.modelo.VendaValdemar;
@ManagedBean
@SessionScoped
public class VendaControllerValdemar {
	
	private String CADASTRO_VENDA  = "cadastroVenda.xhtml";
	private String LISTA_VENDA = "listaVenda.xhtml";
	
	private VendaValdemar venda = new VendaValdemar();
	private VendasDaoValdemar dao = new VendasDaoValdemar();
	private ProdutoValdemar produtoSelecionado = new ProdutoValdemar();
	
	public VendaValdemar getVenda() {
		return venda;
	}

	public void setVenda(VendaValdemar venda) {
		this.venda = venda;
	}

	public ProdutoValdemar getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(ProdutoValdemar produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		venda = new VendaValdemar();
		produtoSelecionado = new ProdutoValdemar();
	}
	
	public String salvar() {
		try {
			if(venda.getId()==null) {
				venda.setProdutoValdemar(produtoSelecionado);
				dao.incluir(venda);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(venda);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_VENDA;
	}
	public String editar() {
		produtoSelecionado = venda.getProdutoValdemar();
		return CADASTRO_VENDA;
	}
	public String excluir() {
		
		try {
				dao.excluir(venda);
				exibirMensagem("Exclusão realizada com sucesso!");
				limparCampos();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
		return LISTA_VENDA;
	}
	public List<VendaValdemar> getLista(){
		List<VendaValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	
	}
}
