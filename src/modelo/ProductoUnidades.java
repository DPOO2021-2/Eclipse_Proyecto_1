package modelo;

import java.util.HashMap;

public class ProductoUnidades extends Producto
{
	
	
	public ProductoUnidades(String nombre, String codigoBarras, String tipoRefrigerado, double precio_publico_unidad, String precio_publico_unidad_medida) 
	{
		super(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad, precio_publico_unidad_medida);
	}

	
	
	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	public double comprarProducto(double cantidad)
	{
		//aqui se est? revisando que cantidad sea un entero
		
		// si no lo es: coge el entero m?s cercano
		int cantidad_entera = (int) cantidad;
		
		 HashMap<String, Lote> lotes = getLotes();
		 for (Lote lote: lotes.values())
		 {
			 //aqui se est?n restando de los lotes en orden que lotes.values
			 // los organice
			 //lo cu?l puede que no sea por fecha
			 
			 
			 //si es necesario que se resten primero de la fecha mas antigua
			 //hay que revisar como se haria
			 //podria ser ordenando aqui por fecha o pidiendole al mapa que 
			 //se mantenga organizado aunque no creo que eso se pueda ya que es hashmap
			 cantidad = lote.restarCantidad(cantidad_entera);
		 }
		 
		 double costo = calcularPrecio(cantidad_entera);
		 setGananciaTotal(getGananciaTotal() + costo);
		 
		 actualizarActividad();
		 
		 return costo;
		
	}
	

	public void entregarProducto(double cantidad)
	{
		
		// si no lo es: coge el entero m?s cercano
		int cantidad_entera = (int) cantidad;
		
		 HashMap<String, Lote> lotes = getLotes();
		 for (Lote lote: lotes.values())
		 {


			 
			 
			 
			 
			 
			 
			 cantidad = lote.restarCantidad(cantidad);
			 
		 }
		
//		 double costo = calcularPrecio(cantidad);
//		 setGananciaTotal(getGananciaTotal() + costo);
		 
		 actualizarActividad();
		 
//		 return costo;
	}




	@Override
	public boolean sePuedeComprar(double cantidad) 
	{
		int cantidad_entera = (int) cantidad;
		
		return (cantidadTotal() > cantidad_entera) && (cantidad_entera==cantidad);
	}
}
