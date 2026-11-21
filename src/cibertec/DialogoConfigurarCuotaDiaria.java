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

public class DialogoConfigurarCuotaDiaria extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCuotaDiaria;
	private JTextField txtCuotaDiaria;
	private JButton btnAceptar;
	private JButton btnCancelar;

	// Crea la caja de diálogo
	public DialogoConfigurarCuotaDiaria() {
		setTitle("Configurar cuota diaria");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 115);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblCuotaDiaria = new JLabel("Cuota diaria (S/)");
		lblCuotaDiaria.setBounds(10, 14, 120, 14);
		contentPanel.add(lblCuotaDiaria);

		txtCuotaDiaria = new JTextField();
		txtCuotaDiaria.setBounds(130, 11, 150, 20);
		contentPanel.add(txtCuotaDiaria);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(335, 10, 89, 23);
		contentPanel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(335, 38, 89, 23);
		contentPanel.add(btnCancelar);

		txtCuotaDiaria.setText("" + Tienda.cuotaDiaria);
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

	// Valida y reemplaza la cuota diaria
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		double cuota = leerCuotaDiaria();
		if (cuota <= 0) {
			JOptionPane.showMessageDialog(this, "Ingrese una cuota diaria válida (número mayor que cero)",
					"Error", JOptionPane.ERROR_MESSAGE);
			txtCuotaDiaria.requestFocus();
			txtCuotaDiaria.selectAll();
			return;
		}
		Tienda.cuotaDiaria = cuota;
		dispose();
	}

	// Cierra la caja de diálogo sin hacer cambios
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		dispose();
	}

	// Lee la cuota diaria; retorna -1 si el texto no es un número
	double leerCuotaDiaria() {
		try {
			return Double.parseDouble(txtCuotaDiaria.getText().trim());
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}
}
