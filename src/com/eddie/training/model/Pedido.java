package com.eddie.training.model;

public class Pedido implements ValueObject{
	private	Integer numeroLinea=null;
	private Integer numeroPedido=null;
	
	
	public Pedido() {
		super();
	}
	public Integer getNumeroLinea() {
		return numeroLinea;
	}
	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	public Integer getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	
}
