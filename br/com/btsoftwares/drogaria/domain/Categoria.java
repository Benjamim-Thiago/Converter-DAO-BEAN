package br.com.btsoftwares.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Categoria extends GenericDomain {
	
	private static final long serialVersionUID = 1L;
	@Column(length = 35, nullable = false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
