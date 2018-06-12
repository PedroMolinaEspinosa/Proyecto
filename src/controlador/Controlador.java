package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;

import modelo.DAO.PersonaDAOImplementada;
import modelo.DTO.PersonaDTO;
import vista.View;

public class Controlador implements ActionListener {
	// private View vista;
	private View vista1;
	private PersonaDAOImplementada dao;
	private List<PersonaDTO> listaPersonas;
	private String path;
	private int contador = 0;
	static Object[][] data;
	static String[] titulos = new String[] { "Id", "Nombre", "Apellidos", "Email", "genero" };
	TMPersona tmpersona;
	JTable table;
	private Connection conexion = Conexion.getConexion();

	public Controlador(View view) {
		this.vista1 = view;
		dao = new PersonaDAOImplementada();
		actionListener(this);
		listaPersonas = dao.listarTodasLasPersonas();
		// registrarBotones();

		// mostrarPersonaje(0);
	}

	private void actionListener(ActionListener escuchador) {
		vista1.getMntmCargarCsv().addActionListener(escuchador);

		/*
		 * vista.getBtnAgregar().addActionListener(escuchador);
		 * vista.getBtnBorrar().addActionListener(escuchador);
		 * vista.getBtnPrimero().addActionListener(escuchador);
		 * vista.getBtnUltimo().addActionListener(escuchador);
		 * vista.getButton_anterior().addActionListener(escuchador);
		 * vista.getButton_posterior().addActionListener(escuchador);
		 * vista.getRdbtnHombre().addActionListener(escuchador);
		 * vista.getRdbtnMujer().addActionListener(escuchador);
		 * vista.getBtnActualizarEmail().addActionListener(escuchador);
		 */
	}

	private void lanzarEleccionFichero() {

		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista1.getFrame());
		if (resultado == jFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getPath();
			listaPersonas = dao.cargarCSV(path);

			String sql = "SELECT * FROM persona;";
			try (Statement st = conexion.createStatement();) {
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					System.out.println("Ya hay datos en la BD");
					cargarModeloDePersonas(dao.listarTodasLasPersonas());

				} else {
					dao.insertarListaPersonas(listaPersonas);
					System.out.println("La base de datos ha sido rellenada con los datos del csv");
					cargarModeloDePersonas(dao.listarTodasLasPersonas());

				}
				setTabla();
				vista1.getBtnInsertar().setEnabled(true);
				vista1.getRdbtnHombre().setEnabled(true);
				vista1.getRdbtnMujer().setEnabled(true);
				vista1.getTextFieldApellidos().setEnabled(true);
				vista1.getTextFieldId().setEnabled(true);
				vista1.getTextFieldNombre().setEnabled(true);
				vista1.getTextFieldEmail().setEnabled(true);
				vista1.getBtnBorrar().setEnabled(true);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// setTabla();

			// vista.setTmpersona(new TMPersona(dao.listarTodasLasPersonas()));

			// System.out.println(dao.listarTodasLasPersonas());
			// vista.getTable_1().setModel(vista.getTmpersona());
			// cargarTabla(dao.listarTodasLasPersonas());
			// tmpersona = new TMPersona(listaPersonas);
			// JTable table = new JTable(tmpersona);
			// table.getModel().addTableModelListener(table);
			// vista.getScrollPane().setViewportView(table);

			// dao.insertarListaPersonas(listaPersonas);

			// colocarFormularioPersona(contador);
		}

	}

	public static void cargarModeloDePersonas(List<PersonaDTO> listaDePersonas) {
		data = new Object[listaDePersonas.size()][5];
		int cont = 0;
		for (PersonaDTO personaDTO : listaDePersonas) {
			data[cont][0] = personaDTO.getId();
			data[cont][1] = personaDTO.getNombre();
			data[cont][2] = personaDTO.getApellidos();
			data[cont][3] = personaDTO.getEmail();
			data[cont][4] = personaDTO.getGenero();

			cont++;
		}
		// System.out.println(data.length);
	}

	public void setTabla() {
		tmpersona = new TMPersona(titulos, data);
		table = new JTable(tmpersona);
		vista1.getScrollPane().setViewportView(table);

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("cargar CSV")) {
			lanzarEleccionFichero();

		}

		if (e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton) e.getSource();
			String textoBoton = jButton.getText();
			switch (textoBoton) {
			case ">":
				System.out.println("pulsado " + textoBoton);
				contador++;
				// colocarFormularioPersona(contador);
				break;
			case "Ultimo":
				System.out.println("pulsado " + textoBoton);
				contador = listaPersonas.size() - 1;
				// colocarFormularioPersona(contador);
				break;
			case "<":
				System.out.println("pulsado " + textoBoton);
				contador--;
				// colocarFormularioPersona(contador);
				break;
			case "Primero":
				System.out.println("pulsado " + textoBoton);
				contador = 1;
				// colocarFormularioPersona(contador);
				break;
			default:
				break;
			}

		}
	}
}
