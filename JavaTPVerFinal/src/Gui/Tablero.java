package Gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Conector.Jugabilidad;
import Dominio.Personaje;
import Dominio.Usuario;

public class Tablero extends JPanel{

	private Datos d1, d2;
	private PersonajeVista personajeV;
	private UsuarioVista usuarioV;
	private ImageLabel[] cuadro;
	private JComboBox<String> cmbPersonajes1, cmbPersonajes2; 
    private JLabel lblUsuario1, lblUsuario2; 

    private Boton[] menu;
    private JPanel panel;
    private Ventana ventana;
    private Personaje personaje1, personaje2;
    
    
    public ImageLabel[] getCuadros(){
    	return cuadro;
    }
    
    public Datos getDatos1(){
    	return d1;
    }
    
    public void setDatos1(Datos dato){
    	this.d1 = dato;
    }
    
    public Datos getDatos2(){
    	return d2;
    }
    
    public void setDatos2(Datos dato){
    	this.d2 = dato;
    }
    public void setDatos(Datos datos, String nombre, String raza, String tipo, int fuerza, int inteligencia, int vida){
    	datos.setNombre("Name: "+nombre);
    	datos.setFuerza("Fuerza: "+fuerza);
    	datos.setInteligencia("Inteligencia: "+inteligencia);
    	datos.setVida(vida);
    	
    }
    
    public Tablero(int cuadros, int filas, int columnas){
    	cuadro = new ImageLabel[cuadros];
    	menu= new Boton[2];
    	
    	generarCuadros(cuadros, filas, columnas);
    	
    	cmbPersonajes1 = new JComboBox<String>();
    	cmbPersonajes2 = new JComboBox<String>();
    	
    	lblUsuario1 = new JLabel(); 
    	lvlUsuario2 = new JLabel();
    	
    	d1 = new Datos();
    	d2 = new Datos();  	
    }
    
    public void datosNull(){
    	d1 = null;
    	d2 = null;
    }
    
    public JComboBox<String> getCmbPersonajes1(){
    	return cmbPersonajes1;
    }
    
    public JComboBox<String> getCmbPersonajes2(){
    	return cmbPersonajes2;
    }
    
    public void vaciarCuadros(){
    	for(int i=0;i<cuadro.length;i++){
    		cuadro[i].setImage(new ImageIcon(""));
    	}
    }
  
    public void cargarDatos(Jugabilidad juego){
    	panel = new JPanel();
    	panel.setLayout(new GridLayout(4,2));
    	String[] listaMenu ={"Reiniciar","Dados"};
    	for(int i=0;i<menu.length;i++){
    		menu[i] = new Boton(listaMenu[i]);
    		panel.add(menu[i]);
    	}
    	
    	menu[0].addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			reiniciarJuego(juego);
    		}
    	});
    	
    	menu[1].addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			rndDados(juego);
    		}
    	});
    	
    	cmbPersonajes1.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			if(cmbPersonajes1.getItemCount()>1){
    				Personaje personaje = juego.getPersonaje(cmbPersonajes1.getSelectedItem().toString());
    			   if(personaje!=null){
    				   setDatos(d1, personaje.getRaza().toString(), juego.personajeTipo(personaje), personaje.getNombre(), personaje.getFuerza(), personaje.getInteligencia(), personaje.getVida());
    			   }
    			}
    		}
    	}); 
    	
    	
    	cmbPersonajes2.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			if(cmbPersonajes2.getItemCount()>1){
    				Personaje personaje = juego.getPersonaje(cmbPersonajes2.getSelectedItem().toString());
    			   if(personaje!=null){
    				   setDatos(d2, personaje.getRaza().toString(), juego.personajeTipo(personaje), personaje.getNombre(), personaje.getFuerza(), personaje.getInteligencia(), personaje.getVida());
    			   }
    			}
    		}
    	}); 
 
    panel.add(lblUsuario1);
    panel.add(lblUsuario2);
    
    cmbPersonajes2.setEnabled(false);
    panel.add(cmbPersonajes1);
    panel.add(cmbPersonajes2);
    panel.add(d1);
    panel.add(d2);
    	
    this.add(panel, BorderLayout.NORTH);
    }
    
    public Personaje getPj1(){
    	return personaje1;
    }
    public void setPj1(Personaje personaje1){
    	this.personaje1 = personaje1;
    }

    public Personaje getPj2(){
    	return personaje2;
    }
    public void setPj2(Personaje personaje2){
    	this.personaje2 = personaje2;
    }
    
    public void setLabelUsr1(JLabel lblUsuario1){
    	this.lblUsuario1 = lblUsuario1;
    }
    
    public void setLabelUsr2(JLabel lblUsuario2){
    	this.lblUsuario2 = lblUsuario2;
    }
    
    public void actualizarVida(Usuario p, int vida){
    	String cadena="Usuario 1:"+p.getNombre();
    	System.out.println("actualizo a "+vida);
    	
//>>>>>>>>>>>      	//RARO\\          <<<<<<<<<<<<<<<<<<<<
    	if(cadena.compareTo(lblUsuario1.getText())==0){
    		d1.setVida(vida);
    	}
    	else{
    		d2.setVida(vida);
    	}
    }
    
public void cargaComboBox1(Jugabilidad juego, Usuario usr){
	cmbPersonajes1.removeAllItems();
	for(Personaje pjs : juego.obtenerPersonaje(usr)){
		if(pjs.getVida()>0){
			cmbPersonajes1.addItem(pjs.getNombre());
		}
	}
}

public void cargaComboBox2(Jugabilidad juego, Usuario usr){
	cmbPersonajes2.removeAllItems();
	for(Personaje pjs : juego.obtenerPersonaje(usr)){
		if(pjs.getVida()>0){
			cmbPersonajes2.addItem(pjs.getNombre());
		}
	}
}
/////Get label usr1 y set label usr1
public void eliminarComboBox(JComboBox<String> cmb){
	cmb.removeItem(cmb.getSelectedItem().toString());
}
    
//ACT cmb
public void actualizarComboBox(Jugabilidad juego, Usuario usr){
	
}

}
