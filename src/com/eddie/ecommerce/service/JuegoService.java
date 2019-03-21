package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;

public interface JuegoService {
	
	//Buscador
	public Resultados<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, int startIndex, int count) throws DataException;
	
	//Buscador del index.html todos los juegos por data 
	public Resultados<Juego> findAllByDate(String idioma, int startIndex, int count) throws DataException;
	
	//Buscador del index.html todos por puntuacion
	public List<Juego> findAllByValoracion(String idioma) throws DataException;
	
	//Buscar un juego en concreto
	public Juego findById(Integer id, String idioma)throws DataException;
	
	public Juego create(Juego j) throws DataException;
	
	public boolean update(Juego j) throws DataException;
	
	public void delete(Integer id) throws DataException;
	
	//añadir comentario a juego mas su fecha
	public boolean addComent(ItemBiblioteca it)throws DataException;
	
	public boolean borrarComent(ItemBiblioteca it)throws DataException;
	
	public List<ItemBiblioteca> findByJuego(Integer idJuego) throws DataException;
	
	//Comprobar si tiene el juego en la biblioteca boton
	public List<Integer> comprobarBiblio(List<Integer> j,String email)throws DataException;
}
