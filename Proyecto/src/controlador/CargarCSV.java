package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.PersonaDTO;

public class CargarCSV {

	public static void main(String[] args) {
		cargarCSV();
	}

	public static List<PersonaDTO> cargarCSV() {
		File file = new File("BD/MOCK_DATA.csv");
		List<PersonaDTO> listaPersonas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] datos = line.split(",");
				// Imprime datos.
				PersonaDTO persona = new PersonaDTO(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4]);
				listaPersonas.add(persona);

			}
			System.out.println(listaPersonas);
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
/*
 * public void leerCSVComplejo(String path) throws IOException {
 * 
 * CSVReader lector = new CSVReader(new FileReader(path), separador, comillas);
 * String[] linea = null;
 * 
 * while ((linea = lector.readNext()) != null) {
 * System.out.println(Arrays.toString(linea)); }
 * 
 * if (linea != null) { lector.close(); } }
 * 
 * public boolean insertarLibro(PersonaDTO libro) { int resultado = 0; // INSERT
 * INTO libro (nombre, autor, editorial, categoria) VALUES // ('1','2','3','4');
 * String sql = "INSERT INTO libro (nombre, autor, editorial, categoria)" +
 * " VALUES (?,?,?,?);"; try (PreparedStatement psStatement =
 * conexion.prepareStatement(sql);) { psStatement.setString(1,
 * libro.getNombreLibro()); psStatement.setString(2, libro.getNombreAutor());
 * psStatement.setString(3, libro.getEditorial()); psStatement.setString(4,
 * libro.getNombreCategoria()); resultado = psStatement.executeUpdate();
 * 
 * } catch (SQLException e) { System.out.println("Error introduciendo datos"); }
 * System.out.println("Insertando datos: " + (resultado == 1)); return resultado
 * == 1; }
 * 
 * 
 * 
 * 
 * 
 */
