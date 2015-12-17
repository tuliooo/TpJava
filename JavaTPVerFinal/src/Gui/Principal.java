	package Gui;

import javax.swing.JFrame;

public class Principal extends JFrame {

	private Tablero tablero;
	public Principal(int casilleros, int filas, int columnas){
			tablero = new Tablero(casilleros,filas, columnas);
			this.add(tablero);
		}
public Tablero getTablero(){
	return tablero;
}

public void mostrar(){
	this.setTitle("Juego");
	this.setSize(800, 600);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
}
}