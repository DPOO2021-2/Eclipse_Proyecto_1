package procesamiento;

import modelo.Compra;
import modelo.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class RegistroCompras {
	
	
	private HashMap<String, ArrayList<Compra>> comprasRegistradas;
	
	
	public RegistroCompras()
	{
		
		this.comprasRegistradas = new HashMap<String, ArrayList<Compra>>();
	}


	public void guadarCompra(String cedula, Compra compra) 
	{
		if(comprasRegistradas.containsKey(cedula))
		{
			comprasRegistradas.get(cedula).add(compra);
		}
		else
		{
			ArrayList<Compra> listaCompras = new ArrayList<Compra>();
			listaCompras.add(compra);
			comprasRegistradas.put(cedula, listaCompras);
		}
	}
	

	
	public HashMap<String, ArrayList<Compra>> getCompras()
	{
		return this.comprasRegistradas;
	}


	public boolean vacio() 
	{
		return this.comprasRegistradas.isEmpty();
	}
	
}
