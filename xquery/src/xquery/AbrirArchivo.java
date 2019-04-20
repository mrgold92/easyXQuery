package xquery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 * @version 1.0.0
 * @author david S
 *
 */

public class AbrirArchivo extends JFileChooser {

	private static final long serialVersionUID = -7128771255492211066L;

	/**
	 * Método que permite seleccionar un archivo.xml guardarlo para poder hacer
	 * consultas sobre él.
	 * 
	 * @param panel JTextArea
	 */
	public void abrirArchivo(JTextArea panel) {

		File directorio = new File("resources");

		if (!directorio.exists()) {

			directorio.mkdir();

		}

		JFileChooser fc = new JFileChooser();
		BufferedReader frBuffer = null;
		FileWriter fw = null;

		int seleccion = fc.showOpenDialog(this);
		File fichero = fc.getSelectedFile();

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {

				frBuffer = new BufferedReader(new FileReader(fichero));
				System.out.println(fichero.getName());
				fw = new FileWriter("resources/" + fichero.getName());

				String linea = frBuffer.readLine();

				while (linea != null) {

					fw.write(linea + "\r\n");
					linea = frBuffer.readLine();
				}

				fw.flush();
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				try {
					fw.close();
					frBuffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		try {
			mostrar(panel, fichero);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void mostrar(JTextArea panel, File fichero) throws IOException {
		BufferedReader frBuffer = new BufferedReader(new FileReader("resources/" + fichero.getName()));

		String linea;
		String texto = "";
		while ((linea = frBuffer.readLine()) != null) {

			texto += linea + "\n";
		}
		frBuffer.close();

		panel.setText(texto);
	}

}
