package procesamiento;

import modelo.Compra;
import modelo.Producto;

import java.util.ArrayList;
import java.util.Map;



public class RegistroCompras {
	
	
	private ArrayList<Compra> comprasRegistradas;
	
	
	public RegistroCompras()
	{
		this.comprasRegistradas = new ArrayList<Compra>();
	}

	
	
	
	



	public void guadarCompra(Compra compra) 
	{
		comprasRegistradas.add(compra);
	}
	
	public void crearArchivos() {
		
	}
	
	public void leerInfoArchivos() {
		
	}
	
	public void actualizarArchivos() {
		
	}
	
}
