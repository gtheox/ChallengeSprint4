package br.com.fiap.beans;

public class Servico {
	private int idServico;
	private String nome;
	private String descricao;

	public Servico() {
		super();
	}

	public Servico(int idServico, String nome, String descricao) {
		super();
		this.idServico = idServico;
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
