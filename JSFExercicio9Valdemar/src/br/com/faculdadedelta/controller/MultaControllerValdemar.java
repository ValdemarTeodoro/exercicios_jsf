package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MultaDaoValdemar;
import br.com.faculdadedelta.modelo.InfracaoValdemar;
import br.com.faculdadedelta.modelo.MotoristaValdemar;
import br.com.faculdadedelta.modelo.MultaValdemar;
import br.com.faculdadedelta.modelo.VeiculoValdemar;

@ManagedBean
@SessionScoped
public class MultaControllerValdemar {
	private String CADASTRO_MULTA = "cadastroMulta.xhtml";
	private String LISTA_MULTA = "listaMulta.xhtml";
	
	private MultaDaoValdemar dao = new MultaDaoValdemar();
	private MultaValdemar multaValdemar = new MultaValdemar();
	private InfracaoValdemar infracaoSelecionada = new InfracaoValdemar();
	private VeiculoValdemar veiculoSelecionado = new VeiculoValdemar();
	private MotoristaValdemar motoristaSelecionado = new MotoristaValdemar();
	public MultaValdemar getMultaValdemar() {
		return multaValdemar;
	}
	public void setMultaValdemar(MultaValdemar multaValdemar) {
		this.multaValdemar = multaValdemar;
	}
	public InfracaoValdemar getInfracaoSelecionada() {
		return infracaoSelecionada;
	}
	public void setInfracaoSelecionada(InfracaoValdemar infracaoSelecionada) {
		this.infracaoSelecionada = infracaoSelecionada;
	}
	public VeiculoValdemar getVeiculoSelecionado() {
		return veiculoSelecionado;
	}
	public void setVeiculoSelecionado(VeiculoValdemar veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}
	public MotoristaValdemar getMotoristaSelecionado() {
		return motoristaSelecionado;
	}
	public void setMotoristaSelecionado(MotoristaValdemar motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		multaValdemar = new MultaValdemar();
		infracaoSelecionada = new InfracaoValdemar();
		veiculoSelecionado = new VeiculoValdemar();
		motoristaSelecionado = new MotoristaValdemar();
	}
	private void selecionados() {
		multaValdemar.setInfracaoValdemar(infracaoSelecionada);
		multaValdemar.setVeiculoValdemar(veiculoSelecionado);
		multaValdemar.setMotoristaValdemar(motoristaSelecionado);
	}
	public String salvar() {
		
		try {
			if(multaValdemar.getId()==null) {
				selecionados();
				dao.incluir(multaValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				selecionados();
				dao.alterar(multaValdemar);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_MULTA;
	}
	public String editar() {
		
		infracaoSelecionada = multaValdemar.getInfracaoValdemar();
		veiculoSelecionado = multaValdemar.getVeiculoValdemar();
		motoristaSelecionado = multaValdemar.getMotoristaValdemar();
		return CADASTRO_MULTA;
	}
	public String excluir() {
		try {
			dao.excluir(multaValdemar);
			exibirMensagem("exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_MULTA;
	}
	public List<MultaValdemar> getLista(){
		List<MultaValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
