package cibertec;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tienda extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmConsultarComputadora;
	private JMenuItem mntmModificarComputadora;
	private JMenuItem mntmListarComputadoras;
	private JMenu mnVentas;
	private JMenuItem mntmVender;
	private JMenuItem mntmGenerarReportes;
	private JMenu mnConfiguracion;
	private JMenuItem mntmConfigurarDescuentos;
	private JMenuItem mntmConfigurarObsequios;
	private JMenuItem mntmConfigurarCuotaDiaria;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDeTienda;
	private JLabel lblTitulo;
	private JLabel lblSubtitulo;
	private JLabel lblDetalle;
	//___________________________________________________________________________________________________________________
	//AGREGADO_POR_JUAN_CC
	private JMenuItem mntmCompararModelos;
	private JMenuItem mntmRecomendadorPC;
	private JMenuItem mntmHistorialVentas;
    //--------------------------------------------------------------------------------------------------------------------
	// Datos mínimos de la primera computadora All-in-One
	public static String modelo0 = "HP ProOne 440 G9";
	public static double precio0 = 3429.0;
	public static String procesador0 = "Intel Core i5-14500";
	public static int memoriaRam0 = 16;
	public static int almacenamiento0 = 1000;
	public static double pantalla0 = 23.8;
	
	//____________________________________________________________________________________________________________
	//AGREGO_JUAN_CC
	public static String historialVentas = "";
	//____________________________________________________________________________________________________________

	// Datos mínimos de la segunda computadora All-in-One
	public static String modelo1 = "Lenovo IdeaCentre AIO 24IRH9";
	public static double precio1 = 4629.0;
	public static String procesador1 = "Intel Core i7-13620H";
	public static int memoriaRam1 = 16;
	public static int almacenamiento1 = 512;
	public static double pantalla1 = 23.8;

	// Datos mínimos de la tercera computadora All-in-One
	public static String modelo2 = "ASUS AIO P470VAK";
	public static double precio2 = 4399.0;
	public static String procesador2 = "Intel Core 7 240H";
	public static int memoriaRam2 = 16;
	public static int almacenamiento2 = 1000;
	public static double pantalla2 = 27.0;

	// Datos mínimos de la cuarta computadora All-in-One
	public static String modelo3 = "Acer Aspire C27";
	public static double precio3 = 3885.0;
	public static String procesador3 = "AMD Ryzen 5 7430U";
	public static int memoriaRam3 = 16;
	public static int almacenamiento3 = 512;
	public static double pantalla3 = 27.0;

	// Datos mínimos de la quinta computadora All-in-One
	public static String modelo4 = "MSI Modern AM242TP";
	public static double precio4 = 3905.0;
	public static String procesador4 = "Intel Core 3 100U";
	public static int memoriaRam4 = 8;
	public static int almacenamiento4 = 512;
	public static double pantalla4 = 23.8;

	// Porcentajes de descuento
	public static double porcentaje1 = 5.0;
	public static double porcentaje2 = 7.5;
	public static double porcentaje3 = 10.0;
	public static double porcentaje4 = 12.5;

	// Obsequios
	public static String obsequio1 = "Mouse inalámbrico";
	public static String obsequio2 = "Kit teclado y mouse";
	public static String obsequio3 = "Audífonos Bluetooth";

	// Cuota diaria de ventas (en soles)
	public static double cuotaDiaria = 200000.0;

	// Contador general de ventas
	public static int numeroVentas = 0;

	// Contadores y acumuladores de ventas por modelo
	public static int cantidadVentas0 = 0;
	public static int unidadesVendidas0 = 0;
	public static double importeAcumulado0 = 0;

	public static int cantidadVentas1 = 0;
	public static int unidadesVendidas1 = 0;
	public static double importeAcumulado1 = 0;

	public static int cantidadVentas2 = 0;
	public static int unidadesVendidas2 = 0;
	public static double importeAcumulado2 = 0;

	public static int cantidadVentas3 = 0;
	public static int unidadesVendidas3 = 0;
	public static double importeAcumulado3 = 0;

	public static int cantidadVentas4 = 0;
	public static int unidadesVendidas4 = 0;
	public static double importeAcumulado4 = 0;

	// Lanza la aplicación
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tienda frame = new Tienda();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Tienda() {
		setTitle("Tienda 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 465);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);

		mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		//___________________________________________________________________________________
		//CREADO_POR_JUAN_CC
		mntmCompararModelos = new JMenuItem("Comparar modelos");
		mntmCompararModelos.addActionListener(this);
		mnMantenimiento.add(mntmCompararModelos);

		mntmRecomendadorPC = new JMenuItem("Recomendador de PC");
		mntmRecomendadorPC.addActionListener(this);
		mnMantenimiento.add(mntmRecomendadorPC);
		//____________________________________________________________________________________

		mntmConsultarComputadora = new JMenuItem("Consultar computadora");
		mntmConsultarComputadora.addActionListener(this);
		mnMantenimiento.add(mntmConsultarComputadora);

		mntmModificarComputadora = new JMenuItem("Modificar computadora");
		mntmModificarComputadora.addActionListener(this);
		mnMantenimiento.add(mntmModificarComputadora);

		mntmListarComputadoras = new JMenuItem("Listar computadoras");
		mntmListarComputadoras.addActionListener(this);
		mnMantenimiento.add(mntmListarComputadoras);

		mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);

		mntmVender = new JMenuItem("Vender");
		mntmVender.addActionListener(this);
		mnVentas.add(mntmVender);

		mntmGenerarReportes = new JMenuItem("Generar reportes");
		mntmGenerarReportes.addActionListener(this);
		mnVentas.add(mntmGenerarReportes);
		//_________________________________________________________________________________________
		//CREADO_POR_JUAN_CC
		mntmHistorialVentas = new JMenuItem("Historial de ventas");
		mntmHistorialVentas.addActionListener(this);
		mnVentas.add(mntmHistorialVentas);
		//__________________________________________________________________________________________

		mnConfiguracion = new JMenu("Configuración");
		menuBar.add(mnConfiguracion);

		mntmConfigurarDescuentos = new JMenuItem("Configurar descuentos");
		mntmConfigurarDescuentos.addActionListener(this);
		mnConfiguracion.add(mntmConfigurarDescuentos);

		mntmConfigurarObsequios = new JMenuItem("Configurar obsequios");
		mntmConfigurarObsequios.addActionListener(this);
		mnConfiguracion.add(mntmConfigurarObsequios);

		mntmConfigurarCuotaDiaria = new JMenuItem("Configurar cuota diaria");
		mntmConfigurarCuotaDiaria.addActionListener(this);
		mnConfiguracion.add(mntmConfigurarCuotaDiaria);

		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		mntmAcercaDeTienda = new JMenuItem("Acerca de Tienda");
		mntmAcercaDeTienda.addActionListener(this);
		mnAyuda.add(mntmAcercaDeTienda);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitulo = new JLabel("Ripley - Computadoras All-in-One");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitulo.setBounds(10, 120, 726, 40);
		contentPane.add(lblTitulo);

		lblSubtitulo = new JLabel("Sistema de venta de computadoras de escritorio");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubtitulo.setBounds(10, 170, 726, 25);
		contentPane.add(lblSubtitulo);

		lblDetalle = new JLabel("Seleccione una opción del menú para comenzar");
		lblDetalle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalle.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblDetalle.setBounds(10, 220, 726, 20);
		contentPane.add(lblDetalle);
	}

	// Direcciona los eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
		if (e.getSource() == mntmConsultarComputadora) {
			actionPerformedMntmConsultarComputadora(e);
		}
		if (e.getSource() == mntmModificarComputadora) {
			actionPerformedMntmModificarComputadora(e);
		}
		if (e.getSource() == mntmListarComputadoras) {
			actionPerformedMntmListarComputadoras(e);
		}
		if (e.getSource() == mntmVender) {
			actionPerformedMntmVender(e);
		}
		if (e.getSource() == mntmGenerarReportes) {
			actionPerformedMntmGenerarReportes(e);
		}
		if (e.getSource() == mntmConfigurarDescuentos) {
			actionPerformedMntmConfigurarDescuentos(e);
		}
		if (e.getSource() == mntmConfigurarObsequios) {
			actionPerformedMntmConfigurarObsequios(e);
		}
		if (e.getSource() == mntmConfigurarCuotaDiaria) {
			actionPerformedMntmConfigurarCuotaDiaria(e);
		}
		if (e.getSource() == mntmAcercaDeTienda) {
			actionPerformedMntmAcercaDeTienda(e);
		}
		//________________________________________________________________________________________________________-
		//CREADO_POR_JUAN_CC
		if (e.getSource() == mntmCompararModelos) {
			actionPerformedMntmCompararModelos(e);
		}
		if (e.getSource() == mntmRecomendadorPC) {
			actionPerformedMntmRecomendadorPC(e);
		}
		if (e.getSource() == mntmHistorialVentas) {
			actionPerformedMntmHistorialVentas(e);
		}
		//_________________________________________________________________________________________________________
	}
	
	//_____________________________________________________________________________________________________________
	//AGREGO_JUAN_CC
	//método estático para ir acumulando cada boleta:
	public static void agregarBoletaHistorial(String boleta) {
		historialVentas += boleta + "\n----------------------------------------\n\n";
	}
    //_______________________________________________________________________________________________________________
	// Archivo > Salir
	protected void actionPerformedMntmSalir(ActionEvent e) {
		System.exit(0);
	}

	// Mantenimiento > Consultar computadora
	protected void actionPerformedMntmConsultarComputadora(ActionEvent e) {
		DialogoConsultarComputadora d = new DialogoConsultarComputadora();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Mantenimiento > Modificar computadora
	protected void actionPerformedMntmModificarComputadora(ActionEvent e) {
		DialogoModificarComputadora d = new DialogoModificarComputadora();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Mantenimiento > Listar computadoras
	protected void actionPerformedMntmListarComputadoras(ActionEvent e) {
		DialogoListarComputadoras d = new DialogoListarComputadoras();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Ventas > Vender
	protected void actionPerformedMntmVender(ActionEvent e) {
		DialogoVender d = new DialogoVender();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Ventas > Generar reportes
	protected void actionPerformedMntmGenerarReportes(ActionEvent e) {
		DialogoGenerarReportes d = new DialogoGenerarReportes();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Configuración > Configurar descuentos
	protected void actionPerformedMntmConfigurarDescuentos(ActionEvent e) {
		DialogoConfigurarDescuentos d = new DialogoConfigurarDescuentos();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Configuración > Configurar obsequios
	protected void actionPerformedMntmConfigurarObsequios(ActionEvent e) {
		DialogoConfigurarObsequios d = new DialogoConfigurarObsequios();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Configuración > Configurar cuota diaria
	protected void actionPerformedMntmConfigurarCuotaDiaria(ActionEvent e) {
		DialogoConfigurarCuotaDiaria d = new DialogoConfigurarCuotaDiaria();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Ayuda > Acerca de Tienda
	protected void actionPerformedMntmAcercaDeTienda(ActionEvent e) {
		DialogoAcercaDeTienda d = new DialogoAcercaDeTienda();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}
	
	//____________________________________________________________________________________
	//AGREGADO_POR_JUAN_CC
	// Mantenimiento > Comparar modelos
	protected void actionPerformedMntmCompararModelos(ActionEvent e) {
		DialogoCompararModelos d = new DialogoCompararModelos();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Mantenimiento > Recomendador de PC
	protected void actionPerformedMntmRecomendadorPC(ActionEvent e) {
		DialogoRecomendadorPC d = new DialogoRecomendadorPC();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}

	// Ventas > Historial de ventas
	protected void actionPerformedMntmHistorialVentas(ActionEvent e) {
		DialogoHistorialVentas d = new DialogoHistorialVentas();
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}
	//_______________________________________________________________________________________

	// ------------------------------------------------------------------
	// Métodos compartidos por todas las cajas de diálogo
	// ------------------------------------------------------------------

	// Carga en un JComboBox los modelos de las cinco computadoras
	public static void cargarModelos(JComboBox<String> cbo) {
		cbo.removeAllItems();
		for (int i = 0; i < 5; i++) {
			cbo.addItem(obtenerModelo(i));
		}
	}

	// Retorna el modelo de la computadora de posición i
	public static String obtenerModelo(int i) {
		switch (i) {
			case 0:
				return modelo0;
			case 1:
				return modelo1;
			case 2:
				return modelo2;
			case 3:
				return modelo3;
			default:
				return modelo4;
		}
	}

	// Retorna el precio de la computadora de posición i
	public static double obtenerPrecio(int i) {
		switch (i) {
			case 0:
				return precio0;
			case 1:
				return precio1;
			case 2:
				return precio2;
			case 3:
				return precio3;
			default:
				return precio4;
		}
	}

	// Retorna el procesador de la computadora de posición i
	public static String obtenerProcesador(int i) {
		switch (i) {
			case 0:
				return procesador0;
			case 1:
				return procesador1;
			case 2:
				return procesador2;
			case 3:
				return procesador3;
			default:
				return procesador4;
		}
	}

	// Retorna la memoria RAM (GB) de la computadora de posición i
	public static int obtenerMemoriaRam(int i) {
		switch (i) {
			case 0:
				return memoriaRam0;
			case 1:
				return memoriaRam1;
			case 2:
				return memoriaRam2;
			case 3:
				return memoriaRam3;
			default:
				return memoriaRam4;
		}
	}

	// Retorna el almacenamiento SSD (GB) de la computadora de posición i
	public static int obtenerAlmacenamiento(int i) {
		switch (i) {
			case 0:
				return almacenamiento0;
			case 1:
				return almacenamiento1;
			case 2:
				return almacenamiento2;
			case 3:
				return almacenamiento3;
			default:
				return almacenamiento4;
		}
	}

	// Retorna el tamaño de pantalla (pulgadas) de la computadora de posición i
	public static double obtenerPantalla(int i) {
		switch (i) {
			case 0:
				return pantalla0;
			case 1:
				return pantalla1;
			case 2:
				return pantalla2;
			case 3:
				return pantalla3;
			default:
				return pantalla4;
		}
	}

	// Guarda los nuevos datos de la computadora de posición i
	public static void guardarComputadora(int i, String modelo, double precio, String procesador,
			int memoriaRam, int almacenamiento, double pantalla) {
		switch (i) {
			case 0:
				modelo0 = modelo;
				precio0 = precio;
				procesador0 = procesador;
				memoriaRam0 = memoriaRam;
				almacenamiento0 = almacenamiento;
				pantalla0 = pantalla;
				break;
			case 1:
				modelo1 = modelo;
				precio1 = precio;
				procesador1 = procesador;
				memoriaRam1 = memoriaRam;
				almacenamiento1 = almacenamiento;
				pantalla1 = pantalla;
				break;
			case 2:
				modelo2 = modelo;
				precio2 = precio;
				procesador2 = procesador;
				memoriaRam2 = memoriaRam;
				almacenamiento2 = almacenamiento;
				pantalla2 = pantalla;
				break;
			case 3:
				modelo3 = modelo;
				precio3 = precio;
				procesador3 = procesador;
				memoriaRam3 = memoriaRam;
				almacenamiento3 = almacenamiento;
				pantalla3 = pantalla;
				break;
			default:
				modelo4 = modelo;
				precio4 = precio;
				procesador4 = procesador;
				memoriaRam4 = memoriaRam;
				almacenamiento4 = almacenamiento;
				pantalla4 = pantalla;
		}
	}

	// Registra una venta en los contadores y acumuladores del modelo i
	public static void registrarVenta(int i, int cantidad, double importePagar) {
		numeroVentas++;
		switch (i) {
			case 0:
				cantidadVentas0++;
				unidadesVendidas0 += cantidad;
				importeAcumulado0 += importePagar;
				break;
			case 1:
				cantidadVentas1++;
				unidadesVendidas1 += cantidad;
				importeAcumulado1 += importePagar;
				break;
			case 2:
				cantidadVentas2++;
				unidadesVendidas2 += cantidad;
				importeAcumulado2 += importePagar;
				break;
			case 3:
				cantidadVentas3++;
				unidadesVendidas3 += cantidad;
				importeAcumulado3 += importePagar;
				break;
			default:
				cantidadVentas4++;
				unidadesVendidas4 += cantidad;
				importeAcumulado4 += importePagar;
		}
	}

	// Retorna la cantidad de ventas realizadas del modelo i
	public static int obtenerCantidadVentas(int i) {
		switch (i) {
			case 0:
				return cantidadVentas0;
			case 1:
				return cantidadVentas1;
			case 2:
				return cantidadVentas2;
			case 3:
				return cantidadVentas3;
			default:
				return cantidadVentas4;
		}
	}

	// Retorna las unidades vendidas del modelo i
	public static int obtenerUnidadesVendidas(int i) {
		switch (i) {
			case 0:
				return unidadesVendidas0;
			case 1:
				return unidadesVendidas1;
			case 2:
				return unidadesVendidas2;
			case 3:
				return unidadesVendidas3;
			default:
				return unidadesVendidas4;
		}
	}

	// Retorna el importe acumulado del modelo i
	public static double obtenerImporteAcumulado(int i) {
		switch (i) {
			case 0:
				return importeAcumulado0;
			case 1:
				return importeAcumulado1;
			case 2:
				return importeAcumulado2;
			case 3:
				return importeAcumulado3;
			default:
				return importeAcumulado4;
		}
	}

	// Retorna el importe total general acumulado (suma de los cinco modelos)
	public static double obtenerImporteTotalGeneral() {
		double total = 0;
		for (int i = 0; i < 5; i++) {
			total += obtenerImporteAcumulado(i);
		}
		return total;
	}

	// Retorna qué porcentaje de la cuota diaria representa un importe
	public static double calcularPorcentajeCuota(double importe) {
		return importe * 100 / cuotaDiaria;
	}
}
