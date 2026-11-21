package cibertec;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoConsultarComputadora extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblModelo;
	private JLabel lblPrecio;
	private JLabel lblProcesador;
	private JLabel lblMemoriaRam;
	private JLabel lblAlmacenamiento;
	private JLabel lblPantalla;
	private JComboBox<String> cboModelo;
	private JTextField txtPrecio;
	private JTextField txtProcesador;
	private JTextField txtMemoriaRam;
	private JTextField txtAlmacenamiento;
	private JTextField txtPantalla;
	private JButton btnCerrar;

	// Crea la caja de diálogo
	public DialogoConsultarComputadora() {
		setTitle("Consultar computadora");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 14, 120, 14);
		contentPanel.add(lblModelo);

		lblPrecio = new JLabel("Precio (S/)");
		lblPrecio.setBounds(10, 42, 120, 14);
		contentPanel.add(lblPrecio);

		lblProcesador = new JLabel("Procesador");
		lblProcesador.setBounds(10, 70, 120, 14);
		contentPanel.add(lblProcesador);

		lblMemoriaRam = new JLabel("Memoria RAM (GB)");
		lblMemoriaRam.setBounds(10, 98, 120, 14);
		contentPanel.add(lblMemoriaRam);

		lblAlmacenamiento = new JLabel("Almacenamiento SSD (GB)");
		lblAlmacenamiento.setBounds(10, 126, 150, 14);
		contentPanel.add(lblAlmacenamiento);

		lblPantalla = new JLabel("Pantalla (pulgadas)");
		lblPantalla.setBounds(10, 154, 120, 14);
		contentPanel.add(lblPantalla);

		cboModelo = new JComboBox<String>();
		cboModelo.setBounds(160, 10, 200, 22);
		contentPanel.add(cboModelo);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(160, 39, 200, 20);
		contentPanel.add(txtPrecio);

		txtProcesador = new JTextField();
		txtProcesador.setEditable(false);
		txtProcesador.setBounds(160, 67, 200, 20);
		contentPanel.add(txtProcesador);

		txtMemoriaRam = new JTextField();
		txtMemoriaRam.setEditable(false);
		txtMemoriaRam.setBounds(160, 95, 200, 20);
		contentPanel.add(txtMemoriaRam);

		txtAlmacenamiento = new JTextField();
		txtAlmacenamiento.setEditable(false);
		txtAlmacenamiento.setBounds(160, 123, 200, 20);
		contentPanel.add(txtAlmacenamiento);

		txtPantalla = new JTextField();
		txtPantalla.setEditable(false);
		txtPantalla.setBounds(160, 151, 200, 20);
		contentPanel.add(txtPantalla);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(385, 10, 89, 23);
		contentPanel.add(btnCerrar);

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
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Al elegir un modelo se muestran inmediatamente sus datos
	protected void actionPerformedCboModelo(ActionEvent e) {
		mostrarDatos(leerModelo());
	}

	// Cierra la caja de diálogo
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}

	// Lee la posición del modelo seleccionado
	int leerModelo() {
		return cboModelo.getSelectedIndex();
	}

	// Muestra en los JTextField los datos de la computadora de posición i
	void mostrarDatos(int i) {
		txtPrecio.setText(String.format("%.2f", Tienda.obtenerPrecio(i)));
		txtProcesador.setText(Tienda.obtenerProcesador(i));
		txtMemoriaRam.setText("" + Tienda.obtenerMemoriaRam(i));
		txtAlmacenamiento.setText("" + Tienda.obtenerAlmacenamiento(i));
		txtPantalla.setText("" + Tienda.obtenerPantalla(i));
	}
}
