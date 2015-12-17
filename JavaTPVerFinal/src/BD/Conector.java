package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dominio.Caballero;
import Dominio.Clerigo;
import Dominio.Mago;
import Dominio.Personaje;
import Dominio.Raza;
import Dominio.Usuario;

public class Conector {

String url = "Medieval.db3";
Connection connect;

public void connect(){
	 try {
	     connect = DriverManager.getConnection("jdbc:sqlite:"+url);
	     if (connect!=null) {
	         System.out.println("Conectado");
	     }
	 }catch (SQLException ex) {
	     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
	 }
	}
	 public void close(){
	        try {
	            connect.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 }
	 
	 public void saveCaballero(Caballero unCaballero){
	        try {
	            PreparedStatement st = connect.prepareStatement("insert into personajes (nombre, raza, inteligencia, fuerza, tipo) values (?, ?, ?, ?, ?, ?, ?)");
	            st.setString(1, unCaballero.getNombre());
	            st.setString(2, unCaballero.getRaza().toString());
	            st.setInt(3, unCaballero.getInteligencia());
	            st.setInt(4, unCaballero.getFuerza());
	            st.execute();
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }

	    }
	 
	 public void savePersonaje(Personaje unPersonaje, String tipo)
	 {
		 try {
	            PreparedStatement st = connect.prepareStatement("insert into personajes(nombre, raza, inteligencia, fuerza, tipo) values (?, ?, ?, ?, ?, ?, ?)");
	            st.setString(1, unPersonaje.getNombre());
	            st.setString(2, unPersonaje.getRaza().toString());
	            st.setInt(3, unPersonaje.getInteligencia());
	            st.setInt(4, unPersonaje.getFuerza());
	            st.setString(5, tipo);
	            st.execute();
	        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
	        }
	 }
	 

	 
	 public void mostrarPersonajes(){
	        ResultSet result = null;
	        try {
	            PreparedStatement st = connect.prepareStatement("select * from personajes");
	            result = st.executeQuery();
	            while (result.next()) {
	                System.out.print("ID: ");
	                System.out.println(result.getInt("id"));
	 
	                System.out.print("Nombre: ");
	                System.out.println(result.getString("nombre"));
	 
	                System.out.print("Tipo: ");
	                System.out.println(result.getString("tipo"));

	                System.out.print("Raza: ");
	                System.out.println(result.getString("raza"));
	                
	                System.out.print("Inteligencia: ");
	                System.out.println(result.getInt("inteligencia"));

	                System.out.print("Fuerza: ");
	                System.out.println(result.getInt("fuerza"));

	                System.out.println("=======================");
	            }
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
	 
	 public boolean saveUsuario(Usuario unUsuario)
	 {
		 try {
	            PreparedStatement st = connect.prepareStatement("insert into usuarios(nombre, password) values (?, ?)");
	            st.setString(1, unUsuario.getNombre());
	            st.setString(2, unUsuario.getPassword());
	            st.execute();
	            return true;
	        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
	        }
	 }
	 
	 public ArrayList<Usuario> traeUsuarios(){
	        ResultSet result = null;
	        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	        try {
	        	PreparedStatement st = connect.prepareStatement("select * from usuarios");
	        	result = st.executeQuery();
	        	while (result.next()) 
	        	{
	        		Usuario unUsuario = new Usuario(result.getString("nombre"), result.getString("password"));
	                listaUsuarios.add(unUsuario);
	        	}
	            
	            
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
			return listaUsuarios;
	    }
	 
	 public ArrayList<Personaje> traePersonajes(){
	        ResultSet result = null;
	        Personaje unPersonaje = null;
	        ArrayList<Personaje> listaPersonajes = new ArrayList<Personaje>();
	        try {
	        	PreparedStatement st = connect.prepareStatement("select * from personajes");
	        	result = st.executeQuery();
	        	while (result.next()) 
	        	{
	        		
	    			switch (result.getString(("tipo"))) {
	    			case "Caballero":
	    				unPersonaje = new Caballero(result.getString("nombre"),Raza.valueOf(result.getString("raza")) , result.getInt("fuerza"), result.getInt("inteligencia"), 100);
	    				break;

	    			case "Mago":
	    				unPersonaje = new Mago(result.getString("nombre"),Raza.valueOf(result.getString("raza")) , result.getInt("fuerza"), result.getInt("inteligencia"), 100);
	    				break;

	    			case "Clerigo":
	    				unPersonaje = new Clerigo(result.getString("nombre"),Raza.valueOf(result.getString("raza")) , result.getInt("fuerza"), result.getInt("inteligencia"), 100);	
	    				break;
	    			default:
	    				break;
	    			}

	                
	        		listaPersonajes.add(unPersonaje);
	        	}
	            
	            
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
			return listaPersonajes;
	    }
	 
	 public void mostrarUsuarios(){
	        ResultSet result = null;
	        try {
	            PreparedStatement st = connect.prepareStatement("select * from usuarios");
	            result = st.executeQuery();
	            while (result.next()) {
	                System.out.print("ID: ");
	                System.out.println(result.getInt("id"));
	 
	                System.out.print("Nombre: ");
	                System.out.println(result.getString("nombre"));
	 
	                
	                System.out.println("=======================");
	            }
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
}
