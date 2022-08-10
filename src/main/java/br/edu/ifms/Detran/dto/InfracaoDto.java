package br.edu.ifms.Detran.dto;

import java.io.Serializable;

import br.edu.ifms.Detran.model.Infracao;

public class InfracaoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private int pontos;
	
	private float valor;
	
	public InfracaoDto() {
		// TODO Auto-generated constructor stub
	}

	public InfracaoDto(Infracao obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.pontos = obj.getPontos();
		this.valor = obj.getValor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	
	
}
