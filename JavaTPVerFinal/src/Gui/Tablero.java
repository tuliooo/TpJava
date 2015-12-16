package Gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Dominio.Personaje;

public class Tablero extends JPanel{

	private Datos d1, d2;
	private VistaPersonaje personajeV;
	private VistaUsuario usuarioV;
	private ImageLabel[] cuadro;
	private JComboBox<String> cmbPersonajes1, cmbPersonajes2; 
    private JLabel lblUsuario1, lvlUsuario2;
    private Boton[] menu;
    private JPanel panel2;
    private Ventana ventana;
    private Personaje personaje1, personaje2;
    
    
    public ImageLabel[] getCuadros(){
    	return cuadros;
    }
    
    public Datos getDatos(){
    	return dato;
    }
    
    public void setDatos(Datos dato){
    	this.dato = dato;
    }
    
    

}
