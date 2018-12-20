package com.eddie.training.model.comparator;

import java.util.Comparator;

import com.eddie.training.model.Usuario;

public class UsuarioComparator implements Comparator<Usuario>{

		public static UsuarioComparator instace=null;
		
		public static final UsuarioComparator getInstance() {
			if (instace == null) {
				instace= new UsuarioComparator();
			}
			return instace;
		}
	
		@Override
		public int compare(Usuario u1, Usuario u2) {
	
			return u1.getNombre().compareTo(u2.getNombre());
		}
		
		private UsuarioComparator() {};

}
