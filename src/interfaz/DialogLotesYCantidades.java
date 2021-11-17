package interfaz;



import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JList;

import javax.swing.JScrollPane;


public class DialogLotesYCantidades extends JDialog 
{


	public DialogLotesYCantidades(Map<String, Double> lotes_y_cantidades) 
	{
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
//		setSize(250, 400);
	
		
		
	
		
		ArrayList<String> lycLista= new ArrayList<String>();
		//TODO
		//llenar lyc:
		for (String codigo_lote:lotes_y_cantidades.keySet())
		{
			String loteYcantidad = "Lote "+codigo_lote +": "+ lotes_y_cantidades.get(codigo_lote);
			lycLista.add(loteYcantidad);
		}
	
		
		JList lista = new JList(lycLista.toArray());
		
		
		lista.setFont(new Font("Georgia", Font.ITALIC, 15));
		lista.setBackground(Color.ORANGE);
		
		
		JScrollPane scroll = new JScrollPane(lista);
		
		
		scroll.getVerticalScrollBar().setOpaque(false);
		
		add(scroll);
		pack();
		

	}



}