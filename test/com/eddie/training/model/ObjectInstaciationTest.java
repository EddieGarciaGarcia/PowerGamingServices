package com.eddie.training.model;

import java.util.Date;

public class ObjectInstaciationTest {

	public static void main(String[] args) {
			
		/*Juego juego1= new Juego("Perfect","Muy buen juego","i5,nvidia 890");
		
		juego1.setNombre("Perfect");
		juego1.setIdJuego(1);
		juego1.setFechaLanzamiento(new Date());
		StringBuilder sb=new StringBuilder(" ");
		
		Juego juego2= new Juego();
		juego2.setNombre("No mans sky");
		juego2.setIdJuego(2);
		juego2.setFechaLanzamiento(new Date());
		
		Juego[] arrayjuegos= new Juego[2];
		arrayjuegos[0]=juego1;
		arrayjuegos[1]=juego2;
		*/
		Usuario u1=new Usuario("Eddie", "Garcia","Garcia", "masquisieras","eddie_t@hotmail.com");
		
		Administrador u2=new Administrador("Jorge","piñe","vice","pass","jordepiñevice@hotmail.com",new Date(),new Direccion(),5,4,true,null);
		/*
		for(Juego juego : arrayjuegos) {
			System.out.println("Hola "+juego.getNombre()+" Bienvenido a Jal Training!"+juego1.getFechaLanzamiento());
		}
		System.out.println("Primer objecto "+juego1.getIdJuego()+" "+juego1.getNombre()+" se lanzo el "+juego1.getFechaLanzamiento());
		
		System.out.println(juego2.toString());
		
		Juego j1=new Juego(1,"Mad max","Primer juego de todos");
		Juego j2=new Juego(1,"Diablo 3","Segundo juego de todos");
		
		Juego[] j= new Juego[2];
		j[1]=j1;
		j[2]=j2;
		
		System.out.println(j1.toString());
		System.out.println(""+j2);
		System.out.println(j1.getIdJuego()==j2.getIdJuego());*/
		System.out.println(u1.toString());
		System.out.println(u2.toString());
		
		
		
		
	}

}
