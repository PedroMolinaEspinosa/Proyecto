package main;

import java.awt.EventQueue;

import controlador.Controlador;
import vista.View;

public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					new Controlador(window);
					window.getFrmBaseDeDatos().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
