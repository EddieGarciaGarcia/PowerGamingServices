package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Formato;

import java.util.List;

public interface FormatoService {
	List<Formato> findbyIdsFormato(List<Integer> ids, String idioma) throws DataException;
	//Lista de Formatos
	List<Formato> findAll(String idioma) throws DataException;
}
