package Dominio;

import java.util.ArrayList;

public class Mapa {
	private ArrayList<Integer> hechizos;
	
	public Mapa(){
		hechizos = new ArrayList<Integer>();
	}
	
	public void borrarHechizos(){
		hechizos = null;
	}

	public void agregarHechizo(int cuadros){
		hechizos.add(cuadros);
	}
	
	public void borrarHechizo(int cuadros){
		hechizos.remove(cuadros);
	}
	
	public int buscadorHechizos(int posicion){
	int i = 0;
	for(int pos : hechizos){
		if(pos==posicion){
			return i;
		}
		i++;
	}
	return -1;
	}
	
	public void listarHechizos(){
		for(int pos : hechizos){
			System.out.println("Hechizo en cuadro: "+pos);
		}
	}
	
}
