package modelo;

import java.util.List;

public interface PersonaDAO {

	List<PersonaDTO> listarTodasLasPersonas();

	boolean borrarPersona(int id, String nombrePersona);

	boolean actualizarCategoriaPersona(PersonaDTO persona, String nombreCategoria);

	boolean insertarPersona(PersonaDTO persona);

	boolean insertarListaPersonas(List<PersonaDTO> listaPersonas);

}
