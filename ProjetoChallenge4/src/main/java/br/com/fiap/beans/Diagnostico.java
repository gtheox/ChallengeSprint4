package br.com.fiap.beans;

public class Diagnostico {
	private int idProblema;
	private String descricao;
	private int gravidade;
	private String tipo;

	public Diagnostico() {
		super();
	}

	public Diagnostico(int idProblema, String descricao, int gravidade, String tipo) {
		super();
		this.idProblema = idProblema;
		this.descricao = descricao;
		this.gravidade = gravidade;
		this.tipo = tipo;
	}

	public int getIdProblema() {
		return idProblema;
	}

	public void setIdProblema(int idProblema) {
		this.idProblema = idProblema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getGravidade() {
		return gravidade;
	}

	public void setGravidade(int gravidade) {
		this.gravidade = gravidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
