package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaCOMPRA extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaCOMPRA dialog = new VentanaCOMPRA();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaCOMPRA() {
		getContentPane().setBackground(Color.GRAY);
		setTitle("Registrar Compra");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Revisar Actividad del Cliente");
				okButton.setBackground(Color.ORANGE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Guardar e Imprimir Compra");
				cancelButton.setBackground(Color.ORANGE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(2, 3, 0, 0));
			{
				JTextPane txtpnSeleccioneLaCategoria = new JTextPane();
				txtpnSeleccioneLaCategoria.setEditable(false);
				txtpnSeleccioneLaCategoria.setBackground(Color.GRAY);
				txtpnSeleccioneLaCategoria.setText("Seleccione la Categoria");
				panel.add(txtpnSeleccioneLaCategoria);
			}
			{
				JTextPane txtpnSeleccioneElProducto = new JTextPane();
				txtpnSeleccioneElProducto.setEditable(false);
				txtpnSeleccioneElProducto.setBackground(Color.GRAY);
				txtpnSeleccioneElProducto.setText("Seleccione el Producto");
				panel.add(txtpnSeleccioneElProducto);
			}
			{
				JTextPane txtpnSeleccioneLaCantidad = new JTextPane();
				txtpnSeleccioneLaCantidad.setEditable(false);
				txtpnSeleccioneLaCantidad.setBackground(Color.GRAY);
				txtpnSeleccioneLaCantidad.setText("Escriba la cantidad");
				panel.add(txtpnSeleccioneLaCantidad);
			}
			{
				JComboBox comboBox = new JComboBox();
				panel.add(comboBox);
			}
			{
				JComboBox comboBox = new JComboBox();
				panel.add(comboBox);
			}
			{
				textField = new JTextField();
				textField.setBackground(Color.WHITE);
				panel.add(textField);
				textField.setColumns(10);
			}
		}
		{
			JButton btnNewButton = new JButton("Agregar Producto");
			btnNewButton.setBackground(Color.ORANGE);
			getContentPane().add(btnNewButton, BorderLayout.WEST);
		}
		{
			JLabel lblNewLabel = new JLabel("Imagen");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		}
	}

}
