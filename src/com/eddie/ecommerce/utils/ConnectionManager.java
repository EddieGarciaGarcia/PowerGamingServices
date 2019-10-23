package com.eddie.ecommerce.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager	 {
	
	private static Logger logger = LogManager.getLogger(ConnectionManager.class.getName());
	
	/*private static ResourceBundle dbConfiguration = ResourceBundle.getBundle("DBConfiguration");*/
	//IP clase:10.53.124.212:3306
	private static final String DRIVER_CLASS_NAME_PARAMETER = "com.mysql.cj.jdbc.Driver";
	private static final String URL_PARAMETER = "jdbc:mysql://localhost:3306/powergaming?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER_PARAMETER = "root";
	private static final String PASSWORD_PARAMETER = "root";

	//  Database credentials
	private static String url;
	private static String user;
	private static String password;
	private static final Integer TAMANHOMAX= 50;
	private static final Integer TAMANHOMIN=1;
	
	private static ComboPooledDataSource poolConexiones=null;

	static {

		try {
			// Carga el driver directamente, sin pool 
			//Class.forName(JDBC_DRIVER);
			/*String driverClassName = dbConfiguration.getString(DRIVER_CLASS_NAME_PARAMETER);
			url = dbConfiguration.getString(URL_PARAMETER);
			user = dbConfiguration.getString(USER_PARAMETER);
			password = dbConfiguration.getString(PASSWORD_PARAMETER);*/
			
			 poolConexiones = new ComboPooledDataSource();
			 poolConexiones.setDriverClass(DRIVER_CLASS_NAME_PARAMETER);
			 poolConexiones.setJdbcUrl(URL_PARAMETER);
			 poolConexiones.setUser(USER_PARAMETER);
			 poolConexiones.setPassword(PASSWORD_PARAMETER);
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
