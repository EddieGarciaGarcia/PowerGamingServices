package com.eddie.training.dao;

import com.eddie.training.model.Plataforma;

public interface PlataformaDAO {

	public Plataforma findbyIdFormato(Integer id) throws Exception;
	
	public Plataforma create(Plataforma p) throws Exception;
	
	public void delete(Plataforma p) throws Exception;
}
