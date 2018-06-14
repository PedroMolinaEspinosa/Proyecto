package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import modelo.DAO.PersonaDAOImplementada;
import modelo.DTO.Logs;
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
	JDialog emergente;
	JLabel etiqueta;
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
		vista1.getBtnInsertar().addActionListener(escuchador);
		vista1.getBtnBorrar().addActionListener(escuchador);

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
					System.out.println(dao.listarTodasLasPersonas().size());
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
				cargarFormulario();
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void cargarSeleccionEnFormulario() {

	}

	public void cargarFormulario() {
		String id = data[0][0] + "";
		String nombre = (String) data[0][1];
		String apellidos = (String) data[0][2];
		String email = (String) data[0][3];
		String genero = (String) data[0][4];
		vista1.getTextFieldId().setText(id);
		vista1.getTextFieldNombre().setText(nombre);
		vista1.getTextFieldApellidos().setText(apellidos);
		vista1.getTextFieldEmail().setText(email);

		if (genero == "Male") {

			vista1.getRdbtnHombre().setSelected(true);
		} else
			vista1.getRdbtnMujer().setSelected(true);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("cargar CSV")) {
			lanzarEleccionFichero();

		}
		if (e.getActionCommand().equals("Insertar")) {
			insertarPersona();

		}
		if (e.getActionCommand().equals("Borrar")) {
			borrarPersona();

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

	private void borrarPersona() {
		int id = Integer.parseInt(vista1.getTextFieldId().getText());
		dao.borrarPersona(id);
		cargarModeloDePersonas(dao.listarTodasLasPersonas());
		setTabla();

	}

	private void insertarPersona() {

		String id = "";
		String nombre = "";
		String apellidos = "";
		String email = "";
		String genero = "";
		PersonaDTO persona = null;

		// Configurando inserción de Id
		if (vista1.getTextFieldId().getText().isEmpty() || !vista1.getTextFieldId().getText().matches("\\d{1,4}")) {
			JOptionPane.showMessageDialog(vista1.getFrame(), "El id debe ser un número de 1 a 4 dígitos", "Error",
					JOptionPane.ERROR_MESSAGE);
			/*
			 * emergente = new JDialog(vista1.getFrame(), "Fallo de Id");
			 * 
			 * etiqueta = new
			 * JLabel("Debes rellenar el Campo del Id con, de uno a cuatro dígitos numéricos"
			 * );
			 * 
			 * emergente.getContentPane().add(etiqueta);
			 * emergente.getContentPane().add(botonAceptar); emergente.pack();
			 * emergente.setLocation(400, 600); emergente.setVisible(true);
			 * botonAceptar.addActionListener(new ActionListener() { public void
			 * actionPerformed(ActionEvent e) { emergente.setVisible(false);
			 * emergente.dispose(); } });
			 */
		} else
			id = vista1.getTextFieldId().getText();

		// Configurando inserción de Nombre
		if (vista1.getTextFieldNombre().getText().isEmpty()
				|| !vista1.getTextFieldNombre().getText().matches("\\w{2,20}")) {
			JOptionPane.showMessageDialog(vista1.getFrame(), "El nombre debe ser tipo texto y de 2 a 20 caracteres",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else
			nombre = vista1.getTextFieldNombre().getText();

		// Configurando inserción de Apellidos
		if (vista1.getTextFieldApellidos().getText().isEmpty()
				|| !vista1.getTextFieldApellidos().getText().matches("\\w{2,30}")) {
			JOptionPane.showMessageDialog(vista1.getFrame(),
					"Los apellidos deben ser tipo texto y de 2 a 30 caracteres", "Error", JOptionPane.ERROR_MESSAGE);

		} else
			apellidos = vista1.getTextFieldApellidos().getText();

		// Configurando inserción de Email
		if (vista1.getTextFieldEmail().getText().isEmpty() || !vista1.getTextFieldEmail().getText().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			JOptionPane.showMessageDialog(vista1.getFrame(),
					"El email no está bien formado; Ejemplo: \n" + "info@iesvirgendelcarmen.com", "Error",
					JOptionPane.ERROR_MESSAGE);

		} else
			email = vista1.getTextFieldEmail().getText();

		// Configurando inserción de Genero
		if (!vista1.getRdbtnHombre().isEnabled() && !vista1.getRdbtnMujer().isEnabled()) {
			JOptionPane.showMessageDialog(vista1.getFrame(), "Debes marcar una de las dos opciones de sexo", "Error",
					JOptionPane.ERROR_MESSAGE);

		} else if (vista1.getRdbtnHombre().isEnabled()) {
			genero = "Male";
		} else
			genero = "Female";

		try {
			if (!(vista1.getTextFieldId().getText().isEmpty() || !vista1.getTextFieldId().getText().matches("\\d{1,4}")
					|| vista1.getTextFieldNombre().getText().isEmpty()
					|| !vista1.getTextFieldNombre().getText().matches("\\w{2,20}")
					|| vista1.getTextFieldApellidos().getText().isEmpty()
					|| !vista1.getTextFieldApellidos().getText().matches("\\w{2,30}")
					|| vista1.getTextFieldEmail().getText().isEmpty()
					|| !vista1.getTextFieldEmail().getText()
							.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
									+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
					|| !vista1.getRdbtnHombre().isEnabled() && !vista1.getRdbtnMujer().isEnabled())) {
				persona = new PersonaDTO(Integer.parseInt(id), nombre, apellidos, email, genero);
				System.out.println(persona);
			} else {
				System.out.println("No se ha podido insertar un carajo");
				System.out.println(persona);

			}
			dao.insertarPersona(persona);
			cargarModeloDePersonas(dao.listarTodasLasPersonas());
			setTabla();
			Logs.crearLog("OPERACIÓN CRUD: 'Ha sido insertado un registro con el id' " + persona.getId()
					+ " --------- FECHA: " + LocalDate.now());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista1.getFrame(), "No se ha añadido nada", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

	}
}
