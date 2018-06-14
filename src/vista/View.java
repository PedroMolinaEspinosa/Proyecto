package vista;

import java.awt.EventQueue;

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

import controlador.Controlador;

public class View {

	private JFrame frame;
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
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					new Controlador(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
		frame.setBounds(100, 100, 818, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Personitas");
		frame.setIconImage(new ImageIcon("Images/Diamond_Sword_25716.png").getImage());
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
		rdbtnHombre.setEnabled(false);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setEnabled(false);
		btGroup.add(rdbtnMujer);
		btGroup.add(rdbtnHombre);

		btnInsertar = new JButton("Insertar");
		btnInsertar.setEnabled(false);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblId)
								.addComponent(lblNewLabel).addComponent(lblApellidos).addComponent(lblEmail))
						.addGap(46)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(btnBorrar)
										.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 202,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldApellidos, Alignment.LEADING)
										.addComponent(textFieldNombre, Alignment.LEADING).addComponent(textFieldId,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(60)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnInsertar)
										.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnHombre)
												.addGap(35).addComponent(rdbtnMujer)))))
				.addContainerGap(55, Short.MAX_VALUE)));
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
						.addGap(83)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnInsertar)
								.addComponent(btnBorrar)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)))
				.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmCargarCsv = new JMenuItem("cargar CSV");
		mnFile.add(mntmCargarCsv);
	}

	public JMenuItem getMntmCargarCsv() {
		return mntmCargarCsv;
	}

	public void setMntmCargarCsv(JMenuItem mntmCargarCsv) {
		this.mntmCargarCsv = mntmCargarCsv;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
