package cibertec;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class DialogoHistorialVentas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnActualizar;
	private JButton btnExportar;
	private JButton btnCerrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	public DialogoHistorialVentas() {
		setTitle("Historial de ventas y exportación");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 600, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(10, 10, 105, 23);
		contentPanel.add(btnActualizar);

		btnExportar = new JButton("Exportar .TXT");
		btnExportar.addActionListener(this);
		btnExportar.setBounds(125, 10, 120, 23);
		contentPanel.add(btnExportar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(485, 10, 89, 23);
		contentPanel.add(btnCerrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 45, 564, 325);
		contentPanel.add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);

		cargarHistorial();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			cargarHistorial();
		}
		if (e.getSource() == btnExportar) {
			actionPerformedBtnExportar(e);
		}
		if (e.getSource() == btnCerrar) {
			dispose();
		}
	}

	void cargarHistorial() {
		txtS.setText("");
		imprimir("HISTORIAL DE BOLETAS DE LA SESIÓN - TIENDA 1.0");
		imprimir("============================================================");
		if (Tienda.historialVentas.length() == 0) {
			imprimir("Aún no se han registrado ventas en esta sesión.");
		}
		else {
			imprimir(Tienda.historialVentas);
		}
		imprimir("============================================================");
		imprimir("Total de ventas registradas : " + Tienda.numeroVentas);
		imprimir("Importe total acumulado     : S/ " + String.format("%.2f", Tienda.obtenerImporteTotalGeneral()));
		txtS.setCaretPosition(0);
	}

	protected void actionPerformedBtnExportar(ActionEvent e) {
		if (txtS.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay contenido para exportar",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFileChooser selector = new JFileChooser();
		selector.setDialogTitle("Guardar historial de ventas");
		selector.setSelectedFile(new File("historial_ventas_tienda.txt"));

		int resultado = selector.showSaveDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			File archivo = selector.getSelectedFile();
			if (!archivo.getName().toLowerCase().endsWith(".txt")) {
				archivo = new File(archivo.getAbsolutePath() + ".txt");
			}

			try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
				pw.print(txtS.getText());
				JOptionPane.showMessageDialog(this, "Archivo guardado correctamente en:\n" + archivo.getAbsolutePath(),
						"Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}