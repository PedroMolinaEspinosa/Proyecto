package modelo.DAO;

import java.util.List;

import modelo.DTO.PersonaDTO;

public interface PersonaDAO {

	// Lectura CSV
	List<PersonaDTO> cargarCSV(String path);

	// Lectura base de datos
	List<PersonaDTO> listarTodasLasPersonas();

	boolean borrarPersona(int id);

	boolean actualizarPersona(int id, String nombre, String apellidos, String email, String genero);

	boolean insertarPersona(PersonaDTO persona);

	boolean insertarListaPersonas(List<PersonaDTO> listaPersonas);

}
