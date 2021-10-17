package modelo;

import java.util.HashMap;

public class ProductoUnidades extends Producto
{
	
	
	public ProductoUnidades(String nombre, String codigoBarras, String tipoRefrigerado, double precioActual) 
	{
		super(nombre, codigoBarras, tipoRefrigerado, precioActual);
	}

	
	
	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	public double comprarProducto(double cantidad)
	{
		//aqui se está revisando que cantidad sea un entero
		
		// si no lo es: coge el entero más cercano
		int cantidad_entera = (int) cantidad;
		
		 HashMap<String, Lote> lotes = getLotes();
		 for (Lote lote: lotes.values())
		 {
			 //aqui se están restando de los lotes en orden que lotes.values
			 // los organice
			 //lo cuál puede que no sea por fecha
			 
			 
			 //si es necesario que se resten primero de la fecha mas antigua
			 //hay que revisar como se haria
			 //podria ser ordenando aqui por fecha o pidiendole al mapa que 
			 //se mantenga organizado aunque no creo que eso se pueda ya que es hashmap
			 cantidad = lote.restarCantidad(cantidad_entera);
		 }
		 
		 double costo = calcularPrecio(cantidad_entera);
		 setGananciaTotal(getGananciaTotal() + costo);
		 return costo;
		 
		 
			 
		 
	}
}
