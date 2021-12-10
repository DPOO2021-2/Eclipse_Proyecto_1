package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ClienteRegistrado;
import modelo.Producto;
import procesamiento.Supermercado;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaCOMPRA extends JDialog implements ActionListener 
{
	private JTextField textFieldCantidad;
	private JComboBox<String> comboBoxCategoria;
	private JComboBox<String> comboBoxProducto;
	private JLabel lblNewLabel;
	private String nombre;
	private String cedula;
//	private String categoria;
//	private String producto;
	private Supermercado supermercado;
	
	private Map<String, Double> productosyCantidades;


	

	/**
	 * Create the dialog.
	 */
	public VentanaCOMPRA(Supermercado supermercado, String nombre, String cedula) 
	{
		this.supermercado = supermercado;
		this.nombre = nombre;
		this.cedula = cedula;
		this.productosyCantidades = new HashMap<String, Double>();
//		this.categoria = null;
//		this.producto = null;
		
		
		getContentPane().setBackground(Color.GRAY);
		setTitle("Registrar Compra");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton botonGrafica = new JButton("Revisar Actividad del Cliente");
				botonGrafica.setBackground(Color.ORANGE);
				botonGrafica.setActionCommand("grafica");
				buttonPane.add(botonGrafica);
				botonGrafica.addActionListener(this);
				getRootPane().setDefaultButton(botonGrafica);
			
				JButton confirmarButton = new JButton("Guardar e Imprimir Compra");
				confirmarButton.setBackground(Color.ORANGE);
				confirmarButton.setActionCommand("confirmar");
				confirmarButton.addActionListener(this);
				buttonPane.add(confirmarButton);
			
		
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(2, 3, 0, 0));
			
				JTextPane txtpnSeleccioneLaCategoria = new JTextPane();
				txtpnSeleccioneLaCategoria.setEditable(false);
				txtpnSeleccioneLaCategoria.setBackground(Color.GRAY);
				txtpnSeleccioneLaCategoria.setText("Seleccione la Categoria");
				panel.add(txtpnSeleccioneLaCategoria);
			
				JTextPane txtpnSeleccioneElProducto = new JTextPane();
				txtpnSeleccioneElProducto.setEditable(false);
				txtpnSeleccioneElProducto.setBackground(Color.GRAY);
				txtpnSeleccioneElProducto.setText("Seleccione el Producto");
				panel.add(txtpnSeleccioneElProducto);
			
				JTextPane txtpnSeleccioneLaCantidad = new JTextPane();
				txtpnSeleccioneLaCantidad.setEditable(false);
				txtpnSeleccioneLaCantidad.setBackground(Color.GRAY);
				txtpnSeleccioneLaCantidad.setText("Escriba la cantidad");
				panel.add(txtpnSeleccioneLaCantidad);
			
//---------------------------------------------------------------------------------------------------------------			
				comboBoxCategoria = new JComboBox<String>();
				
				// Accion a realizar cuando el JComboBox cambia de item seleccionado.
				comboBoxCategoria.addActionListener(new ActionListener() 
				{
							@Override
							public void actionPerformed(ActionEvent e) 
							{
								String categoriaSelecc = comboBoxCategoria.getSelectedItem().toString();
								Map<String, Producto> productos = supermercado.getInventario().getProductos();
								
								
//	esto no se usara	setCategoria(comboBoxCategoria.getSelectedItem().toString());
								 //filtrar los items del combobox 
								/*
								for(String key: productos.keySet())
								{
									if(categoriaSelecc == key.getCategorias())
									{
										
									}
								}*/
							}

				});
				panel.add(comboBoxCategoria);
				
//---------------------------------------------------------------------------------------------------------------		
				
				comboBoxProducto = new JComboBox<String>();
				//comboBoxProducto.addItem("100000");
				Set<String> productos = supermercado.getInventario().getProductos().keySet();
				for (String codigoBarras: productos)
				{
					comboBoxProducto.addItem(codigoBarras);
				}
				comboBoxProducto.addActionListener(this);
				comboBoxProducto.setActionCommand("CambiarImagen");
				panel.add(comboBoxProducto);
//---------------------------------------------------------------------------------------------------------------				
				
				
				textFieldCantidad = new JTextField();
				textFieldCantidad.setBackground(Color.WHITE);
				panel.add(textFieldCantidad);
				textFieldCantidad.setColumns(10);
			
		
		
			JButton btnNewButton = new JButton("Agregar Producto");
			btnNewButton.setActionCommand("agregarProducto");
			btnNewButton.addActionListener(this);
			btnNewButton.setBackground(Color.ORANGE);
			getContentPane().add(btnNewButton, BorderLayout.WEST);
		
			lblNewLabel = new JLabel();
			ImageIcon icono = new ImageIcon("ImagenesProductos/NOIMAGE.png");
			lblNewLabel.setIcon(icono);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
	}

	
	/*
	private void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	*/
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String comando = e.getActionCommand();
		
		
		if ("agregarProducto".equals(comando))
		{
			String codigoBarras = comboBoxProducto.getSelectedItem().toString();
			String cantidadStr = textFieldCantidad.getText();
			
			try
			{
				Double cantidad = Double.parseDouble(cantidadStr);
				if(cantidad > 0)
				{
					productosyCantidades.put(codigoBarras, cantidad);
					JOptionPane.showMessageDialog(this, "Se ha agregado "+cantidadStr + " del producto "+codigoBarras + " a la compra",
							"Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Cantidades estrictamente mayores a cero por favor. No somos un sitio de compraventa."
							+ " Gracias.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}	
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(this, "¡Debes escoger un producto e ingresar una cantidad para agregarlos a la compra!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(NullPointerException npe)
			{
				JOptionPane.showMessageDialog(this, "¡Debes escoger un producto e ingresar cantidad para agregarlos a la compra!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
				
				
		}
		
		else if("confirmar".equals(comando))
		{
			
			if(supermercado.clienteRegistrado(cedula))
			{
				
				ClienteRegistrado cliente = supermercado.getClienteRegistrado(cedula);
				String puntosAUSAR = JOptionPane.showInputDialog(null, "Eres un clientre registardo tienes x puntos");
			}
			
			
			else
			{
			
			
			String factura = supermercado.registrarCompra(productosyCantidades, nombre, cedula);
			
			
			
			if("no".equals(factura))
			{
				JOptionPane.showMessageDialog(this, "Esta compra no se puede realizar. Puede ser porque la cantidad"
						+ " requerida de algún producto no está disponible o no es valida.",
						"Error", JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Tu factura es: "+factura,
						"Tarea Cumplida", JOptionPane.INFORMATION_MESSAGE);
			}
			
			}
			dispose();
			
		}
		
		else if("grafica".equals(comando))
		{
			VentanaGRAFICA vGrafica = new VentanaGRAFICA(nombre, cedula, supermercado);
			vGrafica.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			vGrafica.setVisible(true);
		}
		else if("CambiarImagen".equals(comando))
		{
				String codigoBarras = comboBoxProducto.getSelectedItem().toString();
			    String nombreImagen = supermercado.getImagenProducto(codigoBarras);
			//	String nombreImagen = "libro";
				if (nombreImagen != null)
				{
					
					ImageIcon iconoProducto = new ImageIcon("ImagenesProductos/"+nombreImagen+".png");
					
					lblNewLabel.setIcon(iconoProducto);
				//	lblNewLabel.setText("");
				}
				else
				{
					ImageIcon icono = new ImageIcon("ImagenesProductos/NOIMAGE.png");
					lblNewLabel.setIcon(icono);
				}
				
		}
		
	}
}


