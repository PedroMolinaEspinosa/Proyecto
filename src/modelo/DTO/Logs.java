package modelo.DTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {

	public static void crearLog(String msj) {
		File file = new File("src/Logs/logs.txt");

		try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bf = new BufferedWriter(fw);) {
			bf.write(msj);
			bf.write("\n");
			bf.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
