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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	static Object[][] data1;
	static Object[][] data2;
	static Object[][] data3;
	static Object[][] data4;
	static Object[][] data5;
	static Object[][] data6;
	int paginacionTabla = 1;

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
		vista1.getBtnPrimero().addActionListener(escuchador);
		vista1.getButtonMenos().addActionListener(escuchador);
		vista1.getButtonMas().addActionListener(escuchador);
		vista1.getBtnUltimo().addActionListener(escuchador);
		vista1.getBtnActualizar().addActionListener(escuchador);
		vista1.getBtnPginaAnterior().addActionListener(escuchador);
		vista1.getBtnSiguientePg().addActionListener(escuchador);

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
			try {

				listaPersonas = dao.cargarCSV(path);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(vista1.getFrame(), "El archivo no es csv", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
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
				llenarDatasPaginacion();
				setTabla(data1);
				vista1.getBtnInsertar().setEnabled(true);

				vista1.getRdbtnMujer().setEnabled(true);
				vista1.getRdbtnHombre().setEnabled(true);
				vista1.getRdbtnHombre().setSelected(true);
				vista1.getBtnActualizar().setEnabled(true);
				vista1.getTextFieldApellidos().setEnabled(true);
				vista1.getTextFieldId().setEnabled(true);
				vista1.getTextFieldNombre().setEnabled(true);
				vista1.getTextFieldEmail().setEnabled(true);
				vista1.getBtnBorrar().setEnabled(true);
				vista1.getBtnPrimero().setEnabled(true);
				vista1.getButtonMenos().setEnabled(true);
				vista1.getButtonMas().setEnabled(true);
				vista1.getBtnUltimo().setEnabled(true);
				vista1.getBtnPginaAnterior().setEnabled(true);
				vista1.getBtnSiguientePg().setEnabled(true);

				vista1.getMntmCargarCsv().setEnabled(false);
				limpiarFormulario();
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

	public void setTabla(Object data) {
		tmpersona = new TMPersona(titulos, (Object[][]) data);
		table = new JTable(tmpersona);

		vista1.getScrollPane().setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void llenarDatasPaginacion() {
		data1 = new Object[200][5];
		data2 = new Object[200][5];
		data3 = new Object[200][5];
		data4 = new Object[200][5];
		data5 = new Object[200][5];
		data6 = new Object[data.length - 1000][5];
		int j = 0;

		for (int i = 0; i < 200; i++) {
			data1[i][0] = data[i][0];
			data1[i][1] = data[i][1];
			data1[i][2] = data[i][2];
			data1[i][3] = data[i][3];
			data1[i][4] = data[i][4];

		}

		for (int i = 200; i < 400; i++) {

			data2[j][0] = data[i][0];
			data2[j][1] = data[i][1];
			data2[j][2] = data[i][2];
			data2[j][3] = data[i][3];
			data2[j][4] = data[i][4];
			j++;

		}
		j = 0;
		for (int i = 400; i < 600; i++) {
			data3[j][0] = data[i][0];
			data3[j][1] = data[i][1];
			data3[j][2] = data[i][2];
			data3[j][3] = data[i][3];
			data3[j][4] = data[i][4];
			j++;

		}
		j = 0;

		for (int i = 600; i < 800; i++) {
			data4[j][0] = data[i][0];
			data4[j][1] = data[i][1];
			data4[j][2] = data[i][2];
			data4[j][3] = data[i][3];
			data4[j][4] = data[i][4];
			j++;
		}
		j = 0;

		for (int i = 800; i < 1000; i++) {
			data5[j][0] = data[i][0];
			data5[j][1] = data[i][1];
			data5[j][2] = data[i][2];
			data5[j][3] = data[i][3];
			data5[j][4] = data[i][4];
			j++;
		}
		j = 0;

		if (data.length > 1000)
			for (int i = 1000; i < data.length; i++) {
				data6[j][0] = data[i][0];
				data6[j][1] = data[i][1];
				data6[j][2] = data[i][2];
				data6[j][3] = data[i][3];
				data6[j][4] = data[i][4];
				j++;
			}
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

		if (genero.equals("Male")) {

			vista1.getRdbtnHombre().setSelected(true);
		} else {

			vista1.getRdbtnMujer().setSelected(true);

		}
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

		if (e.getActionCommand().equals("cargar CSV")) {
			lanzarEleccionFichero();

		}
		if (e.getActionCommand().equals("Insertar")) {
			insertarPersona();

		}
		if (e.getActionCommand().equals("Borrar")) {
			borrarPersona();

		}
		if (e.getActionCommand().equals("Actualizar")) {
			actualizarPersona();
		}

		if (e.getActionCommand().equals("Siguiente página")) {
			paginacionTabla++;

			if (paginacionTabla == 2)
				setTabla(data2);
			if (paginacionTabla == 3)
				setTabla(data3);
			if (paginacionTabla == 4)
				setTabla(data4);
			if (paginacionTabla == 5)
				setTabla(data5);
			if (paginacionTabla == 6)
				setTabla(data6);
			if (paginacionTabla == 7)
				paginacionTabla = 6;
		}

		if (e.getActionCommand().equals("Página anterior")) {

			paginacionTabla--;

			if (paginacionTabla == 1)
				setTabla(data1);
			if (paginacionTabla == 2)
				setTabla(data2);
			if (paginacionTabla == 3)
				setTabla(data3);
			if (paginacionTabla == 4)
				setTabla(data4);
			if (paginacionTabla == 5)
				setTabla(data5);
			if (paginacionTabla == 0)
				paginacionTabla = 1;
		}

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// do some actions here, for example

				// print first column value from selected row
				vista1.getTextFieldId().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				vista1.getTextFieldNombre().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				vista1.getTextFieldApellidos().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				vista1.getTextFieldEmail().setText(table.getValueAt(table.getSelectedRow(), 3).toString());

				if (table.getValueAt(table.getSelectedRow(), 4).toString().equals("Male")) {
					vista1.getRdbtnHombre().setSelected(true);
				} else
					vista1.getRdbtnMujer().setSelected(true);
			}

		});
		if (e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton) e.getSource();
			String textoBoton = jButton.getText();

			if (vista1.getTextFieldId().getText().matches("\\d{1,4}")) {
				contador = buscarPosicionEnArray();
				// System.out.println(contador);
				// System.out.println("Ha cogido el contador del formulario " +
				// contador);
			} else
				contador = 0;

			switch (textoBoton) {
			case ">":

				if (contador < data.length - 1)
					contador++;
				// System.out.println(contador);
				// System.out.println(data.length);
				funcionalidadBotones(contador);

				// colocarFormularioPersona(contador);
				break;
			case "Ultimo":

				contador = data.length - 1;
				funcionalidadBotones(contador);
				break;
			case "<":

				if (contador > 0)
					contador--;
				System.out.println(contador);
				if (contador >= 0)
					funcionalidadBotones(contador);
				else
					JOptionPane.showMessageDialog(vista1.getFrame(),
							"No hay valores por debajo o por encima de ese campo", "Error", JOptionPane.ERROR_MESSAGE);

				break;
			case "Primero":

				contador = 0;
				funcionalidadBotones(contador);
				break;
			default:
				break;
			}

		}
	}

	private int buscarPosicionEnArray() {
		int i = 0;
		int j = 0;
		while (j < data.length) {
			if ((int) data[j][0] == Integer.parseInt(vista1.getTextFieldId().getText())) {
				i = j;

				break;
			}
			// System.out.println(data[j][0]);
			// System.out.println(vista1.getTextFieldId().getText() + "");
			j++;

		}

		/*
		 * int j = 0; for (int i = 0; i < data.length; i++) { if (data[i][0] ==
		 * vista1.getTextFieldId().getText() + "") { System.out.println(i); j =
		 * i; return j; break; } else {
		 * 
		 * // System.out.println(data[i][0]); //
		 * System.out.println(vista1.getTextFieldId().getText());
		 * 
		 * }
		 * 
		 * } return j;
		 */

		return i;
	}

	private void limpiarFormulario() {
		vista1.getTextFieldId().setText("");
		vista1.getTextFieldNombre().setText("");
		vista1.getTextFieldApellidos().setText("");
		vista1.getTextFieldEmail().setText("");
		vista1.getRdbtnHombre().setSelected(false);
		vista1.getRdbtnMujer().setSelected(false);
		;
	}

	private void funcionalidadBotones(int contador) {

		vista1.getTextFieldId().setText(data[contador][0] + "");
		vista1.getTextFieldNombre().setText((String) data[contador][1]);
		vista1.getTextFieldApellidos().setText((String) data[contador][2]);
		vista1.getTextFieldEmail().setText((String) data[contador][3]);
		String genero = (String) data[contador][4];
		if (genero.equals("Male")) {

			vista1.getRdbtnHombre().setSelected(true);
			;
			;
		} else {

			vista1.getRdbtnMujer().setSelected(true);
		}
	}

	private void actualizarPersona() {

		if (!(vista1.getTextFieldId().getText().isEmpty() || !vista1.getTextFieldId().getText().matches("\\d{1,4}")
				|| vista1.getTextFieldNombre().getText().isEmpty()
				|| !vista1.getTextFieldNombre().getText().matches("\\w{2,20}")
				|| vista1.getTextFieldApellidos().getText().isEmpty()
				|| !vista1.getTextFieldApellidos().getText().matches("\\w{2,15} \\w{2,15}")
				|| vista1.getTextFieldEmail().getText().isEmpty()
				|| !vista1.getTextFieldEmail().getText().matches(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
				|| !vista1.getRdbtnHombre().isEnabled() && !vista1.getRdbtnMujer().isEnabled())) {
			String genero = null;
			if (vista1.getRdbtnHombre().isSelected())
				genero = "Male";
			else
				genero = "Female";

			dao.actualizarPersona(Integer.parseInt(vista1.getTextFieldId().getText()),
					vista1.getTextFieldNombre().getText(), vista1.getTextFieldApellidos().getText(),
					vista1.getTextFieldEmail().getText(), genero);

			cargarModeloDePersonas(dao.listarTodasLasPersonas());
			setTabla(data1);
		} else
			JOptionPane.showMessageDialog(vista1.getFrame(),
					"Uno de los campos no está bien formado: \n"
							+ "		> El id debe ser un número de 1 a 4 dígitos\n"
							+ "		> El nombre debe ser tipo texto y de 2 a 20 caracteres, sin espacios, no se admiten nombres compuestos\n"
							+ "		> Los apellidos deben ser tipo texto y de 2 a 30 caracteres separados por un espacio\n"
							+ "		> El email no está bien formado; Ejemplo: \n" + "info@iesvirgendelcarmen.com\n"
							+ "		> Debes marcar una de las dos opciones de sexo\n",
					"Error", JOptionPane.ERROR_MESSAGE);
	}

	private void borrarPersona() {
		int id = Integer.parseInt(vista1.getTextFieldId().getText());
		dao.borrarPersona(id);
		cargarModeloDePersonas(dao.listarTodasLasPersonas());
		setTabla(data1);
		limpiarFormulario();

	}

	private void insertarPersona() {

		String id = "";
		String nombre = "";
		String apellidos = "";
		String email = "";
		String genero = "";
		PersonaDTO persona = null;

		// Configurando inserción de Id

		// tratando error de insercción con el mismo id
		try {

			if (vista1.getTextFieldId().getText().isEmpty() || !vista1.getTextFieldId().getText().matches("\\d{1,4}")
					|| comprobarSiExiste()) {
				JOptionPane.showMessageDialog(vista1.getFrame(),
						"El id debe ser un número de 1 a 4 dígitos y no existir en la base de datos", "Error",
						JOptionPane.ERROR_MESSAGE);
				// System.out.println(comprobarSiExiste());
				// System.out.println(vista1.getTextFieldId().getText());
				/*
				 * emergente = new JDialog(vista1.getFrame(), "Fallo de Id");
				 * 
				 * etiqueta = new
				 * JLabel("Debes rellenar el Campo del Id con, de uno a cuatro dígitos numéricos"
				 * );
				 * 
				 * emergente.getContentPane().add(etiqueta);
				 * emergente.getContentPane().add(botonAceptar);
				 * emergente.pack(); emergente.setLocation(400, 600);
				 * emergente.setVisible(true);
				 * botonAceptar.addActionListener(new ActionListener() { public
				 * void actionPerformed(ActionEvent e) {
				 * emergente.setVisible(false); emergente.dispose(); } });
				 */
			} else
				id = vista1.getTextFieldId().getText();
		} catch (Exception e) {
			// System.out.println(comprobarSiExiste());
			// System.out.println(vista1.getTextFieldId().getText());
			JOptionPane.showMessageDialog(vista1.getFrame(), "La insercion del id ha fallado", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		// Configurando inserción de Nombre
		if (vista1.getTextFieldNombre().getText().isEmpty()
				|| !vista1.getTextFieldNombre().getText().matches("\\w{2,20}")) {
			JOptionPane.showMessageDialog(vista1.getFrame(),
					"El nombre debe ser tipo texto y de 2 a 20 caracteres, sin espacios, no se admiten nombres compuestos",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else
			nombre = vista1.getTextFieldNombre().getText();

		// Configurando inserción de Apellidos
		if (vista1.getTextFieldApellidos().getText().isEmpty()
				|| !vista1.getTextFieldApellidos().getText().matches("\\w{2,15} \\w{2,15}")) {
			JOptionPane.showMessageDialog(vista1.getFrame(),
					"Los apellidos deben ser tipo texto y de 2 a 30 caracteres separados por un espacio", "Error",
					JOptionPane.ERROR_MESSAGE);

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
					|| comprobarSiExiste() || vista1.getTextFieldNombre().getText().isEmpty()
					|| !vista1.getTextFieldNombre().getText().matches("\\w{2,20}")
					|| vista1.getTextFieldApellidos().getText().isEmpty()
					|| !vista1.getTextFieldApellidos().getText().matches("\\w{2,15} \\w{2,15}")
					|| vista1.getTextFieldEmail().getText().isEmpty()
					|| !vista1.getTextFieldEmail().getText()
							.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
									+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
					|| !vista1.getRdbtnHombre().isEnabled() && !vista1.getRdbtnMujer().isEnabled())) {
				contador = 0;

				persona = new PersonaDTO(Integer.parseInt(id), nombre, apellidos, email, genero);
				System.out.println(persona);

			} else {
				System.out.println("No se ha podido insertar un carajo");
				System.out.println(persona);

			}
			try {

				dao.insertarPersona(persona);

				JOptionPane.showMessageDialog(vista1.getFrame(),
						"Persona con id: " + id + " ,se ha insertado correctamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
				cargarModeloDePersonas(dao.listarTodasLasPersonas());
				setTabla(data1);
				Logs.crearLog("OPERACIÓN CRUD: 'Ha sido insertado un registro con el id' " + persona.getId()
						+ " --------- FECHA: " + LocalDate.now());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(vista1.getFrame(),
						"Ya existe un campo con ese id o el id no está bien formado: un numero de 1 a 4 cifras",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista1.getFrame(), "No se ha añadido nada", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public boolean comprobarSiExiste() {
		boolean respuesta = false;
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] == vista1.getTextFieldId().getText() + "") {
				respuesta = true;
				System.out.println("SI EXISTE EN BD");
				continue;

			} else {

				// System.out.println(data[i][0]);
				// System.out.println(vista1.getTextFieldId().getText());

			}
		}

		return respuesta;

	}
}
