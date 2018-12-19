package com.eddie.training.dao;

import java.sql.Date;
import java.util.List;

import com.eddie.training.model.Edicion;
import com.eddie.training.model.Juego;

public interface JuegoDAO {
	
	public List<Edicion> findByJuegoAll(Integer id) throws Exception;
	
	public List<Juego> findByCreador(Integer id_creador) throws Exception;
	
	public Juego create(Juego j) throws Exception;
	
	public List<Juego> findByFechaNombre(Date fecha, String nombre) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Juego j) throws Exception;
}
