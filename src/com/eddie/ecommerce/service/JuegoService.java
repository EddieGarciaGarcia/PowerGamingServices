package com.eddie.ecommerce.service;

import com.eddie.ecommerce.model.Resultados;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;

import java.util.List;

public interface JuegoService {

	//Buscador
	Resultados<Juego> findByJuegoCriteria(JuegoCriteria juegoCriteria, String idioma, int startIndex, int count) throws DataException;

	//Buscador del index.html todos los juegos por data
	Resultados<Juego> findAllByDate(String idioma, int startIndex, int count) throws DataException;

	//Buscador
	List<Juego> findByJuegoCriteria(JuegoCriteria juegoCriteria, String idioma) throws DataException;
	
	//Buscador del index.html todos los juegos por data 
	List<Juego> findAllByDate(String idioma) throws DataException;
	
	//Buscador del index.html todos por puntuacion
	List<Juego> findAllByValoracion(String idioma) throws DataException;
	
	List<Juego> findByIDs(List<Integer> ids, String idioma)throws DataException;
	
	//Buscar un juego en concreto
	Juego findById(Integer id,String email, String idioma)throws DataException;
	
	Juego create(Juego juego) throws DataException;
	
	boolean update(Juego juego) throws DataException;
	
	boolean delete(Integer id) throws DataException;
	
	//a�adir comentario a juego mas su fecha
	boolean addComent(ItemBiblioteca itemBiblioteca)throws DataException;
	
	boolean borrarComent(ItemBiblioteca itemBiblioteca)throws DataException;
	
	List<ItemBiblioteca> findByJuego(Integer idJuego) throws DataException;

	Integer puntuacion(Integer idJuego)throws DataException;
}
