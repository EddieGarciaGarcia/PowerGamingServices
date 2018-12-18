package com.eddie.training.model;

public class LineaPedido {
	private Integer numeroLinea=null;
	private Integer idJuego=null;
	private Integer idPrecio=null;
	private Integer cantidad=null;
	private static final Double IVA=0.0d;
	
	
	public LineaPedido() {
		
	}
	public Integer getNumeroLinea() {
		return numeroLinea;
	}
	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public Integer getIdPrecio() {
		return idPrecio;
	}
	public void setIdPrecio(Integer idPrecio) {
		this.idPrecio = idPrecio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public static Double getIva() {
		return IVA;
	}
	
	
	
}
