package cibertec;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoModificarComputadora extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblModelo;
	private JLabel lblNuevoNombre;
	private JLabel lblPrecio;
	private JLabel lblProcesador;
	private JLabel lblMemoriaRam;
	private JLabel lblAlmacenamiento;
	private JLabel lblPantalla;
	private JComboBox<String> cboModelo;
	private JTextField txtNombreModelo;
	private JTextField txtPrecio;
	private JTextField txtProcesador;
	private JTextField txtMemoriaRam;
	private JTextField txtAlmacenamiento;
	private JTextField txtPantalla;
	private JButton btnCerrar;
	private JButton btnGuardar;

	// Crea la caja de diálogo
	public DialogoModificarComputadora() {
		setTitle("Modificar computadora");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 14, 120, 14);
		contentPanel.add(lblModelo);

		lblNuevoNombre = new JLabel("Nombre del modelo");
		lblNuevoNombre.setBounds(10, 42, 140, 14);
		contentPanel.add(lblNuevoNombre);

		lblPrecio = new JLabel("Precio (S/)");
		lblPrecio.setBounds(10, 70, 120, 14);
		contentPanel.add(lblPrecio);

		lblProcesador = new JLabel("Procesador");
		lblProcesador.setBounds(10, 98, 120, 14);
		contentPanel.add(lblProcesador);

		lblMemoriaRam = new JLabel("Memoria RAM (GB)");
		lblMemoriaRam.setBounds(10, 126, 120, 14);
		contentPanel.add(lblMemoriaRam);

		lblAlmacenamiento = new JLabel("Almacenamiento SSD (GB)");
		lblAlmacenamiento.setBounds(10, 154, 150, 14);
		contentPanel.add(lblAlmacenamiento);

		lblPantalla = new JLabel("Pantalla (pulgadas)");
		lblPantalla.setBounds(10, 182, 120, 14);
		contentPanel.add(lblPantalla);

		cboModelo = new JComboBox<String>();
		cboModelo.setBounds(160, 10, 200, 22);
		contentPanel.add(cboModelo);

		txtNombreModelo = new JTextField();
		txtNombreModelo.setBounds(160, 39, 200, 20);
		contentPanel.add(txtNombreModelo);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(160, 67, 200, 20);
		contentPanel.add(txtPrecio);

		txtProcesador = new JTextField();
		txtProcesador.setBounds(160, 95, 200, 20);
		contentPanel.add(txtProcesador);

		txtMemoriaRam = new JTextField();
		txtMemoriaRam.setBounds(160, 123, 200, 20);
		contentPanel.add(txtMemoriaRam);

		txtAlmacenamiento = new JTextField();
		txtAlmacenamiento.setBounds(160, 151, 200, 20);
		contentPanel.add(txtAlmacenamiento);

		txtPantalla = new JTextField();
		txtPantalla.setBounds(160, 179, 200, 20);
		contentPanel.add(txtPantalla);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(385, 10, 89, 23);
		contentPanel.add(btnCerrar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(385, 38, 89, 23);
		contentPanel.add(btnGuardar);

		// Carga los modelos y muestra los datos de la primera computadora
		Tienda.cargarModelos(cboModelo);
		cboModelo.addActionListener(this);
		mostrarDatos(0);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboModelo) {
			actionPerformedCboModelo(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Al elegir un modelo se muestran inmediatamente sus datos
	protected void actionPerformedCboModelo(ActionEvent e) {
		mostrarDatos(leerModelo());
	}

	// Valida los datos, guarda los cambios y cierra la caja de diálogo
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		int modelo = leerModelo();

		String nombreModelo = leerNombreModelo();
		if (nombreModelo.length() == 0) {
			mostrarError("Ingrese el nombre del modelo", txtNombreModelo);
			return;
		}

		double precio = leerPrecio();
		if (precio <= 0) {
			mostrarError("Ingrese un precio válido (número mayor que cero)", txtPrecio);
			return;
		}

		String procesador = leerProcesador();
		if (procesador.length() == 0) {
			mostrarError("Ingrese el procesador", txtProcesador);
			return;
		}

		int memoriaRam = leerMemoriaRam();
		if (memoriaRam <= 0) {
			mostrarError("Ingrese una memoria RAM válida (número entero mayor que cero)", txtMemoriaRam);
			return;
		}

		int almacenamiento = leerAlmacenamiento();
		if (almacenamiento <= 0) {
			mostrarError("Ingrese un almacenamiento válido (número entero mayor que cero)", txtAlmacenamiento);
			return;
		}

		double pantalla = leerPantalla();
		if (pantalla <= 0) {
			mostrarError("Ingrese un tamaño de pantalla válido (número mayor que cero)", txtPantalla);
			return;
		}

		Tienda.guardarComputadora(modelo, nombreModelo, precio, procesador, memoriaRam, almacenamiento, pantalla);
		JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente", "Modificar computadora",
				JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	// Cierra la caja de diálogo sin guardar
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}

	// Muestra en los JTextField los datos de la computadora de posición i
	void mostrarDatos(int i) {
		txtNombreModelo.setText(Tienda.obtenerModelo(i));
		txtPrecio.setText("" + Tienda.obtenerPrecio(i));
		txtProcesador.setText(Tienda.obtenerProcesador(i));
		txtMemoriaRam.setText("" + Tienda.obtenerMemoriaRam(i));
		txtAlmacenamiento.setText("" + Tienda.obtenerAlmacenamiento(i));
		txtPantalla.setText("" + Tienda.obtenerPantalla(i));
	}

	// Lee la posición del modelo seleccionado
	int leerModelo() {
		return cboModelo.getSelectedIndex();
	}

	// Lee el nombre del modelo (sin espacios al inicio ni al final)
	String leerNombreModelo() {
		return txtNombreModelo.getText().trim();
	}

	// Lee el procesador (sin espacios al inicio ni al final)
	String leerProcesador() {
		return txtProcesador.getText().trim();
	}

	// Lee el precio; retorna -1 si el texto no es un número
	double leerPrecio() {
		try {
			return Double.parseDouble(txtPrecio.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Lee la memoria RAM; retorna -1 si el texto no es un número entero
	int leerMemoriaRam() {
		try {
			return Integer.parseInt(txtMemoriaRam.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Lee el almacenamiento; retorna -1 si el texto no es un número entero
	int leerAlmacenamiento() {
		try {
			return Integer.parseInt(txtAlmacenamiento.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Lee el tamaño de pantalla; retorna -1 si el texto no es un número
	double leerPantalla() {
		try {
			return Double.parseDouble(txtPantalla.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Muestra un mensaje de error y coloca el cursor en la caja de texto indicada
	void mostrarError(String mensaje, JTextField txt) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		txt.requestFocus();
		txt.selectAll();
	}
}
