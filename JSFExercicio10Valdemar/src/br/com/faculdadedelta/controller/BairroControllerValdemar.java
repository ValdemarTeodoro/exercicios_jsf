package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BairroDaoValdemar;
import br.com.faculdadedelta.modelo.BairroValdemar;
import br.com.faculdadedelta.modelo.MunicipioValdemar;

@ManagedBean
@SessionScoped
public class BairroControllerValdemar {
	private String CADASTRO_BAIRRO = "cadastroBairro.xhtml";
	private String LISTA_BAIRRO = "listaBairro.xhtml";
	
	private MunicipioValdemar municipioSelecionado = new MunicipioValdemar();
	private BairroValdemar bairroValdemar = new BairroValdemar();
	private BairroDaoValdemar dao = new BairroDaoValdemar();
	public MunicipioValdemar getMunicipioSelecionado() {
		return municipioSelecionado;
	}
	public void setMunicipioSelecionado(MunicipioValdemar municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}
	public BairroValdemar getBairroValdemar() {
		return bairroValdemar;
	}
	public void setBairroValdemar(BairroValdemar bairroValdemar) {
		this.bairroValdemar = bairroValdemar;
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		municipioSelecionado = new MunicipioValdemar();
		bairroValdemar = new BairroValdemar();
	}
	public String salvar() {
		try {
			if(bairroValdemar.getId()==null) {
				bairroValdemar.setMunicipioValdemar(municipioSelecionado);
				dao.incluir(bairroValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				bairroValdemar.setMunicipioValdemar(municipioSelecionado);
				dao.alterar(bairroValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opeiração, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_BAIRRO;
	}
	public String editar() {
		municipioSelecionado = bairroValdemar.getMunicipioValdemar();
		return CADASTRO_BAIRRO;
	}
	public String  excluir() {
		try {
			dao.excluir(bairroValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opeiração, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_BAIRRO;
	}
	public List<BairroValdemar> getLista(){
		List<BairroValdemar> listaRetrono = new ArrayList<>();
		try {
			listaRetrono = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opeiração, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetrono;
	}
}
