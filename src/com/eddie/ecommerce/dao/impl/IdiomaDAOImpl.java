package com.eddie.ecommerce.dao.impl;

import com.eddie.ecommerce.dao.IdiomaDAO;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAOImpl implements IdiomaDAO {

    private static Logger logger = LogManager.getLogger(IdiomaDAOImpl.class);

    @Override
    public Idioma findById(Connection conexion, String id, String idiomaS) throws DataException {
        Idioma idioma = new Idioma();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        try {
            query = new StringBuilder();
            query.append("select id_idioma, nombre ");
            query.append("from idioma_idiomaweb ");
            query.append(" where id_idioma= ? and id_idioma_web like ?");

            preparedStatement = conexion.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, idiomaS);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return loadNext(resultSet, idioma);
            } else {
                throw new InstanceNotFoundException("Error " + id + " id introducido incorrecto", Idioma.class.getName());
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
    public List<Idioma> findAll(Connection conexion, String idiomaS) throws DataException {
        Idioma idioma = new Idioma();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
		List<Idioma> idiomas;
		try {
			query = new StringBuilder();
			query.append("select id_idioma, nombre ");
			query.append("from idioma_idiomaweb ");
			query.append("where id_idioma_web like ?");

            preparedStatement = conexion.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, idiomaS);
            resultSet = preparedStatement.executeQuery();

			idiomas = new ArrayList<>();
            while (resultSet.next()) {
                idiomas.add(loadNext(resultSet, idioma));
            }
            return idiomas;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DataException(ex);
        } finally {
            JDBCUtils.closeResultSet(resultSet);
            JDBCUtils.closeStatement(preparedStatement);
        }
    }


    @Override
    public List<Idioma> findByJuego(Connection conexion, Integer idJuego, String idiomaS) throws DataException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		StringBuilder query;
		Idioma idioma = new Idioma();
		List<Idioma> idiomas;
		try {
			query = new StringBuilder();
			query.append("select iiw.id_idioma, iiw.nombre ");
			query.append("from juego_idioma ji ");
			query.append("inner join idioma i on ji.id_idioma=i.id_idioma ");
			query.append("inner join idioma_idiomaweb iiw on iiw.id_idioma=i.id_idioma ");
			query.append("where ji.id_juego = ? AND iiw.id_idioma_web like ? ");

            preparedStatement = conexion.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setInt(1, idJuego);
            preparedStatement.setString(2, idiomaS);

            resultSet = preparedStatement.executeQuery();

            // Recupera la pagina de resultados
			idiomas= new ArrayList<>();
            while (resultSet.next()) {
                idiomas.add(loadNext(resultSet, idioma));
            }
            return idiomas;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataException(e);
        } finally {
            JDBCUtils.closeResultSet(resultSet);
            JDBCUtils.closeStatement(preparedStatement);
        }
    }

    public Idioma loadNext(ResultSet resultSet, Idioma idioma) throws SQLException {
        idioma.setIdIdioma(resultSet.getString("id_idioma"));
        idioma.setNombre(resultSet.getString("nombre"));
        return idioma;
    }


}
