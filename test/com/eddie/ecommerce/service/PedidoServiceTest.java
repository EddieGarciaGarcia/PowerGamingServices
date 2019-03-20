package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	
	private PedidoService serviceP=null;
	private static Logger logger=LogManager.getLogger(PedidoServiceTest.class);
	public PedidoServiceTest() {
		
		serviceP=new PedidoServiceImpl();
		
		
	}

		public void testFindById() { 
			
				Pedido p;
				try {
					p = serviceP.findByID(2);
					logger.debug(p.getEmail());
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			
		}
		public void testfindEmail() {
			
				List<Pedido> pedido;
				try {
					pedido = serviceP.findByEmail("corralciclo@gmail.com");
					for(Pedido p:pedido){
						logger.debug(p.getFecha_pedido());
					}
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			
		}
		public void create() {
			Pedido p=new Pedido();
			
			try {
				serviceP.create(p);
			} catch (DuplicateInstanceException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DataException e) {
				e.printStackTrace();
			}
		}
		
		public void delete() {
			try {
				serviceP.delete(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	public static void main(String[] args) {
		PedidoServiceTest test = new PedidoServiceTest();
	

	}

}
