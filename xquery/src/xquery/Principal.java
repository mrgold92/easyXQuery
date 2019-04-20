package xquery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.xml.xquery.XQException;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * @version 1.0.0
 * @author david s.
 *
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = -8351514609773159308L;
	private JPanel backgroundd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {

		// frame
		setTitle("Easy XQuery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(873, 669);
		setResizable(false);
		setLocationRelativeTo(null);
		backgroundd = new JPanel();
		backgroundd.setBackground(new Color(250, 240, 230));
		backgroundd.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backgroundd);
		backgroundd.setLayout(null);

		//setUndecorated(true);

		// lamina principal
		JPanel menu = new JPanel();
		menu.setBackground(UIManager.getColor("Button.foreground"));
		menu.setBounds(0, 0, 136, 669);
		backgroundd.add(menu);
		menu.setLayout(null);

		// Botón salir.
		JButton salir = new JButton("Salir");
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.this.dispose();
			}
		});
		salir.setForeground(Color.BLACK);
		salir.setFont(new Font("Fira Code Retina", Font.PLAIN, 12));
		salir.setFocusPainted(false);
		salir.setBorder(null);
		salir.setBackground(new Color(219, 172, 39));
		salir.setBounds(0, 597, 136, 37);
		menu.add(salir);

		// fondo título consulta
		JPanel titulo_consulta = new JPanel();
		titulo_consulta.setBackground(new Color(219, 172, 39));
		titulo_consulta.setBounds(135, 52, 738, 38);
		backgroundd.add(titulo_consulta);
		titulo_consulta.setLayout(null);

		// fondo consulta texto
		JLabel lblConsulta = new JLabel("Consulta...");
		lblConsulta.setFont(new Font("Fira Code Retina", Font.PLAIN, 12));
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setForeground(Color.BLACK);
		lblConsulta.setBounds(321, 11, 96, 15);
		titulo_consulta.add(lblConsulta);

		// fondo consulta
		JPanel background_consulta = new JPanel();
		background_consulta.setBackground(Color.WHITE);
		background_consulta.setBorder(null);
		background_consulta.setBounds(135, 91, 738, 179);
		backgroundd.add(background_consulta);
		background_consulta.setLayout(null);

		// fondo respuesta
		JTextPane txtpnIntroduceR = new JTextPane();
		txtpnIntroduceR.setForeground(SystemColor.desktop);
		txtpnIntroduceR.setFont(new Font("Dialog", Font.BOLD, 12));
		txtpnIntroduceR.setToolTipText("Introduce consulta...");
		txtpnIntroduceR.setBounds(7, 0, 724, 179);
		background_consulta.add(txtpnIntroduceR);

		// Scroll respuesta
		JScrollPane txtpnRe = new JScrollPane();
		txtpnRe.setBounds(135, 271, 738, 398);
		backgroundd.add(txtpnRe);

		// Textarea solucion
		JTextArea solucion = new JTextArea();
		solucion.setEditable(false);
		txtpnRe.setViewportView(solucion);

		// Botón cargar archivo
		JButton archivo = new JButton("Cargar archivo...");

		archivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirArchivo archivo = new AbrirArchivo();
				archivo.abrirArchivo(solucion);

			}
		});
		archivo.setFont(new Font("Fira Code Retina", Font.PLAIN, 12));
		archivo.setForeground(Color.BLACK);
		archivo.setBackground(new Color(219, 172, 39));
		archivo.setBounds(0, 52, 136, 37);
		archivo.setFocusPainted(false);
		archivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		archivo.setBorder(null);
		menu.add(archivo);

		// Botón ejecutar
		JButton execute = new JButton(new ImageIcon("/home/david/eclipse-workspace/xquery/img/play2.png"));

		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String consulta = txtpnIntroduceR.getText();

				if (consulta.equals("")) {
					solucion.setText("Debes introducir una consulta válida.");
				} else {
					int posicion = consulta.indexOf('"');

					int posiciondos = consulta.indexOf('"', posicion + 1);

					String original = consulta.substring(posicion + 1, posiciondos);

					String r = "resources/" + original;

					consulta = consulta.replace(original, r);

					try {
						new Consulta(consulta, solucion);
					} catch (XQException e) {
						System.err.println("error");
					}
				}

			}
		});

		execute.setForeground(Color.WHITE);

		execute.setFont(new Font("Fira Code Retina", Font.PLAIN, 12));
		execute.setFocusPainted(false);
		execute.setBorder(null);
		execute.setBackground(new Color(219, 172, 39));

		execute.setBounds(0, 123, 136, 37);
		execute.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menu.add(execute);

		// Copy
		JLabel lblDavidSalazarRodero = new JLabel("\u00a9 David Salazar Rodero - 2019");
		lblDavidSalazarRodero.setBounds(142, 12, 215, 15);
		backgroundd.add(lblDavidSalazarRodero);

	}

}
