package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ClienteDaoValdemar;
import br.com.faculdadedelta.modelo.ClienteValdemar;

@ManagedBean
@SessionScoped
public class ClienteControllerValdemar {
	private String CADASTRO_CLIENTE = "cadastroCliente.xhtml";
	private String LISTA_CLIENTE = "listaOrdem.xhtml";
	private ClienteValdemar cliente = new ClienteValdemar();
	private ClienteDaoValdemar dao = new ClienteDaoValdemar();
	public ClienteValdemar getCliente() {
		return cliente;
	}
	public void setCliente(ClienteValdemar cliente) {
		this.cliente = cliente;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		cliente = new ClienteValdemar();
	}
	
	public String salvar() {
		
		try {
			if(cliente.getId()==null) {
				dao.incluir(cliente);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(cliente);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_CLIENTE;
	}
	
	public String editar() {
		return CADASTRO_CLIENTE;
	}
	
	public String excluir() {
		try {
				dao.excluir(cliente);
				exibirMensagem("Exclusão realizda com sucesso!");
				limparCampos();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
		return LISTA_CLIENTE;
	}
	
	public List<ClienteValdemar> getLista(){
		List<ClienteValdemar> listaRetorno = new ArrayList<>();
		try {
				listaRetorno = dao.lista();
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
		
		return listaRetorno;
	}
}
