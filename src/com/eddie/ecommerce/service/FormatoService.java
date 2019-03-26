package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Formato;

public interface FormatoService {
	public Formato findbyIdFormato(Integer id, String idioma) throws InstanceNotFoundException, DataException;
	//Lista de Formatos
	public List<Formato> findAll(String idioma) throws DataException;
}
