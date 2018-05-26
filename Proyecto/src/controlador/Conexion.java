package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class Conexion {
	private static Connection conexion;

	private Conexion() {

		try {
			String url = "jdbc:sqlite:personas.bd";

			conexion = DriverManager.getConnection(url);
			// Configuramos el objeto Config para permitir foreign keys
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			sqLiteConfig.enforceForeignKeys(true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConexion() {

		if (conexion == null) {
			new Conexion();
			Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		}

		return conexion;

	}

	static class ShutdownHook extends Thread {
		@Override
		public void run() {
			if (conexion != null)
				try {
					System.out.println("Cerrando conexion");
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
