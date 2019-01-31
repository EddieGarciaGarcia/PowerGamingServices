package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	
	private PedidoService serviceP=null;
	
	public PedidoServiceTest() {
		
		serviceP=new PedidoServiceImpl();
		
		
	}

		public void testFindById() { 
			try {
				Pedido p= serviceP.findByID(2);
				System.out.println(p.getEmail());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindEmail() {
			try {
				List<Pedido> pedido;
				pedido = serviceP.findByEmail("corralciclo@gmail.com");
				for(Pedido p:pedido){
					System.out.println(p.getFecha_pedido());
				}
				
			} catch (Exception e) {
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
