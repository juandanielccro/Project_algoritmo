package cibertec;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoListarComputadoras extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scpScroll;
	private JTextArea txtS;
	private JPanel pnlBotones;
	private JButton btnCerrar;
	private JButton btnListar;

	// Crea la caja de diálogo
	public DialogoListarComputadoras() {
		setTitle("Listado de computadoras");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 560, 430);
		getContentPane().setLayout(null);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 11, 524, 330);
		getContentPane().add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);

		pnlBotones = new JPanel();
		pnlBotones.setBounds(178, 352, 188, 23);
		getContentPane().add(pnlBotones);
		pnlBotones.setLayout(null);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(0, 0, 89, 23);
		pnlBotones.add(btnCerrar);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(99, 0, 89, 23);
		pnlBotones.add(btnListar);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnListar) {
			actionPerformedBtnListar(e);
		}
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Cierra la caja de diálogo
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}

	// Muestra el listado de las cinco computadoras
	protected void actionPerformedBtnListar(ActionEvent e) {
		txtS.setText("");
		imprimir("LISTADO DE COMPUTADORAS ALL-IN-ONE");
		for (int i = 0; i < 5; i++) {
			imprimir("");
			listarComputadora(i);
		}
		txtS.setCaretPosition(0);
	}

	// Muestra todos los datos de la computadora de posición i
	void listarComputadora(int i) {
		imprimir("Modelo          : " + Tienda.obtenerModelo(i));
		imprimir("Precio          : S/ " + String.format("%.2f", Tienda.obtenerPrecio(i)));
		imprimir("Procesador      : " + Tienda.obtenerProcesador(i));
		imprimir("Memoria RAM     : " + Tienda.obtenerMemoriaRam(i) + " GB");
		imprimir("Almacenamiento  : " + Tienda.obtenerAlmacenamiento(i) + " GB SSD");
		imprimir("Pantalla        : " + Tienda.obtenerPantalla(i) + " pulgadas");
	}

	// Agrega una línea de texto al JTextArea
	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}
