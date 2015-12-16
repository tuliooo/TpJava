package Gui;

import javax.swing.JFrame;

public class Ventana extends JFrame{
 private int y;
 private int x;
 
 public Ventana(String titulo, int x, int y){
	 this.x = x;
	 this.y = y;
	 this.setTitle(titulo);
	 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 
 }
 
 public void mostrar(){
	 this.setSize(x, y);
	 this.setVisible(true);
 }

}
