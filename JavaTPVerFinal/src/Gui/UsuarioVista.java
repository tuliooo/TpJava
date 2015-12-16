package Gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BD.Conector;
import Conector.Jugabilidad;
import Dominio.Usuario;


public class UsuarioVista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username_field;
	private JPasswordField password_field;
	private Jugabilidad sistema;
	private String name;
	private String pass;
	public Usuario elUsuario;
	
	public void nuevoPlayer(){
		JPanel titulo = new JPanel(), 
			   guardar = new JPanel(),
			   formulario = new JPanel();
		guardar.setLayout(new GridLayout(2,1));
		formulario.setLayout(new GridLayout(6,2));
		
		username_field = new JTextField();
		password_field = new JPasswordField();
	    password_field.setBounds(70, 100, 120, 20);
	    this.pass = String.valueOf(password_field.getPassword());
		
		JLabel 	texto = new JLabel(),
				passLbl = new JLabel(),
				l1 = new JLabel();
		
		
		
		Boton loginBtn = new Boton("LOGIN");
		Boton registBtn = new Boton("Registrarse");

		texto.setText("Nuevo Jugador");
		l1.setText("Nombre del Jugador: ");
		passLbl.setText("Clave:");
		formulario.add(l1);
		formulario.add(username_field);
		formulario.add(passLbl);
		formulario.add(password_field);
		
		guardar.add(loginBtn);
		guardar.add(registBtn);
		titulo.add(texto);
		
		Conector co = new Conector();
	  	 co.connect();
		final ArrayList<Usuario> listaUsuarios = co.traeUsuarios();
	  	co.close();
	  	
		loginBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	name = username_field.getText();
	        	pass = String.valueOf(password_field.getPassword());
	        	Usuario unUsuario = new Usuario(name, pass);
	        	if(buscaUsuario(unUsuario, listaUsuarios))
	        	{
	        		System.out.println("Login");
	        		elUsuario = unUsuario;
	        		System.out.println(unUsuario);
	        		listaUsuarios.add(elUsuario);
	        		sistema.agregarJugador(elUsuario.getNombre());
	        	}else{
	        	}
	        }
	    });
	    
	    registBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	name = username_field.getText();
	        	pass = String.valueOf(password_field.getPassword());
	        	Usuario unUsuario = new Usuario(name, pass);
	        	if(!buscaUsuario(unUsuario, listaUsuarios))
	        	{
	        		if(!name.equals(""))
	        		{
	        			registraUsuario(unUsuario);
	        			elUsuario = unUsuario;
	            		sistema.agregarJugador(elUsuario.getNombre());
	        			
	        		}else
	        		{
	        			System.out.println("Ingrese un nombre");
	        		}
	        		
	        	}else{
	        		System.out.println("El usuario ya existe");
	        	}
	        }
	    });

		this.setLayout(new BorderLayout());
		this.add(titulo, BorderLayout.NORTH);
		this.add(guardar, BorderLayout.SOUTH);
		this.add(formulario, BorderLayout.CENTER);
	}
	
	public boolean buscaUsuario(Usuario unUsuario, ArrayList<Usuario> lista)
	{
		for (Usuario usuario : lista) {
			if (usuario.getNombre().compareTo(unUsuario.getNombre()) == 0)
			{
				if(usuario.getPassword().compareTo(unUsuario.getPassword()) == 0)
				{
					return true;
					
				}else
				{
					System.out.println("El password ingresado es incorrecto");
				}
			}
		}
		return false;
	}

	public void registraUsuario(Usuario unUsuario)
	{
		Conector co = new Conector();
	 	 co.connect();
	 	 if(co.saveUsuario(unUsuario))
	 		{
	 		System.out.println("Usuario Grabado");
	 		}
	 
	}

	public Usuario TraeUsuario()
	{
		return elUsuario;
	 
	}

	public String getNombreJugador() {
		return username_field.getText();
	}

	public void setNombreJugador(JTextField nombreJugador) {
		this.username_field = nombreJugador;
	}
	
	public UsuarioVista(Jugabilidad sistema){
		this.sistema = sistema;
	}
	
	public void guardarJugador(){
		if(username_field.getText().length()!=0){
			sistema.agregarJugador(username_field.getText());
		}
	}
}