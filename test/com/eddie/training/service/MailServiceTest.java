package com.eddie.training.service;

public class MailServiceTest {

	public static void main(String[] args) {
		
		MailService mailService=new MailService();
		
		String mensaje;
		mensaje="<html>Bienvenido a Power Gaming te informaremos de lo que puedes hacer en estos momentos en nuestra pagina web</html>";

		String[] to=new String[2];
		String usuario1="eddietuenti@gmail.com";
		String usuario2="eddie_taboada@hotmail.com";
		to[0]=usuario1;
		to[1]=usuario2;
		
		mailService.sendMail(mensaje, to);
	}

}
