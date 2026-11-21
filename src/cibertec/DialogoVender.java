package cibertec;

import java.awt.BorderLayout;
import java.awt.Font;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoVender extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblModelo;
	private JLabel lblPrecio;
	private JLabel lblCantidad;
	private JComboBox<String> cboModelo;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JButton btnVender;
	private JButton btnCerrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Crea la caja de diálogo
	public DialogoVender() {
		setTitle("Vender");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 520, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 14, 90, 14);
		contentPanel.add(lblModelo);

		lblPrecio = new JLabel("Precio (S/)");
		lblPrecio.setBounds(10, 42, 90, 14);
		contentPanel.add(lblPrecio);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 70, 90, 14);
		contentPanel.add(lblCantidad);

		cboModelo = new JComboBox<String>();
		cboModelo.setBounds(100, 10, 250, 22);
		contentPanel.add(cboModelo);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(100, 39, 250, 20);
		contentPanel.add(txtPrecio);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(100, 67, 250, 20);
		contentPanel.add(txtCantidad);

		btnVender = new JButton("Vender");
		btnVender.addActionListener(this);
		btnVender.setBounds(395, 10, 99, 23);
		contentPanel.add(btnVender);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(395, 38, 99, 23);
		contentPanel.add(btnCerrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 100, 484, 210);
		contentPanel.add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);

		// Carga los modelos y muestra el precio del primero
		Tienda.cargarModelos(cboModelo);
		cboModelo.addActionListener(this);
		mostrarPrecio(0);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboModelo) {
			actionPerformedCboModelo(e);
		}
		if (e.getSource() == btnVender) {
			actionPerformedBtnVender(e);
		}
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Al elegir un modelo se muestra inmediatamente su precio unitario
	protected void actionPerformedCboModelo(ActionEvent e) {
		mostrarPrecio(leerModelo());
		txtS.setText("");
	}

	// Efectúa la venta
	protected void actionPerformedBtnVender(ActionEvent e) {
		// Entrada de datos
		int modelo = leerModelo();
		int cantidad = leerCantidad();

		// Validación de la cantidad
		if (cantidad <= 0) {
			JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida (número entero mayor que cero)",
					"Error", JOptionPane.ERROR_MESSAGE);
			txtCantidad.requestFocus();
			txtCantidad.selectAll();
			return;
		}

		// Proceso de cálculo
		double precio = Tienda.obtenerPrecio(modelo);
		double importeCompra = calcularImporteCompra(precio, cantidad);
		double importeDescuento = calcularImporteDescuento(importeCompra, cantidad);
		double importePagar = calcularImportePagar(importeCompra, importeDescuento);
		String obsequio = determinarObsequio(cantidad);

		// Salida de resultados
		mostrarBoleta(Tienda.obtenerModelo(modelo), precio, cantidad, importeCompra, importeDescuento,
				importePagar, obsequio);
		
		//Agrego_JUAN_CC
		Tienda.agregarBoletaHistorial(txtS.getText());

		// Registro de la venta y alerta cada cinco ventas
		Tienda.registrarVenta(modelo, cantidad, importePagar);
		mostrarAvanceVentas();
	}

	// Cierra la caja de diálogo
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}

	// Lee la posición del modelo seleccionado
	int leerModelo() {
		return cboModelo.getSelectedIndex();
	}

	// Lee la cantidad; retorna -1 si el texto no es un número entero
	int leerCantidad() {
		try {
			return Integer.parseInt(txtCantidad.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Muestra el precio unitario del modelo de posición i
	void mostrarPrecio(int i) {
		txtPrecio.setText(String.format("%.2f", Tienda.obtenerPrecio(i)));
	}

	// Calcula el importe de la compra
	double calcularImporteCompra(double precio, int cantidad) {
		return precio * cantidad;
	}

	// Retorna el porcentaje de descuento según la cantidad de unidades (Tabla 1)
	double obtenerPorcentajeDescuento(int cantidad) {
		if (cantidad <= 5) {
			return Tienda.porcentaje1;
		}
		else if (cantidad <= 10) {
			return Tienda.porcentaje2;
		}
		else if (cantidad <= 15) {
			return Tienda.porcentaje3;
		}
		else {
			return Tienda.porcentaje4;
		}
	}

	// Calcula el importe del descuento
	double calcularImporteDescuento(double importeCompra, int cantidad) {
		return importeCompra * obtenerPorcentajeDescuento(cantidad) / 100;
	}

	// Calcula el importe a pagar
	double calcularImportePagar(double importeCompra, double importeDescuento) {
		return importeCompra - importeDescuento;
	}

	// Determina el obsequio según la cantidad de unidades (Tabla 2)
	String determinarObsequio(int cantidad) {
		if (cantidad == 1) {
			return Tienda.obsequio1;
		}
		else if (cantidad <= 5) {
			return Tienda.obsequio2;
		}
		else {
			return Tienda.obsequio3;
		}
	}

	// Muestra la boleta de venta
	void mostrarBoleta(String modelo, double precio, int cantidad, double importeCompra,
			double importeDescuento, double importePagar, String obsequio) {
		txtS.setText("");
		imprimir("BOLETA DE VENTA");
		imprimir("");
		imprimir("Modelo             : " + modelo);
		imprimir("Precio unitario    : S/ " + String.format("%.2f", precio));
		imprimir("Cantidad           : " + cantidad);
		imprimir("Importe compra     : S/ " + String.format("%.2f", importeCompra));
		imprimir("Importe descuento  : S/ " + String.format("%.2f", importeDescuento) + "  ("
				+ obtenerPorcentajeDescuento(cantidad) + "%)");
		imprimir("Importe a pagar    : S/ " + String.format("%.2f", importePagar));
		imprimir("Obsequio           : " + obsequio);
	}

	// Cada cinco ventas muestra el avance respecto a la cuota diaria
	void mostrarAvanceVentas() {
		if (Tienda.numeroVentas % 5 == 0) {
			double importeTotalGeneral = Tienda.obtenerImporteTotalGeneral();
			double porcentajeCuota = Tienda.calcularPorcentajeCuota(importeTotalGeneral);
			JOptionPane.showMessageDialog(this,
					"Venta Nro. " + Tienda.numeroVentas + "\n"
					+ "Importe total general acumulado : S/ " + String.format("%.2f", importeTotalGeneral) + "\n"
					+ "Porcentaje de la cuota diaria : " + String.format("%.2f", porcentajeCuota) + "%",
					"Avance de ventas", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Agrega una línea de texto al JTextArea
	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}
