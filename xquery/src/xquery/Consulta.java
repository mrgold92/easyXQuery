package xquery;

import javax.swing.JTextArea;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import com.saxonica.xqj.SaxonXQDataSource;

/**
 * @version 1.0.0
 * @author david s
 *
 */
public class Consulta {

	private String consulta;
	private JTextArea solucion;

	public Consulta(String consulta, JTextArea solucion) throws XQException {
		this.consulta = consulta;
		this.solucion = solucion;
		consultar();
	}

	public void consultar() {
		XQDataSource datasource = new SaxonXQDataSource();
		XQConnection conexion;
		try {
			conexion = datasource.getConnection();
			XQPreparedExpression expresion = conexion.prepareExpression(this.consulta);
			XQResultSequence resultado = expresion.executeQuery();

			String texto = "";
			while (resultado.next()) {
				texto += resultado.getItemAsString(null);

			}
			solucion.setText(texto);
		} catch (XQException e) {
			System.out.println(e.getMessage());
		} catch (StringIndexOutOfBoundsException ea) {
			System.err.println("error");
		}

	}
}
