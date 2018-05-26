package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {

	public void crearLog(String msj) {
		File file = new File("BD/logs.txt");
		try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);) {
			fw.write(msj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
