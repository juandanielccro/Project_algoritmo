package cibertec;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoAcercaDeTienda extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSeparator sepLinea;
	private JButton btnCerrar;

	// Crea la caja de diálogo
	public DialogoAcercaDeTienda() {
		setTitle("Acerca de Tienda");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		crearEtiqueta("Tienda 1.0", Font.BOLD, 26, 10);
		crearEtiqueta("Venta de computadoras All-in-One - Ripley", Font.PLAIN, 12, 48);

		sepLinea = new JSeparator();
		sepLinea.setBounds(10, 75, 414, 2);
		contentPanel.add(sepLinea);

		crearEtiqueta("Autores", Font.BOLD, 18, 85);
		crearEtiqueta("Pedro Rhai Rivera León (Coordinador)", Font.BOLD, 13, 120);
		crearEtiqueta("Juan Daniel Ccorimanya Rodas", Font.BOLD, 13, 145);
		crearEtiqueta("Fernando José Alcántara Cóndor", Font.BOLD, 13, 170);
		crearEtiqueta("Christian Zeña", Font.BOLD, 13, 195);
		crearEtiqueta("Jorge Luis Naira Jibaja", Font.BOLD, 13, 220);
		crearEtiqueta("Grupo 10 - Introducción a la Algoritmia - Lima, 2026", Font.PLAIN, 11, 260);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(172, 300, 89, 23);
		contentPanel.add(btnCerrar);
	}

	// Crea una etiqueta centrada y la agrega a la caja de diálogo
	void crearEtiqueta(String texto, int estilo, int tamanoLetra, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", estilo, tamanoLetra));
		lbl.setBounds(10, y, 414, tamanoLetra + 12);
		contentPanel.add(lbl);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Cierra la caja de diálogo
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}
}
