package modelo;

import java.util.HashMap;

public class ProductoUnidades extends Producto
{
	
	
	public ProductoUnidades(String nombre, String codigoBarras, String categoria, String subcategoria1,
			String subcategoria2, String tipoRefrigerado, int precioActual, int ganaciaTotal) 
	{
		super(nombre, codigoBarras, categoria, subcategoria1, subcategoria2, tipoRefrigerado, precioActual, ganaciaTotal);
	}

	public double calcularPrecio(double cantidad)
	{
		//aqui hay que revisar que cantidad se un entero
		return getPrecioActual()*cantidad;
	}
	
	public void comprarProducto(double cantidad)
	{
		//aqui hay que revisar que cantidad se un entero
		
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
			 cantidad = lote.restarCantidad(cantidad);
			 
		 }
	}
}
