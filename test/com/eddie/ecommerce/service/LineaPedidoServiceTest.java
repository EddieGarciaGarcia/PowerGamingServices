package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {

	private LineaPedidoService serviceLP=null;
	
	public LineaPedidoServiceTest() {
		
		serviceLP=new LineaPedidoServiceImpl();
	}

		public void testfindById() { 
			try {
				LineaPedido lp= serviceLP.findById(4);
				System.out.println(lp.getIdEdicion());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindByPedido() {
			try {
				List<LineaPedido> lineapedido;
				lineapedido = serviceLP.findByPedido(2);
				for(LineaPedido lp : lineapedido){
					System.out.println(lp.getIdEdicion()+" "+lp.getPrecio());
				}
				
			} catch (Exception e) {
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
