package Dominio;

import Conector.Jugabilidad;

public class Clerigo extends Personaje{

	private String dios;
	
	public Clerigo(String dios){
		this.dios = dios;
	}
	
	public String getDios(){
		return dios;
	}
	
	public void setDios(String dios){
		this.dios = dios;
	}
	
	public Clerigo(String nombre, Raza raza, int fuerza, int inteligencia, int vidaMaxima){
		super.personaje(nombre, raza, fuerza, inteligencia, vidaMaxima);
	}

	@Override
	public int accion(Usuario adversario, Jugabilidad juego) {
		juego.curar(this, adversario);
		return 0;
	}
}
