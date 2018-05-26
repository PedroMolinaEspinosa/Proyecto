package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImplementada implements PersonaDAO {
	private Connection conexion;

	@Override
	public List<PersonaDTO> listarTodasLasPersonas() {
		List<PersonaDTO> listaPersonas = new ArrayList<>();
		// Crear objeto Statement
		String sql = "SELECT * FROM persona;";
		try (Statement statement = conexion.createStatement();) {
			// Crear objeto ResultSet
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()) {
				PersonaDTO persona = new PersonaDTO(resulset.getInt(1), resulset.getString(2), resulset.getString(3),
						resulset.getString(4), resulset.getString(5));
				listaPersonas.add(persona);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPersonas;
	}

	@Override
	public boolean borrarPersona(int id, String nombrePersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarCategoriaPersona(PersonaDTO persona, String nombreCategoria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarPersona(PersonaDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarListaPersonas(List<PersonaDTO> listaPersonas) {
		// TODO Auto-generated method stub
		return false;
	}

}
