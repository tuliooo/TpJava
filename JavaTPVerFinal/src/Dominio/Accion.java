package Dominio;

import Conector.Jugabilidad;

public interface Accion {

	int accion(Usuario adversario, Jugabilidad juego);
}
