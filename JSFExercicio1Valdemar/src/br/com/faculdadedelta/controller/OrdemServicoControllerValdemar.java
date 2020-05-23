package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.OrdemServicoDaoValdemar;
import br.com.faculdadedelta.modelo.ClienteValdemar;
import br.com.faculdadedelta.modelo.OrdemServicoValdemar;
import br.com.faculdadedelta.modelo.ServicoValdemar;

@ManagedBean
@SessionScoped
public class OrdemServicoControllerValdemar {
	private String CADASTRO_ORDEM = "cadastroOrdem.xhtml";
	private String LISTA_ORDEM = "listaOrdem.xhtml";
	private OrdemServicoValdemar ordem = new OrdemServicoValdemar();
	private OrdemServicoDaoValdemar dao = new OrdemServicoDaoValdemar();
	private ClienteValdemar clienteSelecionado = new ClienteValdemar();
	private ServicoValdemar servicoSelecionado = new ServicoValdemar();
	
	public OrdemServicoValdemar getOrdem() {
		return ordem;
	}
	public void setOrdem(OrdemServicoValdemar ordem) {
		this.ordem = ordem;
	}
	public ClienteValdemar getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(ClienteValdemar clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	public ServicoValdemar getServicoSelecionado() {
		return servicoSelecionado;
	}
	public void setServicoSelecionado(ServicoValdemar servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		ordem = new OrdemServicoValdemar();
		clienteSelecionado = new ClienteValdemar();
		servicoSelecionado = new ServicoValdemar();
	}	
	
	public String salvar() {
		try {
			if(ordem.getId()==null) {
				ordem.setCliente(clienteSelecionado);
				ordem.setServico(servicoSelecionado);
				dao.Incluir(ordem);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				ordem.setCliente(clienteSelecionado);
				ordem.setServico(servicoSelecionado);
				dao.alterar(ordem);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_ORDEM;
	}
	
	public String editar() {
		clienteSelecionado = ordem.getCliente();
		servicoSelecionado = ordem.getServico();
		return CADASTRO_ORDEM;
	}
	
	public String excluir() {
		try {
			dao.excluir(ordem);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_ORDEM;
	}
	public List<OrdemServicoValdemar> getLista(){
		List<OrdemServicoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
