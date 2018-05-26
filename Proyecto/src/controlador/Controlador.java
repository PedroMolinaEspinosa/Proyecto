package controlador;

public class Controlador {

	public void cargarCSVEnBD() {

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