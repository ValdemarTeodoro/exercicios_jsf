package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BancoDaoValdemar;
import br.com.faculdadedelta.modelo.BancoValdemar;

@ManagedBean
@SessionScoped
public class BancoControllerValdemar {
	private String CADASTRO_BANCO = "cadastroBanco.xhtml";
	private String LISTA_BANCO = "listaBanco.xhtml";
	
	private BancoValdemar bancoValdemar = new BancoValdemar();
	private BancoDaoValdemar dao = new BancoDaoValdemar();
	public BancoValdemar getBancoValdemar() {
		return bancoValdemar;
	}
	public void setBancoValdemar(BancoValdemar bancoValdemar) {
		this.bancoValdemar = bancoValdemar;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		bancoValdemar = new BancoValdemar();
	}
	public String salvar() {
		try {
			if(bancoValdemar.getId()==null) {
				dao.incluir(bancoValdemar);
				exibirMensagem("Inclusão realizada com sucesso");
				limparCampos();
			}else {
				dao.alterar(bancoValdemar);
				exibirMensagem("Alteração realizada com sucesso");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return CADASTRO_BANCO;
	}
	public String editar() {
		return CADASTRO_BANCO;
	}
	public String excluir() {
		try {
			dao.excluir(bancoValdemar);
			exibirMensagem("Exclusão realizada com sucesso");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return LISTA_BANCO;
	}
	public List<BancoValdemar> getLista(){
		List<BancoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return listaRetorno;
	}
}
