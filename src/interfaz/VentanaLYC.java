package interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Collection;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;





public class VentanaLYC extends JDialog
{
	private Map<String, Double> lyc;
	
	private String producto;
	
	public VentanaLYC( Map<String, Double> lyc)
	{
		this.lyc = lyc;
		
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Lotes y cantidades del produtco: "+producto);
//		getContentPane().setBackground(Color.GRAY);
//		setBounds(100, 100, 380, 130);
//		getContentPane().setLayout(new BorderLayout(0, 0));
		
	}
	
	
}
