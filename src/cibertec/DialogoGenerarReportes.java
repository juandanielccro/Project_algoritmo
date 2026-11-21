package cibertec;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoGenerarReportes extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTipoReporte;
	private JComboBox<String> cboTipoReporte;
	private JButton btnCerrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Crea la caja de diálogo
	public DialogoGenerarReportes() {
		setTitle("Generar reportes");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 600, 430);
		getContentPane().setLayout(null);

		lblTipoReporte = new JLabel("Tipo de reporte");
		lblTipoReporte.setBounds(10, 14, 100, 14);
		getContentPane().add(lblTipoReporte);

		cboTipoReporte = new JComboBox<String>();
		cboTipoReporte.addItem("Ventas por modelo");
		cboTipoReporte.addItem("Comparación con la cuota diaria");
		cboTipoReporte.addItem("Modelo más vendido");
		cboTipoReporte.setBounds(110, 10, 280, 22);
		getContentPane().add(cboTipoReporte);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(485, 10, 89, 23);
		getContentPane().add(btnCerrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 45, 564, 335);
		getContentPane().add(scpScroll);

		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);

		cboTipoReporte.addActionListener(this);
		generarReporte(0);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboTipoReporte) {
			actionPerformedCboTipoReporte(e);
		}
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}

	// Al elegir un tipo de reporte se muestra inmediatamente
	protected void actionPerformedCboTipoReporte(ActionEvent e) {
		generarReporte(cboTipoReporte.getSelectedIndex());
	}

	// Cierra la caja de diálogo
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}

	// Muestra el reporte elegido
	void generarReporte(int tipo) {
		txtS.setText("");
		switch (tipo) {
			case 0:
				reporteVentasPorModelo();
				break;
			case 1:
				reporteComparacionCuota();
				break;
			default:
				reporteModeloMasVendido();
		}
		txtS.setCaretPosition(0);
	}

	// Reporte 1: ventas, unidades e importe acumulado de cada modelo
	void reporteVentasPorModelo() {
		imprimir("VENTAS POR MODELO");
		for (int i = 0; i < 5; i++) {
			imprimir("");
			imprimir("Modelo                 : " + Tienda.obtenerModelo(i));
			imprimir("Cantidad de ventas     : " + Tienda.obtenerCantidadVentas(i));
			imprimir("Unidades vendidas      : " + Tienda.obtenerUnidadesVendidas(i));
			imprimir("Importe acumulado      : S/ " + String.format("%.2f", Tienda.obtenerImporteAcumulado(i)));
		}
		imprimir("");
		imprimir("Total de ventas        : " + Tienda.numeroVentas);
		imprimir("Importe total general  : S/ " + String.format("%.2f", Tienda.obtenerImporteTotalGeneral()));
	}

	// Reporte 2: aporte de cada modelo a la cuota diaria
	void reporteComparacionCuota() {
		imprimir("COMPARACIÓN CON LA CUOTA DIARIA");
		imprimir("Cuota diaria           : S/ " + String.format("%.2f", Tienda.cuotaDiaria));
		for (int i = 0; i < 5; i++) {
			double importe = Tienda.obtenerImporteAcumulado(i);
			imprimir("");
			imprimir("Modelo                 : " + Tienda.obtenerModelo(i));
			imprimir("Importe acumulado      : S/ " + String.format("%.2f", importe));
			imprimir("Aporte a la cuota      : " + String.format("%.2f", Tienda.calcularPorcentajeCuota(importe)) + "%");
		}
		double total = Tienda.obtenerImporteTotalGeneral();
		imprimir("");
		imprimir("Importe total general  : S/ " + String.format("%.2f", total));
		imprimir("Avance de la cuota     : " + String.format("%.2f", Tienda.calcularPorcentajeCuota(total)) + "%");
		imprimir("Estado                 : " + evaluarCuota(total));
	}

	// Reporte 3: modelo con mayor cantidad de unidades vendidas
	void reporteModeloMasVendido() {
		imprimir("MODELO MÁS VENDIDO");
		imprimir("");
		if (Tienda.numeroVentas == 0) {
			imprimir("Aún no se ha realizado ninguna venta.");
			return;
		}
		int posicionMayor = 0;
		int unidadesMayor = Tienda.obtenerUnidadesVendidas(0);
		for (int i = 1; i < 5; i++) {
			if (Tienda.obtenerUnidadesVendidas(i) > unidadesMayor) {
				unidadesMayor = Tienda.obtenerUnidadesVendidas(i);
				posicionMayor = i;
			}
		}
		imprimir("Modelo                 : " + Tienda.obtenerModelo(posicionMayor));
		imprimir("Unidades vendidas      : " + unidadesMayor);
		imprimir("Importe acumulado      : S/ "
				+ String.format("%.2f", Tienda.obtenerImporteAcumulado(posicionMayor)));
	}

	// Indica si la cuota diaria fue alcanzada o cuánto falta
	String evaluarCuota(double total) {
		if (total >= Tienda.cuotaDiaria) {
			return "Cuota diaria alcanzada";
		}
		else {
			return "Faltan S/ " + String.format("%.2f", Tienda.cuotaDiaria - total) + " para la cuota";
		}
	}

	// Agrega una línea de texto al JTextArea
	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}
}
