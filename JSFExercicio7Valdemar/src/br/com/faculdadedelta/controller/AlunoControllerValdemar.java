package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.AlunoDaoValdemar;
import br.com.faculdadedelta.modelo.AlunoValdemar;
import br.com.faculdadedelta.modelo.GrauInstrucaoValdemar;


@ManagedBean
@SessionScoped
public class AlunoControllerValdemar {

	private String CADASTRO_ALUNO = "cadastroAluno.xhtml";
	private String LISTA_ALUNO = "listaAluno.xhtml";
	private AlunoDaoValdemar dao = new AlunoDaoValdemar();
	private GrauInstrucaoValdemar grauInstrucaoSelecionado = new GrauInstrucaoValdemar();
	private AlunoValdemar alunoValdemar = new AlunoValdemar();
	
	public GrauInstrucaoValdemar getGrauInstrucaoSelecionado() {
		return grauInstrucaoSelecionado;
	}
	public void setGrauInstrucaoSelecionado(GrauInstrucaoValdemar grauInstrucaoSelecionado) {
		this.grauInstrucaoSelecionado = grauInstrucaoSelecionado;
	}
	public AlunoValdemar getAlunoValdemar() {
		return alunoValdemar;
	}
	public void setAlunoValdemar(AlunoValdemar alunoValdemar) {
		this.alunoValdemar = alunoValdemar;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		alunoValdemar = new AlunoValdemar();
		grauInstrucaoSelecionado = new GrauInstrucaoValdemar();
	}	
	
	public String salvar() {
		try {
			if(alunoValdemar.getId()==null) {
				alunoValdemar.setGrauInstrucaoValdemar(grauInstrucaoSelecionado);
				dao.incluir(alunoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				alunoValdemar.setGrauInstrucaoValdemar(grauInstrucaoSelecionado);
				dao.alterar(alunoValdemar);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_ALUNO;
	}
	
	public String editar() {
		grauInstrucaoSelecionado = alunoValdemar.getGrauInstrucaoValdemar();
		return CADASTRO_ALUNO;
	}
	
	public String excluir() {
		try {
			dao.excluir(alunoValdemar);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return LISTA_ALUNO;
	}
	public List<AlunoValdemar> getLista(){
		List<AlunoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
