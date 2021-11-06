package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;

public class VentanaIMAGEN extends JDialog {
	private JTextField txtEscribaElNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaIMAGEN dialog = new VentanaIMAGEN();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaIMAGEN() {
		setTitle("Selecci\u00F3n de imagen");
		getContentPane().setBackground(Color.GRAY);
		setBounds(100, 100, 380, 130);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			txtEscribaElNombre = new JTextField();
			txtEscribaElNombre.setBackground(Color.WHITE);
			txtEscribaElNombre.setText("Escriba aqui el nombre de la imangen");
			txtEscribaElNombre.setColumns(10);
			getContentPane().add(txtEscribaElNombre, BorderLayout.NORTH);
		}
		{
			JTextPane txtpnRecuerdeQueEsta = new JTextPane();
			txtpnRecuerdeQueEsta.setBackground(Color.LIGHT_GRAY);
			txtpnRecuerdeQueEsta.setEditable(false);
			txtpnRecuerdeQueEsta.setText("Recuerde que esta debe estar en la carpeta datos/imagenes");
			getContentPane().add(txtpnRecuerdeQueEsta, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(127, 255, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.RED);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
