package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ServicoDaoValdemar;
import br.com.faculdadedelta.modelo.ServicoValdemar;

@ManagedBean
@SessionScoped
public class ServicoControllerValdemar {
	private String CADASTRO_SERVICO = "cadastroServico.xhtml";
	private String LISTA_SERVICO ="listaServico.xhtml";
	private ServicoValdemar servicoValdemar = new ServicoValdemar();
	private ServicoDaoValdemar dao = new ServicoDaoValdemar();
	
	
	public ServicoValdemar getServicoValdemar() {
		return servicoValdemar;
	}

	public void setServicoValdemar(ServicoValdemar servicoValdemar) {
		this.servicoValdemar = servicoValdemar;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		servicoValdemar = new ServicoValdemar();
	}
	public String salvar() {
		
		try {
			if(servicoValdemar.getId()==null) {
				dao.incluir(servicoValdemar);
				exibirMensagem("Inclusao realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(servicoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação!" + e.getMessage());
		}
		return CADASTRO_SERVICO;
	}
	
	public String editar() {
		return CADASTRO_SERVICO;
	}
	
	public String excluir() {
		try {
				dao.excluir(servicoValdemar);
				exibirMensagem("Exclusão realizada com sucesso!");
				limparCampos();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação!" + e.getMessage());
		}
		return LISTA_SERVICO;
	}
	public List<ServicoValdemar> getLista(){
		List<ServicoValdemar> listaRetorno = new ArrayList<>();
		try {
				listaRetorno = dao.lista();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
		
		return listaRetorno;
	}
}
