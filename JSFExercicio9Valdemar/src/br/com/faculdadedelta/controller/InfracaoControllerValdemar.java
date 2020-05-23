package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.InfracaoDaoValdemar;
import br.com.faculdadedelta.modelo.InfracaoValdemar;
@ManagedBean
@SessionScoped
public class InfracaoControllerValdemar {
	private String CADASTRO_INFRACAO = "cadastroInfracao.xhtml";
	private String LISTA_INFRACAO = "listaInfracao.xhtml";

	private InfracaoValdemar infracaoValdemar = new InfracaoValdemar();
	private InfracaoDaoValdemar dao = new InfracaoDaoValdemar();
	public InfracaoValdemar getInfracaoValdemar() {
		return infracaoValdemar;
	}
	public void setInfracaoValdemar(InfracaoValdemar infracaoValdemar) {
		this.infracaoValdemar = infracaoValdemar;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		infracaoValdemar = new InfracaoValdemar();
	}
	public String salvar() {
		
		try {
			if(infracaoValdemar.getId()==null) {
				dao.incluir(infracaoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(infracaoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_INFRACAO;
	}
	public String editar() {
		return CADASTRO_INFRACAO;
	}
	public String excluir() {
		try {
			dao.excluir(infracaoValdemar);
			exibirMensagem("exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_INFRACAO;
	}
	public List<InfracaoValdemar> getLista(){
		List<InfracaoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
