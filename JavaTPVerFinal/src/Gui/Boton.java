package Gui;

import javax.swing.JButton;

public class Boton extends JButton{
	private String nombre;

	public Boton(String nom) {
		this.nombre = nom;
		this.setText(nombre);
	}
	public String getNom(){
		return nombre;
	}
	public void steNom(String nom){
       this.nombre = nom;		
	}
	
	public Boton(){
		
	}
}
