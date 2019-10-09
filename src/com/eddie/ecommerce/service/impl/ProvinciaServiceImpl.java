package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.ProvinciaDAO;
import com.eddie.ecommerce.dao.impl.ProvinciaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;
import com.eddie.ecommerce.service.ProvinciaService;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProvinciaServiceImpl implements ProvinciaService{
	
	private static Logger logger=LogManager.getLogger(ProvinciaServiceImpl.class);
	
	ProvinciaDAO pdao=null;
	
	public ProvinciaServiceImpl() {
		pdao=new ProvinciaDAOImpl();
	}

	@Override
	public List<Provincia> findAllByIdPais(Integer idPais) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idPais);
		}
		List<Provincia> p=null;
		boolean commit=false;
		Connection c=null;
		try {
		c= ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p=pdao.findAllByIdPais(c, idPais);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

}
