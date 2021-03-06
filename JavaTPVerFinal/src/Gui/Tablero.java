package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    public Ventana getVentana(){
    	return ventana;
    }
    
    public void setDatos2(Datos dato){
    	this.d2 = dato;
    }
    public void setDatos(Datos datos, String nombre, String raza, String tipo, int fuerza, int inteligencia, int vida){
    	datos.setNombre("Raza: "+nombre);
    	datos.setFuerza("Fuerza: "+fuerza);
    	datos.setInteligencia("Inteligencia: "+inteligencia);
    	datos.setTipo("Tipo: " + raza);
    	datos.setVida(vida);
    	
    }
    
    public Tablero(int cuadros, int filas, int columnas){
    	cuadro = new ImageLabel[cuadros];
    	menu= new Boton[2];
    	
    	generarCuadros(cuadros, filas, columnas);
    	
    	cmbPersonajes1 = new JComboBox<String>();
    	cmbPersonajes2 = new JComboBox<String>();
    	
    	lblUsuario1 = new JLabel(); 
    	lblUsuario2 = new JLabel();
    	
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
  
    public void cargarDatos(final Jugabilidad juego){
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
    			lanzarDados(juego);
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
/////Get label usr1, 2 y set label usr1,2
public void eliminarComboBox(JComboBox<String> cmb){
	cmb.removeItem(cmb.getSelectedItem().toString());
}
    

public void actualizarComboBox(Jugabilidad juego, Usuario usr){
	String cadena = "Jugador 1:"+usr.getNombre();
	if(cadena.compareTo(lblUsuario1.getText())==0){
		eliminarComboBox(cmbPersonajes1);
	}
	else{
		eliminarComboBox(cmbPersonajes2);
	}
}

public void generarCuadros(int cuadros,int filas, int columnas){
	//genero matriz de imagenes
	JPanel matriz = new JPanel();
	matriz.setLayout(new GridLayout(filas,columnas));
	
	for(int i=0;i<cuadros;i++){
		if(((i%columnas)==0)&&((i/columnas)%2!=0)){
			for(int j=i+(columnas-1);j>=i;j--){
				cuadro[j] = new ImageLabel();
				cuadro[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				matriz.add(cuadro[j]);
			}
			i=i+(columnas-1);
		}else{
			cuadro[i] = new ImageLabel();
			cuadro[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			matriz.add(cuadro[i]);
		}
	}
	this.setLayout(new BorderLayout());
	this.add(matriz, BorderLayout.CENTER);
}

public JLabel getLabelUsr1(){
	return lblUsuario1;
}
public JLabel getLabelUsr2(){
	return lblUsuario2;
}

public void setLabelUsr1(String lblUsr1){
	this.lblUsuario1.setText(lblUsr1);
}
public void setLabelUsr2(String lblUsr2){
	this.lblUsuario2.setText(lblUsr2);
}

private void lanzarDados(Jugabilidad sistema){
	if(lblUsuario1.getBackground()==Color.ORANGE){
		sistema.lanzar(cmbPersonajes1.getSelectedItem().toString());
	}else{
		sistema.lanzar(cmbPersonajes2.getSelectedItem().toString());
	}
}

public void activarBotonDados(){
	menu[1].setEnabled(true);
}

public void desactivarBotonDados(){
	menu[1].setEnabled(false);
}
/////>>>>>>>>>>>Agregar Extencion de imagen<<<<<<<<
public void dibujarPj(int u, String imagen){
	cuadro[u].setImage(new ImageIcon(imagen+".png"));
	}

public void desdibujarPj(int u){
	cuadro[u].setImage(new ImageIcon(""));
	}

public void reiniciarJuego(Jugabilidad juego){
	juego.inicializarJugadores();
	nuevoUsuario(juego);
}

public void inicializarCuadro(){
	for(int i=0;i<cuadro.length;i++){
		cuadro[i].setImage(new ImageIcon(""));
	}
}

public void nuevoUsuario(Jugabilidad juego){
	usuarioV = new UsuarioVista(juego);
	usuarioV.nuevoPlayer();
	
	ventana = new Ventana("Inicio de Juego", 450, 300);
	ventana.add(usuarioV);
	ventana.mostrar();
}


public void setPersonajes(Jugabilidad juego, Usuario usr){
	personajeV = new PersonajeVista(juego, usr);
	personajeV.nuevoPersonaje();
	ventana.add(personajeV);
	ventana.mostrar();
}

public void alternarFondo(){
	if(lblUsuario1.getBackground()==Color.ORANGE){
		lblUsuario1.setBackground(Color.lightGray);
		lblUsuario2.setBackground(Color.ORANGE);
		cmbPersonajes1.setEnabled(false);
		cmbPersonajes2.setEnabled(true);
	}else{
		lblUsuario2.setBackground(Color.lightGray);
		lblUsuario1.setBackground(Color.ORANGE);
		cmbPersonajes1.setEnabled(true);
		cmbPersonajes2.setEnabled(false);
	}
}

public UsuarioVista getJugador() {
	return usuarioV;
}


public void infoVentana(String titulo,String texto){
	JOptionPane.showMessageDialog(this,texto,titulo,JOptionPane.INFORMATION_MESSAGE);
}
}

