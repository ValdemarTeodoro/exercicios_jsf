package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.AgenciaDaoValdemar;
import br.com.faculdadedelta.modelo.AgenciaValdemar;
import br.com.faculdadedelta.modelo.BancoValdemar;

@ManagedBean
@SessionScoped
public class AgenciaControllerValdemar {
	private String CADASTRO_AGENCIA = "cadastroAgencia.xhtml";
	private String LISTA_AGENCIA = "listaAgencia.xhtml";
	
	private AgenciaValdemar agenciaValdemar = new AgenciaValdemar();
	private AgenciaDaoValdemar dao = new AgenciaDaoValdemar();
	private BancoValdemar bancoSelecionado = new BancoValdemar();
	public AgenciaValdemar getAgenciaValdemar() {
		return agenciaValdemar;
	}
	public void setAgenciaValdemar(AgenciaValdemar agenciaValdemar) {
		this.agenciaValdemar = agenciaValdemar;
	}
	public BancoValdemar getBancoSelecionado() {
		return bancoSelecionado;
	}
	public void setBancoSelecionado(BancoValdemar bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		agenciaValdemar = new AgenciaValdemar();
		bancoSelecionado = new BancoValdemar();
	}
	public String salvar() {
		try {
			if(agenciaValdemar.getId()==null) {
				agenciaValdemar.setBancoValdemar(bancoSelecionado);
				dao.incluir(agenciaValdemar);
				exibirMensagem("Inclusão realizada com sucesso");
				limparCampos();
			}else {
				agenciaValdemar.setBancoValdemar(bancoSelecionado);
				dao.alterar(agenciaValdemar);
				exibirMensagem("Alteração realizada com sucesso");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return CADASTRO_AGENCIA;
	}
	public String editar() {
		
		bancoSelecionado = agenciaValdemar.getBancoValdemar();
		return CADASTRO_AGENCIA;
	}
	public String excluir() {
		try {
			dao.excluir(agenciaValdemar);
			exibirMensagem("Exclusão realizada com sucesso");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return LISTA_AGENCIA;
	}
	public List<AgenciaValdemar> getLista(){
		List<AgenciaValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return listaRetorno;
	}
}
