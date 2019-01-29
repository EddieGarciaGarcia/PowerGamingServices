package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;

public interface JuegoService {
	
	//Buscador
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma) throws DataException, SQLException;
	
	//Buscador del index.html todos los juegos por data 
	public List<Juego> findAllByDate() throws DataException, SQLException;
	
	//Buscador del index.html todos por puntuacion
	public List<Juego> findAllByValoraci�n() throws DataException, SQLException;
	
	//Buscar un juego en concreto
	public Juego findById(Integer id, String idioma)throws DataException,SQLException;
	
	public Juego create(Juego j) throws DataException,SQLException;
	
	public boolean update(Juego j) throws DataException,SQLException;
	
	public void delete(Integer id) throws DataException,SQLException;
	
	//a�adir comentario a juego mas su fecha
	public boolean addComent(ItemBiblioteca it)throws DataException,SQLException;
	
	public boolean borrarComent(ItemBiblioteca it)throws DataException,SQLException;
}
