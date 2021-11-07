package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaPOS extends JFrame 
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

	/**
	 * Create the frame.
	 */
	public VentanaPOS() 
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
		btnNewButton.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar Compra");
		btnNewButton_1.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_1);
	}

}
