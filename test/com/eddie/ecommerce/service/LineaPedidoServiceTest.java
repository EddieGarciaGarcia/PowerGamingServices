package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {

	private LineaPedidoService serviceLP=null;
	private static Logger logger=LogManager.getLogger(LineaPedidoServiceTest.class);
	public LineaPedidoServiceTest() {
		
		serviceLP=new LineaPedidoServiceImpl();
	}

		public void testfindById() { 
			
				LineaPedido lp;
				try {
					lp = serviceLP.findById(4);
					logger.debug(lp.getIdEdicion());
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		public void testfindByPedido() {
			
				List<LineaPedido> lineapedido;
				try {
					lineapedido = serviceLP.findByPedido(2);
					for(LineaPedido lp : lineapedido){
						logger.debug(lp.getIdEdicion()+" "+lp.getPrecio());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		}
		
		public void create() {
			LineaPedido lp=new LineaPedido();
			lp.setIdEdicion(4);
			lp.setPedido(1);
			lp.setCantidad(2);
			lp.setPrecio(200.0);
			try {
				serviceLP.create(lp);
			} catch (DuplicateInstanceException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DataException e) {
				e.printStackTrace();
			}
		}
		
		public void delete() {
			LineaPedido lp=new LineaPedido();
			lp.setNumeroLinea(1);
			try {
				serviceLP.delete(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	public static void main(String[] args) {
		LineaPedidoServiceTest test = new LineaPedidoServiceTest();
		
		test.testfindByPedido();
		test.testfindById();

	}

}
