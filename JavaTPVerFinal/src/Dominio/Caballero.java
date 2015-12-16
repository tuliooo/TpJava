package Dominio;

import Conector.Jugabilidad;

public class Caballero extends Personaje{
	public Caballero(String nombre, Raza raza, int fuerza, int inteligencia,int vidaMaxima){
		super.personaje(nombre, raza, fuerza, inteligencia, vidaMaxima);
	}


	@Override
	public int accion(Usuario adversario, Jugabilidad juego) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	}

