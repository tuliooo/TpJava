package Gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Datos extends JPanel {

	private JLabel nombre, fuerza, inteligencia;
	private JProgressBar vida;
    private JPanel panelDatos, panelVida;

    public JProgressBar getVida(){
    	return vida;
    }
    
    public void setVida(int vida){
    	this.vida.setValue(vida);
    }

   
    public void setFuerza(String fuerza){
	   this.fuerza.setText(fuerza);
   }
  
    public void setInteligencia(String inteligencia){
    	this.inteligencia.setText(inteligencia);
    }

    

    public void setNombre(String nom){
    	nombre.setText(nom);
    }
    
    public Datos(){
    	panelDatos = new JPanel();
    	panelVida = new JPanel();
    	
    	panelDatos.setLayout(new GridLayout(3,1));
    	panelVida.setLayout(new GridLayout(1,1));
    	
    	nombre = new JLabel();
    	fuerza = new JLabel();
        inteligencia = new JLabel();
        
        vida = new JProgressBar();
        
        panelDatos.add(nombre);
        panelDatos.add(fuerza);
        panelDatos.add(inteligencia);
        
        
        panelVida.add(vida);
        
        this.setLayout(new BorderLayout());
        this.add(panelDatos);
        
    }
    
    public Datos(String nombre, int fuerza, int inteligencia, int vida){
    	
    	this.nombre = new JLabel();
        this.fuerza = new JLabel();
    	this.inteligencia = new JLabel();
    	this.vida = new JProgressBar();
    	
    	this.nombre.setText("Name: "+nombre);
        this.fuerza.setText("Fuerza: "+fuerza);
        this.inteligencia.setText("Inteligencia: "+inteligencia);
        
        this.vida.setValue(vida);
        
        JPanel panelDatos = new JPanel();
        JPanel panelVida = new JPanel();
        
        panelDatos.setLayout(new GridLayout(3,1));
        panelVida.setLayout(new GridLayout(1,1));
        
        panelDatos.add(this.nombre);
        panelDatos.add(this.fuerza);
        panelDatos.add(this.inteligencia);
        
        panelVida.add(this.vida);
        
        //RARO
        panelDatos.add(panelVida);
        
        this.setLayout(new BorderLayout());
        this.add(panelDatos);
        
    }
    
}
