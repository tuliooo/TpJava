package Gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Savepoint;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BD.Conector;
import Conector.Jugabilidad;
import Dominio.Caballero;
import Dominio.Clerigo;
import Dominio.Mago;
import Dominio.Personaje;
import Dominio.Raza;
import Dominio.Usuario;

public class PersonajeVista extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreJugador;
	private Jugabilidad sistema;
	private JComboBox<String> comboTipo,comboRaza;
	JLabel fuerzaLabel;
	private JLabel inteligenciaLabel;
	private String personajeNombre;
	private String personajeTipo;
	private int personajeFuerza;
	private int personajeInteligencia;
	Raza personajeRaza;
	private String personajeRazaS;
	JFrame frame;
	private Usuario user;
	
	public JTextField fuerzaText;
	public JTextField inteligenciaText;
	
	public void nuevoPersonaje(){
		JPanel titulo = new JPanel(), 
			   guardar = new JPanel(),
			   formulario = new JPanel();
		guardar.setLayout(new GridLayout(1,1));
		formulario.setLayout(new GridLayout(5,2));
		
		nombreJugador = new JTextField();
		
		fuerzaLabel = new JLabel("Fuerza");
		inteligenciaLabel = new JLabel("Inteligencia");
		personajeRaza = Raza.ELFO;
		
		JLabel 	texto = new JLabel(),
				l1 = new JLabel(),
				l2 = new JLabel(),
				l3 = new JLabel(),
				l4 = new JLabel(),
				l5 = new JLabel(),
				l6 = new JLabel();
		fuerzaText = new JTextField(0);
		inteligenciaText = new JTextField(0);
		
		Boton b2 = new Boton("Agregar Personaje");
		Boton b3 = new Boton("Terminar");
		
		texto.setText("Nuevo Personaje");
		l1.setText("Nombre del Personaje: ");
		l2.setText("Raza");
		l3.setText("Tipo de Personaje: ");
		l4.setText("Fuerza: ");
		l5.setText("Inteligencia: ");
		
		String tipos[] = { "Arquero", "Caballero", "Clerigo", "Mago" };
		comboTipo = new JComboBox<String>(tipos);
		
		ItemListener itemListener2 = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {
					System.out.println(itemEvent.getItem());
					personajeTipo = (String) itemEvent.getItem();
					if(personajeTipo == "Clerigo")
					{
						String fuerzaInput, inteligenciaInput; 
						int fuerzaInputI = 0;
						int inteligenciaInputI = 0;
						do {
							fuerzaInput = JOptionPane.showInputDialog(frame, "Ingrese fuerza entre 18 y 20");
							System.out.println(fuerzaInput);
							if (isNumeric(fuerzaInput))
								{
									fuerzaInputI = Integer.parseInt(fuerzaInput);
								}
						} while (fuerzaInputI < 18 || fuerzaInputI > 20 );
						
						do {
							inteligenciaInput = JOptionPane.showInputDialog(frame, "Ingrese inteligencia entre 12 y 16");
							System.out.println(inteligenciaInput);
							if (isNumeric(inteligenciaInput))
								{
								inteligenciaInputI = Integer.parseInt(inteligenciaInput);
								}
						} while (inteligenciaInputI < 12 || inteligenciaInputI > 16 );
						
						personajeInteligencia = inteligenciaInputI;
						personajeFuerza = fuerzaInputI;
						String Dios = JOptionPane.showInputDialog(frame, "Ingrese el nombre de su Dios");
						fuerzaText.setText(String.valueOf(personajeFuerza));
						inteligenciaText.setText(String.valueOf(personajeInteligencia));
					}
					System.out.println(personajeTipo);
					if(personajeTipo == "Mago")
					{
						String fuerzaInput, inteligenciaInput; 
						int fuerzaInputI = 0;
						int inteligenciaInputI = 0;
						do {
							fuerzaInput = JOptionPane.showInputDialog(frame, "Ingrese fuerza entre 0 y 15");
							System.out.println(fuerzaInput);
							if (isNumeric(fuerzaInput))
								{
									fuerzaInputI = Integer.parseInt(fuerzaInput);
								}
						} while (fuerzaInputI < 0 || fuerzaInputI > 15 );
						
						do {
							inteligenciaInput = JOptionPane.showInputDialog(frame, "Ingrese inteligencia entre 17 y 20");
							System.out.println(inteligenciaInput);
							if (isNumeric(inteligenciaInput))
								{
								inteligenciaInputI = Integer.parseInt(inteligenciaInput);
								}
						} while (inteligenciaInputI < 17 || inteligenciaInputI > 20 );
						
						personajeInteligencia = inteligenciaInputI;
						personajeFuerza = fuerzaInputI;
						fuerzaText.setText(String.valueOf(personajeFuerza));
						inteligenciaText.setText(String.valueOf(personajeInteligencia));
					}
					
					if(personajeTipo == "Arquero")
					{
						Random rnd = new Random();
						int fuerza = (int)(rnd.nextDouble() * 5 + 15);
						int inteligencia = (int)(rnd.nextDouble() * 5 + 10);
						personajeFuerza = fuerza;
						personajeInteligencia = inteligencia;
						fuerzaText.setText(String.valueOf(personajeFuerza));
						inteligenciaText.setText(String.valueOf(personajeInteligencia));
					}
					
					if(personajeTipo == "Caballero")
					{
						Random rnd = new Random();
						int fuerza = (int)(rnd.nextDouble() * 5 + 15);
						int inteligencia = (int)(rnd.nextDouble() * 5 + 10);
						personajeFuerza = fuerza;
						personajeInteligencia = inteligencia;
						fuerzaText.setText(String.valueOf(personajeFuerza));
						inteligenciaText.setText(String.valueOf(personajeInteligencia));
					}
				}

			}
		};
		comboTipo.addItemListener(itemListener2);
		personajeTipo= comboTipo.getSelectedItem().toString();
		
		String razas[] = { Raza.ELFO.toString(), Raza.ENANO.toString(),
				Raza.HUMANO.toString(), Raza.ORCO.toString() };
		comboRaza = new JComboBox<String>(razas);
		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {
					System.out.println(itemEvent.getItem()+ "RAZA");
					String unItem = (String) itemEvent.getItem();
					personajeRaza = Raza.valueOf(unItem);
					personajeRazaS = personajeRaza.toString();
				}
			}
		};
		
		comboRaza.addItemListener(itemListener1);
		personajeRazaS = comboRaza.getSelectedItem().toString();
		
		formulario.add(l1);
		formulario.add(nombreJugador);
		
		formulario.add(l2);
		formulario.add(comboRaza);
		formulario.add(l3);
		formulario.add(comboTipo);
		formulario.add(l4);
		formulario.add(fuerzaText);
		formulario.add(l5);
		formulario.add(inteligenciaText);
		
		
		guardar.add(b3);
		guardar.add(b2);

		titulo.add(texto);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Personaje unPersonaje = null;
				personajeNombre = nombreJugador.getText();
				personajeInteligencia = Integer.parseInt(inteligenciaText.getText());
				personajeFuerza = Integer.parseInt(fuerzaText.getText());
				//posPersonaje = new Posicion(0, 0);
				// ACA CREO EL PERSONAJE
				switch (personajeTipo) {
				case "Caballero":
					unPersonaje = new Caballero(personajeNombre,personajeRaza , personajeFuerza, personajeInteligencia, 100);
					break;

				case "Mago":
					unPersonaje = new Mago(personajeNombre,personajeRaza , personajeFuerza, personajeInteligencia, 100);
					break;

				case "Clerigo":
					unPersonaje = new Clerigo(personajeNombre,personajeRaza , personajeFuerza, personajeInteligencia, 100);	
					break;
				default:
					break;
				}
				guardarPersonaje(user, unPersonaje);
				save(unPersonaje, personajeTipo);
			}
		});
		
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				otroJugador(user);
			}
		});
		
		this.setLayout(new BorderLayout());
		this.add(titulo, BorderLayout.NORTH);
		this.add(guardar, BorderLayout.SOUTH);
		this.add(formulario, BorderLayout.CENTER);
	}
	
	public void save(Personaje unPersonaje, String tipo){
        Conector con = new Conector();
        con.connect();
        System.out.println(unPersonaje);
        con.savePersonaje(unPersonaje,tipo);
        con.close();
        
    }
	

	public String getNombreJugador() {
		return nombreJugador.getText();
	}

	public void setNombreJugador(JTextField nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	
	public PersonajeVista(Jugabilidad sistema,Usuario player){
		this.sistema = sistema;
		this.user = player;
	}
	
	public PersonajeVista(Jugabilidad sistema){
		this.sistema = sistema;
	}
	
	public void guardarPersonaje(Usuario player, Personaje unPersonaje){
		if(nombreJugador.getText().length()!=0){
			sistema.agregaPersonajeAUsuario(player,unPersonaje);
			nombreJugador.setText("");
			}
	}
	
	public void otroJugador(Usuario player){
		sistema.nuevoJugador();
	}
	
	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}