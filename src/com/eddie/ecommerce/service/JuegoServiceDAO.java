package com.eddie.ecommerce.service;

import com.eddie.ecommerce.model.Juego;

public interface JuegoServiceDAO {
	
	public Juego addComent(Integer idJuego, String email, String comentario) throws Exception;
	
	public Juego updateComent(Integer idJuego, String email) throws Exception;
	
	public Juego puntuacion(Integer idJuego, String email, Double puntuacion) throws Exception;
	
	public Juego addJuegoBiblioteca(Integer idJuego, String email) throws Exception;
	
	public Juego deleteJuegoBiblioteca(Integer idJuego, String email) throws Exception;
	
	public Juego findAllBiblioteca(Integer idJuego, String email) throws Exception;
}
