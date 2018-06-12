package controlador;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.DAO.PersonaDAOImplementada;
import modelo.DTO.PersonaDTO;

public class TMPersona extends AbstractTableModel {

	PersonaDAOImplementada dao = new PersonaDAOImplementada();
	List<PersonaDTO> listaPersonas;
	String[] titulos;
	Object[][] data;

	public TMPersona(String[] titulos, Object[][] data) {
		super();
		this.titulos = titulos;
		this.data = data;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return titulos[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titulos.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
}
