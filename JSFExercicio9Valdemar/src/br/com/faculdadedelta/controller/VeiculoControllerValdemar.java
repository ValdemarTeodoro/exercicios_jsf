package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VeiculoDaoValdemar;
import br.com.faculdadedelta.modelo.VeiculoValdemar;

@ManagedBean
@SessionScoped
public class VeiculoControllerValdemar {
	private String CADASTRO_VEICULO = "cadastroVeiculo.xhtml";
	private String LISTA_VEICULO = "listaVeiculo.xhtml";
	
	private VeiculoValdemar veiculoValdemar = new VeiculoValdemar();
	private VeiculoDaoValdemar dao = new VeiculoDaoValdemar();
	
	public VeiculoValdemar getVeiculoValdemar() {
		return veiculoValdemar;
	}
	public void setVeiculoValdemar(VeiculoValdemar veiculoValdemar) {
		this.veiculoValdemar = veiculoValdemar;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		veiculoValdemar = new VeiculoValdemar();
	}
	public String salvar() {
		
		try {
			if(veiculoValdemar.getId()==null) {
				dao.incluir(veiculoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(veiculoValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_VEICULO;
	}
	public String editar() {
		return CADASTRO_VEICULO;
	}
	public String excluir() {
		try {
			dao.excluir(veiculoValdemar);
			exibirMensagem("exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_VEICULO;
	}
	public List<VeiculoValdemar> getLista(){
		List<VeiculoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
