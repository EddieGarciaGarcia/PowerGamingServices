package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.training.dao.EdicionDAO;
import com.eddie.training.dao.JuegoDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.exceptions.DataException;
import com.eddie.training.model.Edicion;

public class EdicionDAOImpl implements EdicionDAO{
	
	private FormatoDAOImpl formatoDAO=null;
	private TipoEdicionDAOImpl tipoEdicionDAO=null;
	private JuegoDAOImpl juegoDAO=null;
	
	public EdicionDAOImpl(){
		formatoDAO=new FormatoDAOImpl();
		tipoEdicionDAO=new TipoEdicionDAOImpl();
		juegoDAO=new JuegoDAOImpl();
	}
	
	public List<Edicion> findByIdEdicion(Integer id) 
			throws Exception{
				Edicion e=null;
				Connection connection=null;
				PreparedStatement pst=null;
				ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select id_edicion,id_juego,id_formato,id_tipo_edicion,precio from edicion where id_edicion=?";
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				int i=1;
				pst.setLong(i++, id);
				
				rs=pst.executeQuery();
				List <Edicion> ediciones=new ArrayList<Edicion>();
				if(rs.next()){
					e= LoadNext(rs);	
					ediciones.add(e);
					
				}else {
					throw new Exception("Non se encontrou o empleado "+id);
				}
				if (rs.next()) {
					throw new Exception("Juego "+id+" duplicado");
				}
				return ediciones;
			}catch (SQLException ex) {
				throw new DataException(ex);
			}finally{
				JDBCUtils.closeConnection(connection);
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
			}

		}
	
	public Edicion create(Edicion e) {
		return e;
	}
	
	public boolean update(Edicion e) {
		return false;
		}
		

	public void delete(Edicion e) {}
	
	public Edicion LoadNext(ResultSet rs) 
			throws Exception{
				int i=1;
				
				int idEdicion=rs.getInt(i++);
				int idFormato=rs.getInt(i++);
				int idTipoEdicion=rs.getInt(i++);
				double precio=rs.getDouble(i++);
			
				Edicion e=new Edicion();
				
				
				e.setId(idEdicion);
				e.setIdFormato(idFormato);
				e.setIdTipoEdicion(idTipoEdicion);
				e.setPrecio(precio);
				return e;
		}
}
