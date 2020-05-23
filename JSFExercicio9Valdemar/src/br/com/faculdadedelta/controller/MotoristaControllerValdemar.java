package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MotoristaDaoValdemar;
import br.com.faculdadedelta.modelo.MotoristaValdemar;

@ManagedBean
@SessionScoped
public class MotoristaControllerValdemar {
	private String CADASTRO_MOTORISTA = "cadastroMotorista.xhtml";
	private String LISTA_MOTORISTA = "listaMotorista.xhtml";
	
	private MotoristaValdemar motoristaValdemar = new MotoristaValdemar();
	private MotoristaDaoValdemar dao = new MotoristaDaoValdemar();
	public MotoristaValdemar getMotoristaValdemar() {
		return motoristaValdemar;
	}
	public void setMotoristaValdemar(MotoristaValdemar motoristaValdemar) {
		this.motoristaValdemar = motoristaValdemar;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		motoristaValdemar = new MotoristaValdemar();
	}
	public String salvar() {
		
		try {
			if(motoristaValdemar.getId()==null) {
				dao.incluir(motoristaValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(motoristaValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_MOTORISTA;
	}
	public String editar() {
		return CADASTRO_MOTORISTA;
	}
	public String excluir() {
		try {
			dao.excluir(motoristaValdemar);
			exibirMensagem("exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_MOTORISTA;
	}
	public List<MotoristaValdemar> getLista(){
		List<MotoristaValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
