package cibertec;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogoRecomendadorPC extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblUso;
	private JLabel lblPresupuesto;
	private JLabel lblPantalla;
	private JComboBox<String> cboUso;
	private JTextField txtPresupuesto;
	private JComboBox<String> cboPantalla;
	private JButton btnRecomendar;
	private JButton btnCerrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	public DialogoRecomendadorPC() {
		setTitle("Recomendador de PC All-in-One");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 560, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblUso = new JLabel("Tipo de uso");
		lblUso.setBounds(10, 14, 130, 14);
		contentPanel.add(lblUso);

		lblPresupuesto = new JLabel("Presupuesto máx. (S/)");
		lblPresupuesto.setBounds(10, 42, 140, 14);
		contentPanel.add(lblPresupuesto);

		lblPantalla = new JLabel("Pantalla preferida");
		lblPantalla.setBounds(10, 70, 130, 14);
		contentPanel.add(lblPantalla);

		cboUso = new JComboBox<String>();
		cboUso.addItem("Oficina y estudios");
		cboUso.addItem("Programación y multitarea");
		cboUso.addItem("Diseño y multimedia");
		cboUso.addItem("Económico / básico");
		cboUso.setBounds(155, 10, 220, 22);
		contentPanel.add(cboUso);

		txtPresupuesto = new JTextField("4500");
		txtPresupuesto.setBounds(155, 39, 220, 20);
		contentPanel.add(txtPresupuesto);

		cboPantalla = new JComboBox<String>();
		cboPantalla.addItem("Cualquiera");
		cboPantalla.addItem("23.8 pulgadas");
		cboPantalla.addItem("27.0 pulgadas");
		cboPantalla.setBounds(155, 67, 220, 22);
		contentPanel.add(cboPantalla);

		btnRecomendar = new JButton("Recomendar");
		btnRecomendar.addActionListener(this);
		btnRecomendar.setBounds(415, 10, 115, 23);
		contentPanel.add(btnRecomendar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(415, 38, 115, 23);
		contentPanel.add(btnCerrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 102, 524, 258);
		contentPanel.add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRecomendar) {
			actionPerformedBtnRecomendar(e);
		}
		if (e.getSource() == btnCerrar) {
			dispose();
		}
	}

	protected void actionPerformedBtnRecomendar(ActionEvent e) {
		double presupuesto = leerPresupuesto();
		if (presupuesto <= 0) {
			JOptionPane.showMessageDialog(this, "Ingrese un presupuesto válido mayor que cero",
					"Error", JOptionPane.ERROR_MESSAGE);
			txtPresupuesto.requestFocus();
			txtPresupuesto.selectAll();
			return;
		}

		int uso = cboUso.getSelectedIndex();
		int opcionPantalla = cboPantalla.getSelectedIndex();

		int mejorIndice = -1;
		double mejorPuntaje = -1;
		int mejorFueraPresupuesto = 0;
		double menorPrecioFuera = Double.MAX_VALUE;

		for (int i = 0; i < 5; i++) {
			double precio = Tienda.obtenerPrecio(i);
			int ram = Tienda.obtenerMemoriaRam(i);
			int ssd = Tienda.obtenerAlmacenamiento(i);
			double pantalla = Tienda.obtenerPantalla(i);

			boolean cumplePantalla = true;
			if (opcionPantalla == 1 && Math.abs(pantalla - 23.8) > 0.1) {
				cumplePantalla = false;
			}
			else if (opcionPantalla == 2 && Math.abs(pantalla - 27.0) > 0.1) {
				cumplePantalla = false;
			}

			double puntaje = calcularPuntaje(uso, precio, ram, ssd, pantalla);
			if (cumplePantalla) {
				puntaje += 10.0;
			}

			if (precio <= presupuesto) {
				if (puntaje > mejorPuntaje) {
					mejorPuntaje = puntaje;
					mejorIndice = i;
				}
			}
			else if (precio < menorPrecioFuera) {
				menorPrecioFuera = precio;
				mejorFueraPresupuesto = i;
			}
		}

		txtS.setText("");
		imprimir("RESULTADO DE RECOMENDACIÓN");
		imprimir("Uso seleccionado : " + cboUso.getSelectedItem());
		imprimir("Presupuesto máx. : S/ " + String.format("%.2f", presupuesto));
		imprimir("Pantalla         : " + cboPantalla.getSelectedItem());
		imprimir("------------------------------------------------------------");

		if (mejorIndice != -1) {
			imprimir("MEJOR OPCIÓN DENTRO DE TU PRESUPUESTO:");
			mostrarDetalleModelo(mejorIndice);
		}
		else {
			imprimir("Ningún modelo entra en S/ " + String.format("%.2f", presupuesto) + ".");
			imprimir("Alternativa más cercana por precio:");
			mostrarDetalleModelo(mejorFueraPresupuesto);
		}
		txtS.setCaretPosition(0);
	}

	double calcularPuntaje(int uso, double precio, int ram, int ssd, double pantalla) {
		double puntaje = 0;
		switch (uso) {
			case 0: // Oficina y estudios
				puntaje = ram * 2.0 + ssd * 0.015 + (4500.0 - precio) * 0.004;
				break;
			case 1: // Programación y multitarea
				puntaje = ram * 3.5 + ssd * 0.02 + pantalla * 1.2;
				break;
			case 2: // Diseño y multimedia
				puntaje = pantalla * 3.0 + ssd * 0.025 + ram * 2.5;
				break;
			default: // Económico / básico
				puntaje = (6000.0 - precio) * 0.01 + ram * 1.5;
				break;
		}
		return puntaje;
	}

	void mostrarDetalleModelo(int i) {
		imprimir("Modelo           : " + Tienda.obtenerModelo(i));
		imprimir("Precio           : S/ " + String.format("%.2f", Tienda.obtenerPrecio(i)));
		imprimir("Procesador       : " + Tienda.obtenerProcesador(i));
		imprimir("Memoria RAM      : " + Tienda.obtenerMemoriaRam(i) + " GB");
		imprimir("Almacenamiento   : " + Tienda.obtenerAlmacenamiento(i) + " GB SSD");
		imprimir("Pantalla         : " + Tienda.obtenerPantalla(i) + " pulgadas");
	}

	double leerPresupuesto() {
		try {
			return Double.parseDouble(txtPresupuesto.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}