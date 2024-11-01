package br.com.fiap.beans;

public class PecasVeiculo {
	private int idPeca;
	private String nome;
	private String descricaoPeca;

	public PecasVeiculo() {
		super();
	}

	public PecasVeiculo(int idPeca, String nome, String descricaoPeca) {
		super();
		this.idPeca = idPeca;
		this.nome = nome;
		this.descricaoPeca = descricaoPeca;
	}

	public int getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoPeca() {
		return descricaoPeca;
	}

	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}

}
