package Dominio;

public abstract class Personaje implements Accion{

	//Variables
	private String nombre;
	private Raza raza;
	private int fuerza;
	private int fuerzaMaxima;
	private int inteligencia;
	private int inteligenciaMaxima;
	private int vida;
	private int vidaMaxima;
	private int cuadros = 0;	
	
	//Constructor
	public void personaje(String nombre, Raza raza, int fuerza, int inteligencia, int vidaMaxima) {
		this.nombre = nombre;
		this.raza = raza;
		this.fuerza = fuerza;
		this.fuerzaMaxima = fuerza;
		this.inteligencia = inteligencia;
		this.inteligenciaMaxima = inteligencia;
		this.vida = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
	}
	
	//Gets y Sets
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Raza getRaza() {
		return raza;
	}
	public void setRaza(Raza raza) {
		this.raza = raza;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getFuerzaMaxima() {
		return fuerzaMaxima;
	}
	public void setFuerzaMaxima(int fuerzaMaxima) {
		this.fuerzaMaxima = fuerzaMaxima;
	}
	public int getInteligencia() {
		return inteligencia;
	}
	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}
	public int getInteligenciaMaxima() {
		return inteligenciaMaxima;
	}
	public void setInteligenciaMaxima(int inteligenciaMaxima) {
		this.inteligenciaMaxima = inteligenciaMaxima;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}
	public int getCuadros() {
		return cuadros;
	}
	public void setCuadro(int cuadros) {
		this.cuadros += cuadros;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + ", raza=" + raza + ", fuerza=" + fuerza + ", fuerzaMaxima="
				+ fuerzaMaxima + ", inteligencia=" + inteligencia + ", inteligenciaMaxima=" + inteligenciaMaxima
				+ ", vida=" + vida + ", vidaMaxima=" + vidaMaxima + ", cuadro=" + cuadros + "]";
	}

	
	
	
}
