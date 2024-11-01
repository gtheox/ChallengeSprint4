package br.com.fiap.beans;

public class Orcamento {
	
	private int idOrcamento;
	private int valor;
	
	public Orcamento() {
		super();
	}
	public Orcamento(int idOrcamento, int valor) {
		super();
		this.idOrcamento = idOrcamento;
		this.valor = valor;
	}
	public int getIdOrcamento() {
		return idOrcamento;
	}
	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	

}
