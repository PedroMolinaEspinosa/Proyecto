package vista;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class View {

	private JFrame frmBaseDeDatos;

	public JFrame getFrmBaseDeDatos() {
		return frmBaseDeDatos;
	}

	private JScrollPane scrollPane;
	private JMenuItem mntmCargarCsv;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldEmail;
	private JButton btnBorrar;
	private JButton btnInsertar;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JButton btnPrimero;
	private JButton buttonMenos;
	private JButton buttonMas;
	private JButton btnUltimo;
	private JButton btnActualizar;
	private JButton btnSiguientePg;
	private JButton btnPginaAnterior;

	// --------------------------------------GETTERS Y
	// SETTERS--------------------------------------------------------------

	public JTextField getTextFieldId() {
		return textFieldId;
	}

	public void setTextFieldId(JTextField textFieldId) {
		this.textFieldId = textFieldId;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}

	public void setTextFieldApellidos(JTextField textFieldApellidos) {
		this.textFieldApellidos = textFieldApellidos;
	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public void setBtnInsertar(JButton btnInsertar) {
		this.btnInsertar = btnInsertar;
	}

	public JRadioButton getRdbtnHombre() {
		return rdbtnHombre;
	}

	public void setRdbtnHombre(JRadioButton rdbtnHombre) {
		this.rdbtnHombre = rdbtnHombre;
	}

	public JRadioButton getRdbtnMujer() {
		return rdbtnMujer;
	}

	public void setRdbtnMujer(JRadioButton rdbtnMujer) {
		this.rdbtnMujer = rdbtnMujer;
	}

	public void setFrame(JFrame frame) {
		this.frmBaseDeDatos = frame;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBaseDeDatos = new JFrame();
		frmBaseDeDatos.setResizable(false);
		frmBaseDeDatos.setBounds(100, 100, 818, 656);
		frmBaseDeDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBaseDeDatos.setTitle("Base de datos de Personas");
		frmBaseDeDatos.setIconImage(new ImageIcon("Images/dino.png").getImage());
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 400, 400);

		JLabel lblId = new JLabel("Id");

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre");

		textFieldNombre = new JTextField();
		textFieldNombre.setEnabled(false);
		textFieldNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEnabled(false);
		textFieldApellidos.setColumns(10);

		JLabel lblEmail = new JLabel("Email");

		textFieldEmail = new JTextField();
		textFieldEmail.setEnabled(false);
		textFieldEmail.setColumns(10);
		ButtonGroup btGroup = new ButtonGroup();
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setSelected(true);
		rdbtnHombre.setEnabled(false);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setEnabled(false);
		btGroup.add(rdbtnMujer);
		btGroup.add(rdbtnHombre);

		btnInsertar = new JButton("Insertar");
		btnInsertar.setEnabled(false);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);

		btnPrimero = new JButton("Primero");
		btnPrimero.setEnabled(false);

		buttonMenos = new JButton("<");
		buttonMenos.setEnabled(false);

		buttonMas = new JButton(">");
		buttonMas.setEnabled(false);

		btnUltimo = new JButton("Ultimo");
		btnUltimo.setEnabled(false);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);

		btnSiguientePg = new JButton("Siguiente p\u00E1gina");
		btnSiguientePg.setEnabled(false);

		btnPginaAnterior = new JButton("P\u00E1gina anterior");
		btnPginaAnterior.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(frmBaseDeDatos.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
										.addContainerGap().addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 429,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout
														.createSequentialGroup().addGap(60).addComponent(
																rdbtnHombre)
														.addGap(35).addComponent(rdbtnMujer))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(34).addGroup(groupLayout
																.createParallelGroup(Alignment.LEADING, false)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblId)
																				.addComponent(lblNewLabel)
																				.addComponent(lblApellidos)
																				.addComponent(lblEmail)
																				.addComponent(btnInsertar))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addGroup(groupLayout
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(
																										textFieldEmail,
																										GroupLayout.PREFERRED_SIZE,
																										202,
																										GroupLayout.PREFERRED_SIZE)
																								.addGroup(groupLayout
																										.createSequentialGroup()
																										.addGap(27)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				buttonMenos)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				buttonMas))
																														.addComponent(
																																btnActualizar,
																																GroupLayout.DEFAULT_SIZE,
																																88,
																																Short.MAX_VALUE))
																										.addGap(33)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																btnUltimo)
																														.addComponent(
																																btnBorrar))))
																						.addGap(63))
																				.addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								textFieldApellidos,
																								Alignment.LEADING)
																						.addComponent(textFieldNombre,
																								Alignment.LEADING)
																						.addComponent(textFieldId,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								114, Short.MAX_VALUE))))
																.addComponent(btnPrimero)))))
								.addGroup(groupLayout.createSequentialGroup().addGap(62).addComponent(btnPginaAnterior)
										.addGap(110).addComponent(btnSiguientePg)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(51)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(
								textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(31)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblApellidos)
								.addComponent(textFieldApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnHombre)
								.addComponent(rdbtnMujer))
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnPrimero)
								.addComponent(buttonMenos).addComponent(buttonMas).addComponent(btnUltimo))
						.addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnInsertar)
								.addComponent(btnBorrar).addComponent(btnActualizar)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnPginaAnterior)
						.addComponent(btnSiguientePg))
				.addContainerGap(95, Short.MAX_VALUE)));
		frmBaseDeDatos.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmBaseDeDatos.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmCargarCsv = new JMenuItem("cargar CSV");
		mnFile.add(mntmCargarCsv);
	}

	public JButton getBtnSiguientePg() {
		return btnSiguientePg;
	}

	public JButton getBtnPginaAnterior() {
		return btnPginaAnterior;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public JButton getBtnPrimero() {
		return btnPrimero;
	}

	public void setBtnPrimero(JButton btnPrimero) {
		this.btnPrimero = btnPrimero;
	}

	public JButton getButtonMenos() {
		return buttonMenos;
	}

	public void setButtonMenos(JButton buttonMenos) {
		this.buttonMenos = buttonMenos;
	}

	public JButton getButtonMas() {
		return buttonMas;
	}

	public void setButtonMas(JButton buttonMas) {
		this.buttonMas = buttonMas;
	}

	public JButton getBtnUltimo() {
		return btnUltimo;
	}

	public void setBtnUltimo(JButton btnUltimo) {
		this.btnUltimo = btnUltimo;
	}

	public JMenuItem getMntmCargarCsv() {
		return mntmCargarCsv;
	}

	public void setMntmCargarCsv(JMenuItem mntmCargarCsv) {
		this.mntmCargarCsv = mntmCargarCsv;
	}

	public JFrame getFrame() {
		return frmBaseDeDatos;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
