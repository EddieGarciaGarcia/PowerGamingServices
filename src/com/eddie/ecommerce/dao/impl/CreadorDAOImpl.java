package com.eddie.ecommerce.dao.impl;

import com.eddie.ecommerce.dao.CreadorDAO;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreadorDAOImpl implements CreadorDAO {

    private static Logger logger = LogManager.getLogger(CreadorDAOImpl.class);

    @Override
    public Creador findbyIdCreador(Connection conexion, Integer id) throws DataException {

        Creador creador;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		StringBuilder query;
        try {
            query= new StringBuilder();
            query.append("select id_creador, nombre");
			query.append(" from creador");
			query.append(" where id_creador= ?");

            preparedStatement = conexion.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                creador = new Creador();
            	return loadNext(resultSet,creador);
            } else {
                throw new InstanceNotFoundException("Error " + id + " id introducido incorrecto", Creador.class.getName());
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DataException(ex);
        } finally {
            JDBCUtils.closeResultSet(resultSet);
            JDBCUtils.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Creador> findAll(Connection conexion) throws DataException {
        Creador creador;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Creador> creadores;
        try {
           	query= new StringBuilder();
            query.append("select id_creador, nombre");
            query.append("from creador");

            preparedStatement = conexion.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            resultSet = preparedStatement.executeQuery();

            creadores = new ArrayList<>();
            while (resultSet.next()) {
                creador = new Creador();
                creadores.add(loadNext(resultSet, creador));
            }
            return creadores;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DataException(ex);
        } finally {
            JDBCUtils.closeResultSet(resultSet);
            JDBCUtils.closeStatement(preparedStatement);
        }
    }
    public Creador loadNext(ResultSet resultSet, Creador creador) throws SQLException {
        creador.setIdCreador(resultSet.getInt("id_creador"));
        creador.setNombre(resultSet.getString("nombre"));
        return creador;
    }
}
