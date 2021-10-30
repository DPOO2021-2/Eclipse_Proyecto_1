package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class VentanaINV extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaINV frame = new VentanaINV();
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
	public VentanaINV() {
		setTitle("Sistema INVENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 2, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("New button");
		contentPane.add(btnNewButton_2);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("New button");
		contentPane.add(btnNewButton_3);
		
		textField_3 = new JTextField();
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("New button");
		contentPane.add(btnNewButton_4);
		
		textField_4 = new JTextField();
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}

}
