package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import procesamiento.CalculadoraFinanzas;
import procesamiento.Supermercado;

import java.awt.Color;
import java.awt.Font;

//import procesamiento.Supermercado;

public class VentanaINVE extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtEscribaElNombre;
	private JTextField txtEscribaElNombre_1;
	private JTextField txtEscribaElNombre_2;
	private JTextField txtEscribaElNombre_3;
	private JTextField txtEscribaElNombre_4;
	
	
	//private Supermercado superM;
	private VentanaIMAGEN vImagen;
	private Supermercado supermercadoYumbo;
	private CalculadoraFinanzas calcFinzs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaINVE frame = new VentanaINVE();
					frame.setVisible(true);					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaINVE() throws IOException, ParseException 
	{
		//superM = new Supermercado("");
		
		vImagen = new VentanaIMAGEN(this);
		vImagen.setVisible(false);
		this.supermercadoYumbo = new Supermercado("Yumbo");
		this.calcFinzs = new CalculadoraFinanzas(supermercadoYumbo);
		
		
		setBackground(new Color(255, 255, 255));
		setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		setTitle("Sistema INVENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Registrar Archivos Lotes");
		btnNewButton.setActionCommand("registrar_archivo");
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton);
		
		txtEscribaElNombre = new JTextField();
		txtEscribaElNombre.setBackground(Color.WHITE);
		txtEscribaElNombre.setText("Escriba el nombre del archivo de los lotes");
		txtEscribaElNombre.setActionCommand("registrar_archivoR");
		txtEscribaElNombre.addActionListener(this);
		contentPane.add(txtEscribaElNombre);
		txtEscribaElNombre.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Eliminar Lote");
		btnNewButton_1.setActionCommand("eliminar_lote");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_1);
		
		txtEscribaElNombre_1 = new JTextField();
		txtEscribaElNombre_1.setText("Escriba el codigo del producto-lote ");
		contentPane.add(txtEscribaElNombre_1);
		txtEscribaElNombre_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Calcular Rendimiento");
		btnNewButton_2.setActionCommand("calc_rend");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_2);
		
		txtEscribaElNombre_2 = new JTextField();
		txtEscribaElNombre_2.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_2);
		txtEscribaElNombre_2.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Lotes y Cantidades");
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.setActionCommand("lotesCantidades");
		btnNewButton_3.addActionListener(this);
		contentPane.add(btnNewButton_3);
		
		txtEscribaElNombre_3 = new JTextField();
		txtEscribaElNombre_3.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_3);
		txtEscribaElNombre_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Establecer imagen a un producto");
		btnNewButton_4.setBackground(Color.ORANGE);
		btnNewButton_4.setActionCommand("setImagen");
		btnNewButton_4.addActionListener(this);
		contentPane.add(btnNewButton_4);
		
		txtEscribaElNombre_4 = new JTextField();
		txtEscribaElNombre_4.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_4);
		txtEscribaElNombre_4.setColumns(10);
		
		
		
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try 
				{
				//	salvarTop10();
					supermercadoYumbo.actualizarArchivos("puntos");
					supermercadoYumbo.actualizarArchivos("inventario_productos");
					supermercadoYumbo.actualizarArchivos("inventario_lotes");
					supermercadoYumbo.actualizarArchivos("compras");
					JOptionPane.showMessageDialog(esto(), "Se guardaron correctamente los archivos",
							"Hasta Pronto", JOptionPane.INFORMATION_MESSAGE);
					
				} 
				 catch (IOException e1) 
				{
					 JOptionPane.showMessageDialog(esto(), "No se pudieron guardar los archivos, asegurate de no estar usandolos en otra parte.",
								"Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}
	public VentanaINVE esto()
	{
		return this;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if("registrar_archivo".equals(comando))
		{
			ejecutarRegistrarArchivo();
		
		}
		else if("eliminar_lote".equals(comando))
		{
			ejecutarEliminarLote();
			
		}
		else if("calc_rend".equals(comando))
		{
			ejecutarCalcularRend();
			
		}
		else if("lotesCantidades".equals(comando))
		{
			ejecutarLotesyCant();
		}
		else if("setImagen".equals(comando))
		{
			vImagen.setVisible(true);
			

		}
	}

	public void ejecutarSetImagen(String nombreImagen) 
	{
		String codigoproducto = txtEscribaElNombre_4.getText();
		
		
		if(supermercadoYumbo.setImagenProducto(codigoproducto, nombreImagen))
		{
			JOptionPane.showMessageDialog(this, "Imagen establecida para el producto: "+ codigoproducto,
					"Tarea Cumplida", JOptionPane.INFORMATION_MESSAGE, null);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "No se ha encontrado el producto: " + codigoproducto,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ejecutarLotesyCant() 
	{
		String codigoproducto = txtEscribaElNombre_3.getText();
		try
		{
			Map<String, Double> lyc = supermercadoYumbo.lotesYcantidades(codigoproducto);
			
			
			//sacar una nueva ventana que muestre el top10
			
			DialogLotesYCantidades ventana_lyc = new DialogLotesYCantidades(lyc, codigoproducto);
			ventana_lyc.setVisible(true);
		
		}
		catch(NullPointerException ne)
		{
			JOptionPane.showMessageDialog(this, "No se ha encontrado el producto: " + codigoproducto,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ejecutarCalcularRend() 
	{
		String codigoproducto = txtEscribaElNombre_2.getText();
		try
		{
		Double rend = calcFinzs.calcularRendimiento(codigoproducto);
		String rendStr = rend.toString();
		JOptionPane.showMessageDialog(this, "Las ganancias netas del producto ||"+codigoproducto+"|| son: "+rendStr,"Finanzas",
				JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException ne)
		{
			JOptionPane.showMessageDialog(this, "No se ha encontrado el producto: " + codigoproducto,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ejecutarEliminarLote() 
	{

		String codigoproducto_lote = txtEscribaElNombre_1.getText();
		String[] partes = codigoproducto_lote.split("-");
		if (partes.length==2)
		{
			String codigoBarras = partes[0];
			String codigoLote = partes[1];
			if(supermercadoYumbo.eliminarLoteVencido(codigoBarras, codigoLote))
			{
				JOptionPane.showMessageDialog(this, "¡Se ha eliminado el lote!","Lotes",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "No se ha eliminado el lote, puede ser que no exista o no está vencido.","Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "La notación requerida es CODIGOPRODUCTO-CODIGOLOTE","Alerta",
					JOptionPane.WHEN_IN_FOCUSED_WINDOW);
		}
		
		
		
	}

	private void ejecutarRegistrarArchivo() 
	{
		String nombre_archivo = txtEscribaElNombre.getText();
		
		
		try {
			if(supermercadoYumbo.registrarLotes(nombre_archivo)) 
			{
				JOptionPane.showMessageDialog(this, "¡Se ha guardado el archivo!","Archivos",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "No se ha encontrado el archivo.","Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (HeadlessException | IOException | ParseException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
