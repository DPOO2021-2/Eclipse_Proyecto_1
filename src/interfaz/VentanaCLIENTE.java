package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class VentanaCLIENTE extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaCLIENTE dialog = new VentanaCLIENTE();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaCLIENTE() {
		setBackground(Color.WHITE);
		setTitle("Registrar un Cliente");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(7, 2, 0, 0));
		{
			JTextPane txtpnIngreseLaCedula = new JTextPane();
			txtpnIngreseLaCedula.setBackground(Color.LIGHT_GRAY);
			txtpnIngreseLaCedula.setEditable(false);
			txtpnIngreseLaCedula.setText("Ingrese la cedula:");
			getContentPane().add(txtpnIngreseLaCedula);
		}
		{
			textField = new JTextField();
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			JTextPane txtpnIngreseLaEdad = new JTextPane();
			txtpnIngreseLaEdad.setBackground(Color.GRAY);
			txtpnIngreseLaEdad.setText("Ingrese la edad:");
			txtpnIngreseLaEdad.setEditable(false);
			getContentPane().add(txtpnIngreseLaEdad);
		}
		{
			textField_1 = new JTextField();
			getContentPane().add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JTextPane txtpnIngreseElEstado = new JTextPane();
			txtpnIngreseElEstado.setBackground(Color.LIGHT_GRAY);
			txtpnIngreseElEstado.setEditable(false);
			txtpnIngreseElEstado.setText("Ingrese el estado civil:");
			getContentPane().add(txtpnIngreseElEstado);
		}
		{
			textField_2 = new JTextField();
			getContentPane().add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JTextPane txtpnIngreseElNombre = new JTextPane();
			txtpnIngreseElNombre.setBackground(Color.GRAY);
			txtpnIngreseElNombre.setEditable(false);
			txtpnIngreseElNombre.setText("Ingrese el nombre:");
			getContentPane().add(txtpnIngreseElNombre);
		}
		{
			textField_3 = new JTextField();
			getContentPane().add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JTextPane txtpnIngreseElSexo = new JTextPane();
			txtpnIngreseElSexo.setBackground(Color.LIGHT_GRAY);
			txtpnIngreseElSexo.setText("Ingrese el sexo:");
			txtpnIngreseElSexo.setEditable(false);
			getContentPane().add(txtpnIngreseElSexo);
		}
		{
			textField_5 = new JTextField();
			getContentPane().add(textField_5);
			textField_5.setColumns(10);
		}
		{
			JTextPane txtpnIngreseLaSituacin = new JTextPane();
			txtpnIngreseLaSituacin.setBackground(Color.GRAY);
			txtpnIngreseLaSituacin.setEditable(false);
			txtpnIngreseLaSituacin.setText("Ingrese la situaci\u00F3n laboral:");
			getContentPane().add(txtpnIngreseLaSituacin);
		}
		{
			textField_6 = new JTextField();
			getContentPane().add(textField_6);
			textField_6.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			getContentPane().add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.GREEN);
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
