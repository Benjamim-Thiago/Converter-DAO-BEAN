package br.com.btsoftwares.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.btsoftwares.drogaria.dao.CategoriaDAO;
import br.com.btsoftwares.drogaria.domain.Categoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable {
	private Categoria categoria;
	private List<Categoria> categorias;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void novo() {
		categoria = new Categoria();
	}

	public void salvar() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.merge(categoria);
			novo();
			categorias = categoriaDAO.listar();
			Messages.addFlashGlobalInfo("Categoria salva com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar");
			ex.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.excluir(categoria);
			categorias = categoriaDAO.listar();
			Messages.addFlashGlobalInfo("Categoria removida com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Ocorreu um erro ao remover categoria");
			ex.printStackTrace();

		}
	}

	public void editar(ActionEvent evento) {
		categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");
	}

	@PostConstruct
	public void listar() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categorias = categoriaDAO.listar();
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Ocorrreu um erro ao listar categorias");
			ex.printStackTrace();
		}
	}
}
