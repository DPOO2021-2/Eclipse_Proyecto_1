package modelo;


import java.util.HashMap;

public class ProductoKilos extends Producto
{
	
	public ProductoKilos(String nombre, String codigoBarras, String tipoRefrigerado, double precioActual) 
	{
		super(nombre, codigoBarras, tipoRefrigerado, precioActual);
	}
	

	
	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	public double comprarProducto(double cantidad)
	{
		
		 HashMap<String, Lote> lotes = getLotes();
		 for (Lote lote: lotes.values())
		 {
			 //aqui se est�n restando de los lotes en orden que lotes.values
			 // los organice
			 //lo cu�l puede que no sea por fecha
			 
			 
			 //si es necesario que se resten primero de la fecha mas antigua
			 //hay que revisar como se haria
			 //podria ser ordenando aqui por fecha o pidiendole al mapa que 
			 //se mantenga organizado aunque no creo que eso se pueda ya que es hashmap
			 cantidad = lote.restarCantidad(cantidad);
			 
		 }
		
		 double costo = calcularPrecio(cantidad);
		 setGananciaTotal(getGananciaTotal() + costo);
		 return costo;
	}
	
	
	
	
}

