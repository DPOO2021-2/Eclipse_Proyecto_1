package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import procesamiento.Supermercado;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

public class VentanaPOS extends JFrame implements ActionListener
{
	private JPanel contentPane;
	
	private Supermercado supermercadoYumbo;
	
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPOS frame = new VentanaPOS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaPOS() throws IOException, ParseException 
	{
//		vCliente = new VentanaCLIENTE();
//		vCompra = new VentanaCOMPRA();
		
		supermercadoYumbo = new Supermercado("Yumbo");
		
		setTitle("Sistema POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 150);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Registrar Cliente");
		btnNewButton.setActionCommand("registrar_cliente");
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar Compra");
		btnNewButton_1.setActionCommand("registrar_compra");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_1);
		
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
	public VentanaPOS esto()
	{
		return this;
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if("registrar_cliente".equals(comando))
		{
			
			
			VentanaCLIENTE vCliente = new VentanaCLIENTE(supermercadoYumbo);
			vCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			vCliente.setVisible(true);
		}
		else if("registrar_compra".equals(comando))
		{
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del cliente:");
			String cedula = JOptionPane.showInputDialog(null, "Introduce la cedula del cliente:");
//			System.out.println(nombre+".");
//			System.out.println(cedula);
			if(nombre==null || cedula==null )
			{
				JOptionPane.showMessageDialog(this, "¡Debes ingresar una cedula y nombre para registrar una compra!",
						"Error", JOptionPane.ERROR_MESSAGE);
				
			}
			else if ("".equals(nombre) || "".equals(cedula))
			{
				JOptionPane.showMessageDialog(this, "¡Debes ingresar una cedula y nombre para registrar una compra!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				
				VentanaCOMPRA vCompra = new VentanaCOMPRA(supermercadoYumbo, nombre, cedula);
				vCompra.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				vCompra.setVisible(true);
			}
			
			
		}
	}
}
