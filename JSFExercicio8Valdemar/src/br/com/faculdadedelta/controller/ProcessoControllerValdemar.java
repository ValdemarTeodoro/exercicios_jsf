package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProcessoDaoValdemar;
import br.com.faculdadedelta.modelo.ProcessoValdemar;

@ManagedBean
@SessionScoped
public class ProcessoControllerValdemar {
	private String CADASTRO_PROCESSO = "cadastroProcesso.xhtml";
	private String LISTA_PROCESSO = "listaProcesso.xhtml";
	
	private ProcessoValdemar processoValdemar = new ProcessoValdemar();
	private ProcessoDaoValdemar dao = new ProcessoDaoValdemar();
	public ProcessoValdemar getProcessoValdemar() {
		return processoValdemar;
	}
	public void setProcessoValdemar(ProcessoValdemar processoValdemar) {
		this.processoValdemar = processoValdemar;
	}
	
	private void exibriMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		processoValdemar = new ProcessoValdemar();
	}
	
	public String salvar() {
		try {
			if(processoValdemar.getId()==null) {
				dao.incluir(processoValdemar);
				exibriMensagem("inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(processoValdemar);
				exibriMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibriMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_PROCESSO;
	}
	public String editar() {
		return CADASTRO_PROCESSO;
	}
	public String excluir() {
		try {
			dao.excluir(processoValdemar);
			exibriMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibriMensagem("Erro ao realizar a operaçao, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_PROCESSO;
	}
	public List<ProcessoValdemar> getLista(){
		List<ProcessoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibriMensagem("Erro ao realizar a operaçao, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
}
