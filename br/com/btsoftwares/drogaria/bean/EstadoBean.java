package br.com.btsoftwares.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.btsoftwares.drogaria.dao.EstadoDAO;
import br.com.btsoftwares.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado> estados;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo(){
		estado = new Estado();
	}
	
	@PostConstruct
	public void listar(){
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException ex) {
			Messages.addFlashGlobalError("Erro ao listar estados");
			ex.printStackTrace();
		}
	} 
	
	public void salvar(){
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			novo();
			estados = estadoDAO.listar();
			Messages.addGlobalInfo("Estado Salvo com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Erro ao salvar estado");
			ex.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
	try {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.excluir(estado);
		estados = estadoDAO.listar();
		Messages.addGlobalInfo("Estado removido com sucesso");
	} catch (RuntimeException ex) {
		Messages.addGlobalError("Erro ao remover estado");
		ex.printStackTrace();
	}
		
	}
	
	public void editar(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}
}
