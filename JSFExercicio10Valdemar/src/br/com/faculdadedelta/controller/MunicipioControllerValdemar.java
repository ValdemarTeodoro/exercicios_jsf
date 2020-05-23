package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MunicipioDaoValdemar;
import br.com.faculdadedelta.modelo.MunicipioValdemar;
import br.com.faculdadedelta.modelo.UFValdemar;

@ManagedBean
@SessionScoped
public class MunicipioControllerValdemar {
	private String CADASTRO_MUNICIPIO = "cadastroMunicipio.xhtml";
	private String LISTA_MUNICIPIO = "listaMunicipio.xhtml";
	
	private MunicipioValdemar municipio = new MunicipioValdemar();
	private MunicipioDaoValdemar dao = new MunicipioDaoValdemar();
	private UFValdemar ufSelecionado = new UFValdemar();
	
	public MunicipioValdemar getMunicipio() {
		return municipio;
	}
	public void setMunicipio(MunicipioValdemar municipio) {
		this.municipio = municipio;
	}
	public UFValdemar getUfSelecionado() {
		return ufSelecionado;
	}
	public void setUfSelecionado(UFValdemar ufSelecionado) {
		this.ufSelecionado = ufSelecionado;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		ufSelecionado = new UFValdemar();
		municipio = new MunicipioValdemar();
	}
	public String salvar() {
		try {
			if(municipio.getId()==null) {
				municipio.setUfValdemar(ufSelecionado);
				dao.incluir(municipio);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				municipio.setUfValdemar(ufSelecionado);
				dao.alterar(municipio);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação");
		}
		return CADASTRO_MUNICIPIO;
	}
	public String editar() {
		ufSelecionado = municipio.getUfValdemar();
		return CADASTRO_MUNICIPIO;
	}
	public String excluir() {
		try {
			dao.excluir(municipio);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação");
		}
		return LISTA_MUNICIPIO;
	}
	public List<MunicipioValdemar> getLista(){
		List<MunicipioValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
}
