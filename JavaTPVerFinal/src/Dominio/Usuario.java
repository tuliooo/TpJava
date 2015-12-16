package Dominio;

import java.util.ArrayList;

import BD.Conector;

public class Usuario {

	private ArrayList<Personaje> personaje;
    private String nombre;
    private String password;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Personaje> getPersonaje() {
		return personaje;
	}
	public void setPersonaje(ArrayList<Personaje> personaje) {
		this.personaje = personaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addPersonaje(Personaje personaje){
		this.personaje.add(personaje);
	}
	
	public Usuario(String nombre){
		this.personaje = new ArrayList<Personaje>();
		this.nombre = nombre;
	}
	
	public Usuario(String nombre, String password)
	{
		this.nombre = nombre;
		this.password = password;
		this.personaje = new ArrayList<Personaje>();
	}
	
	public void save(){
	    Conector con = new Conector();
	    con.connect();
	    con.saveUsuario(this);
	    con.close();
	}


	public void verPersonajes(){
	    Conector con = new Conector();
	    con.connect();
	    con.mostrarPersonajes();
	    con.close();
	}
	
	@Override
	public String toString() {
		return "Usuario [Nombre= " + nombre + "]";
	}
    
	
}

