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
import javax.swing.border.EmptyBorder;

public class DialogoCompararModelos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblModeloA;
	private JLabel lblModeloB;
	private JComboBox<String> cboModeloA;
	private JComboBox<String> cboModeloB;
	private JButton btnComparar;
	private JButton btnCerrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	public DialogoCompararModelos() {
		setTitle("Comparar modelos All-in-One");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 620, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblModeloA = new JLabel("Modelo A");
		lblModeloA.setBounds(10, 14, 80, 14);
		contentPanel.add(lblModeloA);

		cboModeloA = new JComboBox<String>();
		cboModeloA.setBounds(80, 10, 210, 22);
		contentPanel.add(cboModeloA);

		lblModeloB = new JLabel("Modelo B");
		lblModeloB.setBounds(10, 44, 80, 14);
		contentPanel.add(lblModeloB);

		cboModeloB = new JComboBox<String>();
		cboModeloB.setBounds(80, 40, 210, 22);
		contentPanel.add(cboModeloB);

		btnComparar = new JButton("Comparar");
		btnComparar.addActionListener(this);
		btnComparar.setBounds(320, 10, 110, 23);
		contentPanel.add(btnComparar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(320, 40, 110, 23);
		contentPanel.add(btnCerrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 76, 584, 274);
		contentPanel.add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);

		Tienda.cargarModelos(cboModeloA);
		Tienda.cargarModelos(cboModeloB);
		if (cboModeloB.getItemCount() > 1) {
			cboModeloB.setSelectedIndex(1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnComparar) {
			actionPerformedBtnComparar(e);
		}
		if (e.getSource() == btnCerrar) {
			dispose();
		}
	}

	protected void actionPerformedBtnComparar(ActionEvent e) {
		int a = cboModeloA.getSelectedIndex();
		int b = cboModeloB.getSelectedIndex();

		if (a == b) {
			JOptionPane.showMessageDialog(this, "Seleccione dos modelos distintos para comparar",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		double precioA = Tienda.obtenerPrecio(a);
		double precioB = Tienda.obtenerPrecio(b);
		int ramA = Tienda.obtenerMemoriaRam(a);
		int ramB = Tienda.obtenerMemoriaRam(b);
		int ssdA = Tienda.obtenerAlmacenamiento(a);
		int ssdB = Tienda.obtenerAlmacenamiento(b);
		double panA = Tienda.obtenerPantalla(a);
		double panB = Tienda.obtenerPantalla(b);

		txtS.setText("");
		imprimir("COMPARATIVA DE COMPUTADORAS ALL-IN-ONE");
		imprimir("=========================================================================");
		imprimir(String.format("%-18s | %-24s | %-24s", "Característica", "Modelo A", "Modelo B"));
		imprimir("-------------------------------------------------------------------------");
		imprimir(String.format("%-18s | %-24s | %-24s", "Modelo", recorte(Tienda.obtenerModelo(a), 24),
				recorte(Tienda.obtenerModelo(b), 24)));
		imprimir(String.format("%-18s | %-24s | %-24s", "Precio", "S/ " + String.format("%.2f", precioA),
				"S/ " + String.format("%.2f", precioB)));
		imprimir(String.format("%-18s | %-24s | %-24s", "Procesador", recorte(Tienda.obtenerProcesador(a), 24),
				recorte(Tienda.obtenerProcesador(b), 24)));
		imprimir(String.format("%-18s | %-24s | %-24s", "Memoria RAM", ramA + " GB", ramB + " GB"));
		imprimir(String.format("%-18s | %-24s | %-24s", "Almacenamiento", ssdA + " GB SSD", ssdB + " GB SSD"));
		imprimir(String.format("%-18s | %-24s | %-24s", "Pantalla", panA + " pulg.", panB + " pulg."));
		imprimir("=========================================================================");
		imprimir("");

		double diferenciaPrecio = Math.abs(precioA - precioB);
		if (precioA < precioB) {
			imprimir("* Precio: Modelo A es más económico por S/ " + String.format("%.2f", diferenciaPrecio));
		}
		else if (precioB < precioA) {
			imprimir("* Precio: Modelo B es más económico por S/ " + String.format("%.2f", diferenciaPrecio));
		}
		else {
			imprimir("* Precio: Ambos cuestan lo mismo");
		}

		if (ramA > ramB) {
			imprimir("* RAM: Modelo A tiene más memoria (" + ramA + " GB vs " + ramB + " GB)");
		}
		else if (ramB > ramA) {
			imprimir("* RAM: Modelo B tiene más memoria (" + ramB + " GB vs " + ramA + " GB)");
		}

		if (ssdA > ssdB) {
			imprimir("* SSD: Modelo A tiene mayor almacenamiento (" + ssdA + " GB vs " + ssdB + " GB)");
		}
		else if (ssdB > ssdA) {
			imprimir("* SSD: Modelo B tiene mayor almacenamiento (" + ssdB + " GB vs " + ssdA + " GB)");
		}

		if (panA > panB) {
			imprimir("* Pantalla: Modelo A tiene pantalla más grande (" + panA + "\" vs " + panB + "\")");
		}
		else if (panB > panA) {
			imprimir("* Pantalla: Modelo B tiene pantalla más grande (" + panB + "\" vs " + panA + "\")");
		}

		txtS.setCaretPosition(0);
	}

	String recorte(String texto, int max) {
		if (texto.length() <= max) {
			return texto;
		}
		return texto.substring(0, max - 3) + "...";
	}

	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}