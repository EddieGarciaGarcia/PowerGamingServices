package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;

public interface JuegoServiceDAO {
	
	//Buscador
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma) throws Exception;
	
	//Buscador del index.html todos los juegos por data 
	public List<Juego> findAllByDate() throws Exception;
	
	//Buscador del index.html todos por puntuacion
	public List<Juego> findAllByValoración() throws Exception;
	
	//Buscar un juego en concreto
	public Juego findById(Integer id)throws Exception;
	
	public Juego create(Juego j) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Integer id) throws Exception;
}
