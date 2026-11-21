package cibertec;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoConfigurarDescuentos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblRango1;
	private JLabel lblRango2;
	private JLabel lblRango3;
	private JLabel lblRango4;
	private JLabel lblSimbolo1;
	private JLabel lblSimbolo2;
	private JLabel lblSimbolo3;
	private JLabel lblSimbolo4;
	private JTextField txtPorcentaje1;
	private JTextField txtPorcentaje2;
	private JTextField txtPorcentaje3;
	private JTextField txtPorcentaje4;
	private JButton btnAceptar;
	private JButton btnCancelar;

	// Crea la caja de diálogo
	public DialogoConfigurarDescuentos() {
		setTitle("Configurar porcentajes de descuento");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblRango1 = new JLabel("1 a 5 unidades");
		lblRango1.setBounds(10, 14, 130, 14);
		contentPanel.add(lblRango1);

		lblRango2 = new JLabel("6 a 10 unidades");
		lblRango2.setBounds(10, 42, 130, 14);
		contentPanel.add(lblRango2);

		lblRango3 = new JLabel("11 a 15 unidades");
		lblRango3.setBounds(10, 70, 130, 14);
		contentPanel.add(lblRango3);

		lblRango4 = new JLabel("Más de 15 unidades");
		lblRango4.setBounds(10, 98, 130, 14);
		contentPanel.add(lblRango4);

		txtPorcentaje1 = new JTextField();
		txtPorcentaje1.setBounds(150, 11, 100, 20);
		contentPanel.add(txtPorcentaje1);

		txtPorcentaje2 = new JTextField();
		txtPorcentaje2.setBounds(150, 39, 100, 20);
		contentPanel.add(txtPorcentaje2);

		txtPorcentaje3 = new JTextField();
		txtPorcentaje3.setBounds(150, 67, 100, 20);
		contentPanel.add(txtPorcentaje3);

		txtPorcentaje4 = new JTextField();
		txtPorcentaje4.setBounds(150, 95, 100, 20);
		contentPanel.add(txtPorcentaje4);

		lblSimbolo1 = new JLabel("%");
		lblSimbolo1.setBounds(260, 14, 20, 14);
		contentPanel.add(lblSimbolo1);

		lblSimbolo2 = new JLabel("%");
		lblSimbolo2.setBounds(260, 42, 20, 14);
		contentPanel.add(lblSimbolo2);

		lblSimbolo3 = new JLabel("%");
		lblSimbolo3.setBounds(260, 70, 20, 14);
		contentPanel.add(lblSimbolo3);

		lblSimbolo4 = new JLabel("%");
		lblSimbolo4.setBounds(260, 98, 20, 14);
		contentPanel.add(lblSimbolo4);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(335, 10, 89, 23);
		contentPanel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(335, 38, 89, 23);
		contentPanel.add(btnCancelar);

		mostrarPorcentajesActuales();
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
	}

	// Valida y reemplaza los porcentajes de descuento
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		double porcentaje1 = leerPorcentaje(txtPorcentaje1);
		if (!esPorcentajeValido(porcentaje1)) {
			mostrarError("El porcentaje de 1 a 5 unidades debe ser un número entre 0 y 100", txtPorcentaje1);
			return;
		}
		double porcentaje2 = leerPorcentaje(txtPorcentaje2);
		if (!esPorcentajeValido(porcentaje2)) {
			mostrarError("El porcentaje de 6 a 10 unidades debe ser un número entre 0 y 100", txtPorcentaje2);
			return;
		}
		double porcentaje3 = leerPorcentaje(txtPorcentaje3);
		if (!esPorcentajeValido(porcentaje3)) {
			mostrarError("El porcentaje de 11 a 15 unidades debe ser un número entre 0 y 100", txtPorcentaje3);
			return;
		}
		double porcentaje4 = leerPorcentaje(txtPorcentaje4);
		if (!esPorcentajeValido(porcentaje4)) {
			mostrarError("El porcentaje de más de 15 unidades debe ser un número entre 0 y 100", txtPorcentaje4);
			return;
		}
		if (porcentaje1 > porcentaje2 || porcentaje2 > porcentaje3 || porcentaje3 > porcentaje4) {
			mostrarError("A mayor cantidad de unidades el descuento no puede ser menor.\n"
					+ "Verifique que los porcentajes vayan de menor a mayor", txtPorcentaje1);
			return;
		}

		Tienda.porcentaje1 = porcentaje1;
		Tienda.porcentaje2 = porcentaje2;
		Tienda.porcentaje3 = porcentaje3;
		Tienda.porcentaje4 = porcentaje4;
		dispose();
	}

	// Cierra la caja de diálogo sin hacer cambios
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		dispose();
	}

	// Muestra los valores actuales de las variables globales
	void mostrarPorcentajesActuales() {
		txtPorcentaje1.setText("" + Tienda.porcentaje1);
		txtPorcentaje2.setText("" + Tienda.porcentaje2);
		txtPorcentaje3.setText("" + Tienda.porcentaje3);
		txtPorcentaje4.setText("" + Tienda.porcentaje4);
	}

	// Lee un porcentaje; retorna -1 si el texto no es un número
	double leerPorcentaje(JTextField txt) {
		try {
			return Double.parseDouble(txt.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	// Verifica que el porcentaje esté entre 0 y 100
	boolean esPorcentajeValido(double porcentaje) {
		return porcentaje >= 0 && porcentaje <= 100;
	}

	// Muestra un mensaje de error y coloca el cursor en la caja de texto indicada
	void mostrarError(String mensaje, JTextField txt) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		txt.requestFocus();
		txt.selectAll();
	}
}
