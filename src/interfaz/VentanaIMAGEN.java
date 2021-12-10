package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Color;

public class VentanaIMAGEN extends JDialog implements ActionListener {
	private JTextField txtEscribaElNombre;

	private VentanaINVE parent;

	/**
	 * Create the dialog.
	 */
	public VentanaIMAGEN(VentanaINVE parent) {
		this.parent = parent;

		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Selecci\u00F3n de imagen");
		getContentPane().setBackground(Color.GRAY);
		setBounds(100, 100, 380, 130);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
			txtEscribaElNombre = new JTextField();
			txtEscribaElNombre.setBackground(Color.WHITE);
			txtEscribaElNombre.setText("Escriba aqui el nombre de la imagen");
			txtEscribaElNombre.setColumns(10);
			getContentPane().add(txtEscribaElNombre, BorderLayout.NORTH);
		
			JTextPane txtpnRecuerdeQueEsta = new JTextPane();
			txtpnRecuerdeQueEsta.setBackground(Color.LIGHT_GRAY);
			txtpnRecuerdeQueEsta.setEditable(false);
			txtpnRecuerdeQueEsta.setText("Recuerde que esta debe estar en la carpeta ImagenesProductos");
			getContentPane().add(txtpnRecuerdeQueEsta, BorderLayout.CENTER);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(127, 255, 0));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.RED);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		if("Cancel".equals(command))
		{
			dispose();
		}
		else if ("OK".equals(command))
		{
			parent.ejecutarSetImagen(txtEscribaElNombre.getText());
			dispose();
		}
		
		
		
	}

}
