package com.eddie.ecommerce.dao.Utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager	 {
	
	private static Logger logger = LogManager.getLogger(ConnectionManager.class.getName());
	
	//IP clase:10.53.124.212:3306
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/powergaming?"
			+ "useUnicode=true&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";

	//  Database credentials
	private static final String USER = "root";
	private static final String PASS = "root";
	private static final Integer TAMANHOMAX= 50;
	private static final Integer TAMANHOMIN=1;
	
	private static ComboPooledDataSource poolConexiones=null;

	static {

		try {
			// Carga el driver directamente, sin pool 
			//Class.forName(JDBC_DRIVER);
			
			 poolConexiones = new ComboPooledDataSource();
			 poolConexiones.setDriverClass(JDBC_DRIVER);
			 poolConexiones.setJdbcUrl(DB_URL);
			 poolConexiones.setUser(USER);
			 poolConexiones.setPassword(PASS);
			 poolConexiones.setMinPoolSize(TAMANHOMIN);
			 poolConexiones.setMaxPoolSize(TAMANHOMAX);
			 
			 
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e); 
		}

	}

	private ConnectionManager() {}

	public final static Connection getConnection() throws SQLException {
		//return DriverManager.getConnection(DB_URL, USER, PASS);
		return poolConexiones.getConnection();
	}
	
}