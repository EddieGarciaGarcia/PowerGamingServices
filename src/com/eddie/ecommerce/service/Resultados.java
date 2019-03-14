package com.eddie.ecommerce.service;

import java.util.List;

public class Resultados<T> {
	
	private List<T> resultados= null;
	private Integer total;
	
	public Resultados() {
		
	}

	public List<T> getResultados() {
		return resultados;
	}

	public void setResultados(List<T> resultados) {
		this.resultados = resultados;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
