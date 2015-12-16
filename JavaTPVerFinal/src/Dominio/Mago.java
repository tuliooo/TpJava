package Dominio;

import java.util.ArrayList;

import Conector.Jugabilidad;

public class Mago extends Personaje{
private ArrayList<String> hechizos = new ArrayList<String>();
private String[] hechizo={"h1", "h2"};

public void aprenderHechizo(String hechizo){
	hechizos.add(hechizo);
}

public ArrayList<String> getHechizos(){
	return hechizos;
}

public void setHechizos(ArrayList<String> hechizos){
	this.hechizos = hechizos;
}

public Mago(String nombre, Raza raza, int fuerza, int inteligencia, int vidaMaxima) {
	super.personaje(nombre, raza, fuerza, inteligencia, vidaMaxima);
}

private int random(int from, int to){
	return from + (int)(Math.random()*to);
}

public String getNombreHechizo(){
	int indice = random(0,hechizo.length);
	return hechizo[indice];
}

@Override
public int accion(Usuario adversario, Jugabilidad juego) {
	// TODO Auto-generated method stub
	return 0;
}

//Action
}
