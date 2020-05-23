package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.GrauInstrucaoDaoValdemar;
import br.com.faculdadedelta.modelo.GrauInstrucaoValdemar;


@ManagedBean
@SessionScoped
public class GrauInstrucaoControllerValdemar {

	private String CADASTRO_GRAUINSTRUCAO = "cadastroGrauInstrucao.xhtml";
	private String LISTA_GRAUINSTRUCAO = "listaGrauInstrucao.xhtml";
	
	private GrauInstrucaoValdemar grauInstrucaoValdemar = new GrauInstrucaoValdemar();
	private GrauInstrucaoDaoValdemar dao = new GrauInstrucaoDaoValdemar();
	
	public GrauInstrucaoValdemar getGrauInstrucaoValdemar() {
		return grauInstrucaoValdemar;
	}

	public void setGrauInstrucaoValdemar(GrauInstrucaoValdemar grauInstrucaoValdemar) {
		this.grauInstrucaoValdemar = grauInstrucaoValdemar;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		grauInstrucaoValdemar = new GrauInstrucaoValdemar();
	}
	
	public String salvar() {
		try {
			if(grauInstrucaoValdemar.getId()==null) {
				dao.incluir(grauInstrucaoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(grauInstrucaoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_GRAUINSTRUCAO;
	}
	public String editar() {
		return CADASTRO_GRAUINSTRUCAO;
	}
	public String excluir() {
		try {
			dao.excluir(grauInstrucaoValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_GRAUINSTRUCAO;
	}
	public List<GrauInstrucaoValdemar> getLista(){
		List<GrauInstrucaoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
