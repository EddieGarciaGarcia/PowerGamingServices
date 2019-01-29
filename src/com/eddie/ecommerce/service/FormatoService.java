package com.eddie.ecommerce.service;



import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Formato;

public interface FormatoService {
	public Formato findbyIdFormato(Integer id, String idioma) throws InstanceNotFoundException, DataException, SQLException ;
	//Lista de Formatos
	public List<Formato> findAll(String idioma) throws DataException, SQLException ;
}
