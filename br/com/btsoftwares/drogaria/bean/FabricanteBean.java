package br.com.btsoftwares.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.btsoftwares.drogaria.dao.FabricanteDAO;
import br.com.btsoftwares.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao listar fabricantes");
			ex.printStackTrace();
		}

	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			novo();
			fabricantes = fabricanteDAO.listar();
			Messages.addGlobalInfo("Fabricante salvo com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao cadastrar Fabriante");
			ex.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			fabricanteDAO.excluir(fabricante);
			fabricantes = fabricanteDAO.listar();
			Messages.addGlobalInfo("Fabricante removido com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao excluir cadastro.");
			ex.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

	}
}
