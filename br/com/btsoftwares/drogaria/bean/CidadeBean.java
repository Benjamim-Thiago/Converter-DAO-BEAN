package br.com.btsoftwares.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.btsoftwares.drogaria.dao.CidadeDAO;
import br.com.btsoftwares.drogaria.dao.EstadoDAO;
import br.com.btsoftwares.drogaria.domain.Cidade;
import br.com.btsoftwares.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		try {
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalInfo("Ocorreu um erro ao gerar uma nova cidade");
			ex.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (Exception ex) {
			Messages.addFlashGlobalError("Ocorreu um ao tentar listar as cidades");
			ex.printStackTrace();
		}
	}

	public void salvar() {
	try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);

			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			cidades = cidadeDAO.listar();

			Messages.addFlashGlobalInfo("cidade salva com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar cidade");
			ex.printStackTrace();
		}
		
	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalInfo("Ocorreu um erro ao selecionar a cidade");
			ex.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);

			cidades = cidadeDAO.listar();
			Messages.addFlashGlobalInfo("Cidade removida com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir cidade");
			ex.printStackTrace();
		}
	}
}
