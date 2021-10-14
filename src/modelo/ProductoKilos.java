package modelo;


import java.util.HashMap;

public class ProductoKilos extends Producto
{
	
	public ProductoKilos(String nombre, String codigoBarras, String categoria, String subcategoria1,
			String subcategoria2, String tipoRefrigerado, int precioActual) 
	{
		super(nombre, codigoBarras, categoria, subcategoria1, subcategoria2, tipoRefrigerado, precioActual);
	}
	

	//antes de llamar este metodo asegurese de que 
	//cantidad<cantidadTotal()
	public double calcularPrecio(double cantidad)
	{
		return getPrecioActual()*cantidad;
	}
	
	//antes de llamar este metodo asegurese de que 
	//cantidad<cantidadTotal()
	public void comprarProducto(double cantidad)
	{
		
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

