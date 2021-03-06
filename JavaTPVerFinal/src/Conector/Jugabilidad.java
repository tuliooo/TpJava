package Conector;

import java.awt.Color;
import java.awt.Dialog;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dominio.*;
import Gui.Principal;

public class Jugabilidad {
	private Principal p;
	private Mapa m;
	private Variables vars;
	private Usuario playerActivo;
	private ArrayList<Usuario> playersArray;
	private Notepad eventos;
	
	public Jugabilidad(){
		vars = new Variables();
		setPlayersArray(new ArrayList<Usuario>());
		p = new Principal(vars.getCuadros(),vars.getFilas(),vars.getColumnas());
		p.getTablero().cargarDatos(this);
		
		try{
			eventos = new Notepad();
		}catch(ErrorTxt error){
			popup("Error","Error al abrir el archivo");
		}
	}
	
	public void iniciarPartida(){
		playerActivo = getPlayersArray().get(0);
		p.getTablero().setLabelUsr1("Jugador 1: "+getPlayerActual().getNombre());
		p.getTablero().setLabelUsr2("Jugador 2: "+contrincante(getPlayerActual()).getNombre());
		p.getTablero().getLabelUsr1().setBackground(Color.ORANGE);
		p.getTablero().getLabelUsr2().setBackground(Color.lightGray);
		p.getTablero().cargaComboBox1(this,getPlayerActual());
		///
		p.getTablero().cargaComboBox2(this,contrincante(getPlayerActual()));
		p.getTablero().getCmbPersonajes1().setEnabled(true);
		p.getTablero().getCmbPersonajes2().setEnabled(false);
		Personaje principal = getPersonajePrincipal(getPlayerActual());
		p.getTablero().setPj1(principal);
		Personaje contrincante = getPersonajePrincipal(contrincante(getPlayerActual()));
		p.getTablero().setPj2(contrincante);
		moverPersonaje(principal);
		moverPersonaje(contrincante);
		///VER

		p.getTablero().setDatos(p.getTablero().getDatos1(),principal.getRaza().toString(),personajeTipo(principal),principal.getNombre(),principal.getFuerza(),principal.getInteligencia(),principal.getVida());
		p.getTablero().setDatos(p.getTablero().getDatos2(),contrincante.getRaza().toString(),personajeTipo(contrincante),contrincante.getNombre(),contrincante.getFuerza(),contrincante.getInteligencia(),contrincante.getVida());
		p.getTablero().inicializarCuadro();
		m = new Mapa();

		agregarHechizos();
		p.mostrar();
	}
	
	public void toLog(String texto){
		try{
			eventos.write(texto);
		}catch(ErrorWriteNotepad error){
			popup("Error","Error al grabar archivo");
		}
	}
	public void buscaHechizos(Mago personaje){
		Mapa mapa = getMapa();
		if(mapa.buscadorHechizos(personaje.getCuadros())>=0){
			String hechizo = personaje.getNombreHechizo();
			//System.out.println("Se encontr� un '"+hechizo+"' en el casillero "+personaje.getCasillero());
			toLog("Se encontr� un '"+hechizo+"' en el casillero "+personaje.getCuadros());
			if(personaje.getHechizos().size()<4){
				popup("Hechizo Encontrado", "Se encontr� un '"+hechizo+"' en el casillero "+personaje.getCuadros());
				personaje.aprenderHechizo(hechizo);
				mapa.borrarHechizo(mapa.buscadorHechizos(personaje.getCuadros()));
			}else{
				//System.out.println("No se pudo aprender el hechizo, este mago ya alcanz� el limite de hechizos");
				toLog("No se pudo aprender el hechizo, este mago ya alcanz� el limite de hechizos");
			}
		}else{
			//System.out.println("No se encontro ningun hechizo");
			toLog("No se encontro ningun hechizo");
		}
	}
	
	public int atacar(Mago personaje,Usuario contrincante){
		int ataque = 0;
		ArrayList<String> hechizos = personaje.getHechizos();
		if(hechizos.size()>0){
			for(Personaje per : contrincante.getPersonaje()){
				if(hechizos.size()>0){
					if(per.getCuadros()==personaje.getCuadros()){
						System.out.println("Se encontr� a "+per.getNombre()+" en el cuadro.");
						toLog("Se encontr� a "+per.getNombre()+" en el casillero.");
						if(per.getVida()>0){
							if(per.getVida()>10){
								per.setVida(per.getVida()-10);
								popup("Personaje Atacado", "El "+per.getRaza()+" "+per.getNombre()+" fu� atacado con el hechizo '"+hechizos.get(0)+"' (-10Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
								//System.out.println("El "+per.getRaza()+" "+per.getNombre()+" fu� atacado con el hechizo '"+hechizos.get(0)+"' (-10Vida) ["+per.getVida()+"/"+per.getVida_max()+"]");
								toLog("El "+per.getRaza()+" "+per.getNombre()+" fu� atacado con el hechizo '"+hechizos.get(0)+"' (-10Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
								hechizos.remove(0);
							}else{
								per.setVida(0);
								popup("Personaje Atacado","El "+per.getRaza()+" "+per.getNombre()+" fu� atacado y muri�");
								//System.out.println("El "+per.getRaza()+" "+per.getNombre()+" fu� atacado y muri�");
								toLog("El "+per.getRaza()+" "+per.getNombre()+" fu� atacado y muri�");
								ataque = 1;
							}
							actualizarVida(per.getVida());
						}else{
							//System.out.println("El "+per.getRaza()+" "+per.getNombre()+" est� muerto, no se puede atacar");
							toLog("El "+per.getRaza()+" "+per.getNombre()+" est� muerto, no se puede atacar");
						}
					}
				}
			}
		}else{
			//System.out.println("El mago no sabe trucos no verifico el casillero");
			toLog("El mago no sabe trucos no verifico el casillero");
		}
		return ataque;
	}
	
	public void curar(Clerigo personaje,Usuario contrincante){
		try{
			ArrayList<Personaje> personajes= new ArrayList<Personaje>();
			personajes.addAll(contrincante.getPersonaje());
			personajes.addAll(getPlayerActual().getPersonaje());

			for(Personaje per : personajes){
				//System.out.println("Personaje: "+per.getNombre());
				toLog("Personaje: "+per.getNombre());
				if(per.getCuadros()==personaje.getCuadros()){
					if(per.getVida()>0){ //si hay un personaje vivo
						if(per.getVida()!=per.getVidaMaxima()){ //si el personaje fue atacado antes
							if(per.getVida()+10<=per.getVidaMaxima()){ //si el personaje lleva su vida al maximo
								per.setVida(per.getVida()+10);
								popup("Personaje Curado", "El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+10Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
								//System.out.println("El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+10Vida) ["+per.getVida()+"/"+per.getVida_max()+"]");
								toLog("El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+10Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
							}else{
								int vida = per.getVidaMaxima()-per.getVida();
								per.setVida(per.getVidaMaxima());
								popup("Personaje Curado", "El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+"+vida+"Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
								//System.out.println("El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+"+vida+"Vida) ["+per.getVida()+"/"+per.getVida_max()+"]");
								toLog("El "+per.getRaza()+" "+per.getNombre()+" fu� curado (+"+vida+"Vida) ["+per.getVida()+"/"+per.getVidaMaxima()+"]");
							}
						}
					}
				}
			}
		}catch(Exception e){
			popup("Ocurri� un errror", "No fue posible curar al personaje\nDetalle: "+e.getMessage());
		}
	}
	
	public void agregarHechizos(){
			int i=0;
			while(i<vars.getHechizos()){
				int numero = random(0,vars.getCuadros());
				if(m.buscadorHechizos(numero)==-1){
					m.agregarHechizo(numero); 
					i++;	
				}
				
			}
	}
	
	public int getClerigo_fuerza_min(){
		return vars.getClerigo_fuerza_min();
	}
	
	public int getClerigo_inteligencia_min(){
		return vars.getClerigo_inteligencia_min();
	}
	
	public int getClerigo_inteligencia_max(){
		return vars.getClerigo_inteligencia_max();
	}
	
	public int getMago_inteligencia_min(){
		return vars.getMago_inteligencia_min();
	}
	
	public int getMago_fuerza_max(){
		return vars.getMago_fuerza_max();
	}
	
	public ArrayList<Personaje> obtenerPersonaje(Usuario player){
		return player.getPersonaje();
	}
	
	public void iniciarJuego(){
		inicializarJugadores();
		p.getTablero().reiniciarJuego(this);
	}
	
	public Mapa getMapa() {
		return m;
	}

	public void setMapa(Mapa m) {
		this.m = m;
	}
	
	public String personajeTipo(Personaje persona){
		switch(persona.getClass().getName()){ 
		case "Dominio.Mago":
			return "Mago";
		case "Dominio.Clerigo":
			return "Clerigo";
		case "Dominio.Caballero":
			return "Caballero";
		}
		return null;
	}
	
	public void inicializarJugadores(){
		getPlayersArray().clear();
	}
	
	
	public Usuario contrincante(Usuario player){
		for(Usuario pl : getPlayersArray()){
			if(pl!=player){
				return pl;
			}
		}
		return null;
	}
	
	
	public void moverPersonaje(Personaje personaje){
		System.out.println(personaje);
		p.getTablero().dibujarPj(personaje.getCuadros(),personajeTipo(personaje));
	}
	
	public void limpiarTablero(Personaje personaje){
		p.getTablero().desdibujarPj(personaje.getCuadros());
		for(Personaje per : contrincante(playerActivo).getPersonaje()){
			if((per.getCuadros()==personaje.getCuadros()&&per.getVida()>0)){
				p.getTablero().dibujarPj(per.getCuadros(),personajeTipo(per));
			}
		}
	}
	
	public int buscarPersonaje(String nombre){
		for(int i=0;i<playerActivo.getPersonaje().size();i++){
			if(playerActivo.getPersonaje().get(i).getNombre()==nombre){
				return i;
			}
		}
		return -1;
	}
	
	public int verificarVida(Usuario player){
		int vida = 0;
		for(Personaje per : player.getPersonaje()){
			vida += per.getVida();
		}
		return vida;
	}
	
	public Personaje getPersonaje(String personaje){
		if(buscarPersonaje(personaje)>=0){
			return playerActivo.getPersonaje().get(buscarPersonaje(personaje)); 
		}
		return null;
	}
	
	public boolean jugadorPrincipal(){
		//pregunto si el player actual es el primero
		if(getPlayersArray().get(0).getNombre().compareTo(playerActivo.getNombre())==0){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void actualizarVida(int vida){
		p.getTablero().actualizarVida(contrincante(playerActivo),vida);
	}
	
	
	public void actualizarPersonajes(Usuario player){
		p.getTablero().actualizarComboBox(this,player);
	}
	
	public void mostrarGanador(Usuario player){
		popup("Fin del juego","El ganador es "+player.getNombre()+" !");
		toLog("Fin del juego, El ganador es "+player.getNombre()+" !");
		p.getTablero().desactivarBotonDados();
		inicializarJugadores();
	}
	
	public void popup(String titulo, String texto){
		p.getTablero().infoVentana(titulo,texto);
	}
	
	public Usuario getPlayerActual(){
		return playerActivo;
	}
	
	public void agregarJugador(String nombre){
			Usuario unUsuario = new Usuario(nombre);
			playersArray.add(unUsuario);
			if(playerActivo==null){
				playerActivo = unUsuario;
			}
			//oculto el formulario de jugador y muestro del del personaje
			p.getTablero().getJugador().setVisible(false);
			p.getTablero().setPersonajes(this,unUsuario);
	}
	
	public void nuevoJugador(){
		p.getTablero().getVentana().setVisible(false);
		if(playersArray.size()==2){
			//System.out.println("Se alcanz� el maximo de jugadores.");
			toLog("Se alcanz� el maximo de jugadores.");
			iniciarPartida();
			p.getTablero().activarBotonDados();
		}else{
			p.getTablero().nuevoUsuario(this);
		}
	}
	
	public Personaje getPersonajePrincipal(Usuario player){
		return player.getPersonaje().get(0);
	}
	

	
	public void lanzar(String personaje){
		//System.out.println("personaje seleccionado: "+personaje);
		toLog("personaje seleccionado: "+personaje);
		int dado = lanzarDados();
		JOptionPane.showMessageDialog(null, "Dado: " + dado);
		int pers = buscarPersonaje(personaje);
		int nuevaPosicion = 0;
		if(pers>=0){
			Personaje personajeActual = playerActivo.getPersonaje().get(pers); 
			//pregunto si no me paso de los casilleros configurados previamente
			if(personajeActual.getCuadros() + dado < vars.getCuadros())
			{
				Object[] options = { "IZQUIERDA", "ABAJO","DERECHA", "ARRIBA"};
				int donde = JOptionPane.showOptionDialog(null, "A donde desea moverse?", "Warning",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				System.out.println("El personaje esta en: " + personajeActual.getCuadros());
				System.out.println("En el dado salio " + dado);
				
				switch (donde) {
				case 0: //IZquierda
					if(personajeActual.getCuadros()+dado*(-1)> -1)
					{
						nuevaPosicion = 0;
						nuevaPosicion = nuevaPosicion + personajeActual.getCuadros() - dado;
						System.out.println("Desde " + personajeActual.getCuadros() + "Va " + dado + "para la izquierda y termina en " + nuevaPosicion);
					}else
					{
						nuevaPosicion = 0;

					}
					break;

				case 2:
					if(personajeActual.getCuadros() + dado< vars.getCuadros())
					{
						nuevaPosicion = 0;
						nuevaPosicion = nuevaPosicion + personajeActual.getCuadros()+dado;
						System.out.println("Desde " + personajeActual.getCuadros() + "Va " + dado + "para la derecha y termina en " + nuevaPosicion);

					}else
					{
						nuevaPosicion = vars.getCuadros();
					}
					break;
					
				case 1: //Para abajo
					if(personajeActual.getCuadros()+dado*(+10)< 100)
					{
						nuevaPosicion = 0;
						nuevaPosicion = nuevaPosicion +  personajeActual.getCuadros()+ dado*10 ;
						System.out.println("Desde " + personajeActual.getCuadros() + " Va " + dado + "para la abajo y termina en " + nuevaPosicion);
						
					}else
					{
						nuevaPosicion = vars.getCuadros()-1;
					}
					break;
					
				case 3:
					if(personajeActual.getCuadros()+dado*(-10)> -1)
					{
						nuevaPosicion = 0;
						nuevaPosicion = nuevaPosicion + personajeActual.getCuadros()+dado*(-10);
						System.out.println("Desde " + personajeActual.getCuadros() + "Va " + dado + "para la arriba y termina en " + nuevaPosicion);
					}else
					{
						nuevaPosicion = 0;

					}
					break;
					
				default:
					break;
					
					
				}
				
				limpiarTablero(personajeActual);
				personajeActual.setCuadro(nuevaPosicion);
	
				p.getTablero().alternarFondo();
				moverPersonaje(personajeActual);
				
				//System.out.println("Turno de "+playerActual.getNombre());
				toLog("Turno de "+playerActivo.getNombre());
				//System.out.println("casillero actual: "+personajeActual.getCasillero());
				toLog("casillero actual: "+personajeActual.getCuadros());
				
				if(personajeActual.accion(contrincante(playerActivo),this)!=0){
					actualizarPersonajes(contrincante(playerActivo));
				}
				if(verificarVida(contrincante(playerActivo))==0){
					//System.out.println("El personaje "+personajeActual.getNombre()+" termin� la partida");
					toLog("El personaje "+personajeActual.getNombre()+" termin� la partida");
					limpiarTablero(personajeActual);
					mostrarGanador(playerActivo);
				}
			}else{
				System.out.println("El personaje "+playerActivo.getNombre()+" termin� la partida");
				toLog("El personaje "+personajeActual.getNombre()+" termin� la partida");
				limpiarTablero(personajeActual);
				mostrarGanador(playerActivo);
			}
			playerActivo = contrincante(playerActivo);
		}else{
			System.out.println("No se encontr� el personaje que desea usar.");
		}
	}
	
	public Raza obtenerRaza(String raza){
		Raza objeto;
		switch(raza){
			case "HUMANO":
				objeto = Raza.HUMANO;
			break;
			case "ELFO":
				objeto = Raza.ELFO;
			break;
			case "ENANO":
				objeto = Raza.ENANO;
			break;
			case "ORCO":
				objeto = Raza.ORCO;
			break;
			default:
				objeto = null;
		}
		
		return objeto;
	}
	
	public Principal getP() {
		return p;
	}

	public void setP(Principal p) {
		this.p = p;
	}

	public void agregaPersonajeAUsuario(Usuario player, Personaje unPersonaje){
		player.addPersonaje(unPersonaje);
	}
	
	
	public int lanzarDados(){
		return random(vars.getCant_dados(),vars.getCant_dados()*6);
	}
	
	private int random(int desde,int hasta){
		return desde + (int)(Math.random()*hasta);
	}

	public ArrayList<Usuario> getPlayersArray() {
		return playersArray;
	}

	public void setPlayersArray(ArrayList<Usuario> playersArray) {
		this.playersArray = playersArray;
	}	
	
	
	
}

