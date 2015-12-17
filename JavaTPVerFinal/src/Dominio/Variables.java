package Dominio;

public class Variables {

	private	int columnas = 10;
	private	int filas = 10;
	private int cuadros = filas*columnas;
	private int hechizos = 6;
	private int cant_dados = 1;
	//clerigo
	private int clerigo_fuerza_min = 18; //mayor o igual
	private int clerigo_inteligencia_min = 12; //mayor o igual
	private int clerigo_inteligencia_max = 16; //menor o igual	
	//mago
	private int mago_fuerza_max = 15; //menor o igual
	private int mago_inteligencia_min = 17; //mayor o igual
	
	
	
	public int getMago_fuerza_max() {
		return mago_fuerza_max;
	}
	public void setMago_fuerza_max(int fuerza_max) {
		this.mago_fuerza_max = fuerza_max;
	}
	public int getMago_inteligencia_min() {
		return mago_inteligencia_min;
	}
	public void setMago_inteligencia_min(int inteligencia_min) {
		this.mago_inteligencia_min = inteligencia_min;
	}
	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	
	public int getClerigo_fuerza_min() {
		return clerigo_fuerza_min;
	}
	public void setClerigo_fuerza_min(int clerigo_fuerza_min) {
		this.clerigo_fuerza_min = clerigo_fuerza_min;
	}
	public int getClerigo_inteligencia_min() {
		return clerigo_inteligencia_min;
	}
	public void setClerigo_inteligencia_min(int clerigo_inteligencia_min) {
		this.clerigo_inteligencia_min = clerigo_inteligencia_min;
	}
	public int getClerigo_inteligencia_max() {
		return clerigo_inteligencia_max;
	}
	public void setClerigo_inteligencia_max(int clerigo_inteligencia_max) {
		this.clerigo_inteligencia_max = clerigo_inteligencia_max;
	}
	
	public int getCuadros() {
		return cuadros;
	}

	public int getHechizos() {
		return hechizos;
	}
	public void setHechizos(int hechizos) {
		if(hechizos<=cuadros){
			this.hechizos = hechizos;
		}else{
			System.out.println("La cantidad de hechizos debe ser menor o igual a "+cuadros);
		}
	}
	public int getCant_dados() {
		return cant_dados;
	}
	public void setCant_dados(int cant_dados) {
		this.cant_dados = cant_dados;
	}
}
