package br.com.btsoftwares.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.btsoftwares.drogaria.dao.CidadeDAO;
import br.com.btsoftwares.drogaria.dao.EstadoDAO;
import br.com.btsoftwares.drogaria.dao.PessoaDAO;
import br.com.btsoftwares.drogaria.domain.Cidade;
import br.com.btsoftwares.drogaria.domain.Estado;
import br.com.btsoftwares.drogaria.domain.Pessoa;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@ManagedBean()
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Estado> estados;

	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			// Salva
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar("nome");

			pessoa = new Pessoa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();
			Messages.addGlobalInfo("Salvo com sucesso!!!");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			ex.printStackTrace();

		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			//estados = estadoDAO.buscarEstadoPorCidade(pessoa.getCidade().getEstado());

			//CidadeDAO cidadeDAO = new CidadeDAO();
			//cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());

		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um pessoa");
			ex.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			pessoas = pessoaDAO.listar("nome");
			Messages.addGlobalInfo("Excluido com sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Erro ao excluir pessoa");
		}
	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

	public void validaCPF() {
		try {
			CPFValidator validator = new CPFValidator();
			validator.assertValid(pessoa.getCpf());
			salvar();
		} catch (InvalidStateException ex) {
			ex.printStackTrace();
			Messages.addFlashGlobalError("CPF digitado contem erros.");
		}

	}
}
