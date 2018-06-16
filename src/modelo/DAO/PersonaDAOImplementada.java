package modelo.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controlador.Conexion;
import modelo.DTO.Logs;
import modelo.DTO.PersonaDTO;
import vista.View;

public class PersonaDAOImplementada implements PersonaDAO {
	private Connection conexion = Conexion.getConexion();
	View vista;

	@Override
	public List<PersonaDTO> listarTodasLasPersonas() {
		List<PersonaDTO> listaPersonas = new ArrayList<>();
		// Crear objeto Statement
		String sql = "SELECT * FROM persona ORDER BY id;";
		try (Statement statement = conexion.createStatement();) {
			// Crear objeto ResultSet
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()) {
				PersonaDTO persona = new PersonaDTO(resulset.getInt(1), resulset.getString(2), resulset.getString(3),
						resulset.getString(4), resulset.getString(5));
				listaPersonas.add(persona);

			}
			Logs.crearLog("OPERACIÓN CRUD: 'Han sido listadas todas las personas' --------- FECHA: " + LocalDate.now());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPersonas;
	}

	@Override
	public boolean borrarPersona(int id) {
		int borrados = 0;

		String sql = "DELETE FROM persona WHERE id = ?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
			preparedStatement.setInt(1, id);

			borrados = preparedStatement.executeUpdate();
			Logs.crearLog("OPERACIÓN CRUD: 'Ha sido eliminado el campo con el id' " + id + "  --------- FECHA: "
					+ LocalDate.now());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrados != 0;

	}

	@Override
	public boolean actualizarEmailPersona(int id, String email) {
		int updates = 0;
		// UPDATE libro SET categoria='Seguridad' WHERE nombre = 'Santa Tecla';
		String sql = "UPDATE persona SET email = ? WHERE id = ?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, id);
			updates = preparedStatement.executeUpdate();
			Logs.crearLog("OPERACIÓN CRUD: 'Ha sido actualizado el registro con el id' " + id + " --------- FECHA: "
					+ LocalDate.now());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updates != 0;

	}

	@Override
	public boolean insertarPersona(PersonaDTO persona) {
		int resultado = 0;

		String sql = "INSERT INTO persona VALUES (?,?,?,?,?)";

		try (PreparedStatement psStatement = conexion.prepareStatement(sql);) {

			psStatement.setInt(1, persona.getId());
			psStatement.setString(2, persona.getNombre());
			psStatement.setString(3, persona.getApellidos());
			psStatement.setString(4, persona.getEmail());
			psStatement.setString(5, persona.getGenero());
			resultado = psStatement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(vista.getFrame(), "El id de inserción ya está en la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return resultado == 1;

	}

	public boolean insertarListaPersonas(List<PersonaDTO> listaPersonas) {

		for (PersonaDTO personaDTO : listaPersonas) {
			insertarPersona(personaDTO);
		}
		Logs.crearLog(
				"OPERACIÓN CRUD: 'Ha sido insertada una lista de personas' " + " --------- FECHA: " + LocalDate.now());
		return false;
	}

	public List<PersonaDTO> cargarCSV(String ruta) {
		File file = new File(ruta);
		List<PersonaDTO> listaPersonas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] datos = line.split(",");
				// Imprime datos.

				PersonaDTO persona = new PersonaDTO(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4]);
				listaPersonas.add(persona);

			}
			Logs.crearLog("OPERACIÓN CARGA: 'Ha sido cargado un fichero csv'  --------- FECHA: " + LocalDate.now());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaPersonas;

	}
}
