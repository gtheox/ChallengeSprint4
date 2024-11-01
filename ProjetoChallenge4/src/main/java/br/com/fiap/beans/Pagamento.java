package br.com.fiap.beans;

public class Pagamento {
	
	private int idPagamento;
	private int valor;
	private int data;
	private String formaPagamento;
	
	public Pagamento() {
		super();
	}
	public Pagamento(int idPagamento, int valor, int data, String formaPagamento) {
		super();
		this.idPagamento = idPagamento;
		this.valor = valor;
		this.data = data;
		this.formaPagamento = formaPagamento;
	}
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
