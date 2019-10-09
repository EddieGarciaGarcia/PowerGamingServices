package com.eddie.ecommerce.model.comparator;

import com.eddie.ecommerce.model.Usuario;

import java.util.Comparator;

public class UsuarioComparatorByFechaNacimiento  implements Comparator<Usuario>{
	public UsuarioComparatorByFechaNacimiento() {}
	
	@Override
	public int compare(Usuario u1, Usuario u2) {
		
		return u1.getFechaNacimiento().compareTo(u2.getFechaNacimiento());
	}
}
