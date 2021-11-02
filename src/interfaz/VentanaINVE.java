package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class VentanaINVE extends JFrame {
	private JPanel contentPane;
	private JTextField txtEscribaElNombre;
	private JTextField txtEscribaElNombre_1;
	private JTextField txtEscribaElNombre_2;
	private JTextField txtEscribaElNombre_3;
	private JTextField txtEscribaElNombre_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public VentanaINVE() {
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
		btnNewButton.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton);
		
		txtEscribaElNombre = new JTextField();
		txtEscribaElNombre.setBackground(Color.WHITE);
		txtEscribaElNombre.setText("Escriba el nombre del archivo de los lotes");
		contentPane.add(txtEscribaElNombre);
		txtEscribaElNombre.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Eliminar Lote");
		btnNewButton_1.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_1);
		
		txtEscribaElNombre_1 = new JTextField();
		txtEscribaElNombre_1.setText("Escriba el nombre del lote");
		contentPane.add(txtEscribaElNombre_1);
		txtEscribaElNombre_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Calcular Rendimiento");
		btnNewButton_2.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_2);
		
		txtEscribaElNombre_2 = new JTextField();
		txtEscribaElNombre_2.setText("Escriba el nombre del producto");
		contentPane.add(txtEscribaElNombre_2);
		txtEscribaElNombre_2.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Lotes y Cantidades");
		btnNewButton_3.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_3);
		
		txtEscribaElNombre_3 = new JTextField();
		txtEscribaElNombre_3.setText("Escriba el nombre del producto");
		contentPane.add(txtEscribaElNombre_3);
		txtEscribaElNombre_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Agregar imagen a un producto");
		btnNewButton_4.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton_4);
		
		txtEscribaElNombre_4 = new JTextField();
		txtEscribaElNombre_4.setText("Escriba el nombre del producto");
		contentPane.add(txtEscribaElNombre_4);
		txtEscribaElNombre_4.setColumns(10);
	}

}
