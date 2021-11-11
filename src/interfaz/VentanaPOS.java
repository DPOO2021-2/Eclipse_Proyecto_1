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
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import java.awt.Color;

public class VentanaPOS extends JFrame implements ActionListener
{
	private JPanel contentPane;
	
	private VentanaCLIENTE vCliente;
	private VentanaCOMPRA vCompra;

	

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
		vCliente = new VentanaCLIENTE();
		vCompra = new VentanaCOMPRA();
		
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
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if("registrar_cliente".equals(comando))
		{
			vCliente.main(null);
		}
		else if("registrar_compra".equals(comando))
		{
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del cliente:");
			String cedula = JOptionPane.showInputDialog(null, "Introduce la cedula del cliente:");
			vCompra.main(null);
		}
	}
}
