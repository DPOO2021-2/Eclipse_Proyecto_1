package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaINVE() throws IOException, ParseException 
	{
		//superM = new Supermercado("");
		
		vImagen = new VentanaIMAGEN();
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
		btnNewButton_2.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_2);
		
		txtEscribaElNombre_2 = new JTextField();
		txtEscribaElNombre_2.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_2);
		txtEscribaElNombre_2.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Lotes y Cantidades");
		btnNewButton_3.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_3);
		
		txtEscribaElNombre_3 = new JTextField();
		txtEscribaElNombre_3.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_3);
		txtEscribaElNombre_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Agregar imagen a un producto");
		btnNewButton_4.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_4);
		
		txtEscribaElNombre_4 = new JTextField();
		txtEscribaElNombre_4.setText("Escriba el codigo del producto");
		contentPane.add(txtEscribaElNombre_4);
		txtEscribaElNombre_4.setColumns(10);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if("registrar_archivo".equals(comando))
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
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException | IOException | ParseException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			
		
		}
		else if("eliminar_lote".equals(comando))
		{
			JOptionPane.showMessageDialog(this, "¡Se ha eliminado el lote!","Lotes",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
