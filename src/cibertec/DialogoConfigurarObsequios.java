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

public class DialogoConfigurarObsequios extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblObsequio1;
	private JLabel lblObsequio2;
	private JLabel lblObsequio3;
	private JTextField txtObsequio1;
	private JTextField txtObsequio2;
	private JTextField txtObsequio3;
	private JButton btnAceptar;
	private JButton btnCancelar;

	// Crea la caja de diálogo
	public DialogoConfigurarObsequios() {
		setTitle("Configurar obsequios");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 145);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblObsequio1 = new JLabel("1 unidad");
		lblObsequio1.setBounds(10, 14, 120, 14);
		contentPanel.add(lblObsequio1);

		lblObsequio2 = new JLabel("2 a 5 unidades");
		lblObsequio2.setBounds(10, 42, 120, 14);
		contentPanel.add(lblObsequio2);

		lblObsequio3 = new JLabel("6 a más unidades");
		lblObsequio3.setBounds(10, 70, 120, 14);
		contentPanel.add(lblObsequio3);

		txtObsequio1 = new JTextField();
		txtObsequio1.setBounds(130, 11, 180, 20);
		contentPanel.add(txtObsequio1);

		txtObsequio2 = new JTextField();
		txtObsequio2.setBounds(130, 39, 180, 20);
		contentPanel.add(txtObsequio2);

		txtObsequio3 = new JTextField();
		txtObsequio3.setBounds(130, 67, 180, 20);
		contentPanel.add(txtObsequio3);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(335, 10, 89, 23);
		contentPanel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(335, 38, 89, 23);
		contentPanel.add(btnCancelar);

		mostrarObsequiosActuales();
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

	// Valida y reemplaza los obsequios
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		String obsequio1 = leerObsequio(txtObsequio1);
		if (obsequio1.length() == 0) {
			mostrarError("Ingrese el obsequio para 1 unidad", txtObsequio1);
			return;
		}
		String obsequio2 = leerObsequio(txtObsequio2);
		if (obsequio2.length() == 0) {
			mostrarError("Ingrese el obsequio para 2 a 5 unidades", txtObsequio2);
			return;
		}
		String obsequio3 = leerObsequio(txtObsequio3);
		if (obsequio3.length() == 0) {
			mostrarError("Ingrese el obsequio para 6 a más unidades", txtObsequio3);
			return;
		}

		Tienda.obsequio1 = obsequio1;
		Tienda.obsequio2 = obsequio2;
		Tienda.obsequio3 = obsequio3;
		dispose();
	}

	// Cierra la caja de diálogo sin hacer cambios
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		dispose();
	}

	// Muestra los valores actuales de las variables globales
	void mostrarObsequiosActuales() {
		txtObsequio1.setText(Tienda.obsequio1);
		txtObsequio2.setText(Tienda.obsequio2);
		txtObsequio3.setText(Tienda.obsequio3);
	}

	// Lee un obsequio sin espacios al inicio ni al final
	String leerObsequio(JTextField txt) {
		return txt.getText().trim();
	}

	// Muestra un mensaje de error y coloca el cursor en la caja de texto indicada
	void mostrarError(String mensaje, JTextField txt) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		txt.requestFocus();
		txt.selectAll();
	}
}
