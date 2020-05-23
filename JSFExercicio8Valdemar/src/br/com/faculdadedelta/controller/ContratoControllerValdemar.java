package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ContratoDaoValdemar;
import br.com.faculdadedelta.modelo.ContratoValdemar;
import br.com.faculdadedelta.modelo.ProcessoValdemar;

@ManagedBean
@SessionScoped
public class ContratoControllerValdemar {
	private String CADASTRO_CONTRATO = "cadastroContrato.xhtml";
	private String LISTA_CONTRATO = "listaContrato.html";
	private ContratoValdemar contratoValdemar = new ContratoValdemar();
	private ContratoDaoValdemar dao = new ContratoDaoValdemar();
	private ProcessoValdemar processoSelecionado = new ProcessoValdemar();
	
	public ContratoValdemar getContratoValdemar() {
		return contratoValdemar;
	}
	public void setContratoValdemar(ContratoValdemar contratoValdemar) {
		this.contratoValdemar = contratoValdemar;
	}
	public ProcessoValdemar getProcessoSelecionado() {
		return processoSelecionado;
	}
	public void setProcessoSelecionado(ProcessoValdemar processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}
	
	private void exibirMensagem (String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		processoSelecionado = new ProcessoValdemar();
		contratoValdemar = new ContratoValdemar();
	}
	public String salva() {
		try {
			if(contratoValdemar.getId()==null) {
				contratoValdemar.setProcessoValdemar(processoSelecionado);
				dao.incluir(contratoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				contratoValdemar.setProcessoValdemar(processoSelecionado);
				dao.alterar(contratoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_CONTRATO;
	}
	public String editar() {
		processoSelecionado = contratoValdemar.getProcessoValdemar();
		return CADASTRO_CONTRATO;
	}
	public String excluir() {
		try {
			dao.excluir(contratoValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a Operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_CONTRATO;
	}
	public List<ContratoValdemar> getLista(){
		List<ContratoValdemar> listaRetrono = new ArrayList<>();
		try {
			listaRetrono = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a Operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetrono;
	}
}
