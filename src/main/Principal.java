package main;

import java.util.ArrayList;
import java.util.List;

import modelo.DAO.PersonaDAOImplementada;
import modelo.DTO.PersonaDTO;

public class Principal {

	public static void main(String[] args) {
		List<PersonaDTO> listaPersonas = new ArrayList<>();
		PersonaDAOImplementada manipulacion = new PersonaDAOImplementada();
		// La siguiente llamada sirve para cargar en base de datos los datos del
		// csv
		// manipulacion.insertarListaPersonas(manipulacion.cargarCSV());

	}

}
