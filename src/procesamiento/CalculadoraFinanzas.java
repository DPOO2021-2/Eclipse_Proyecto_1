package procesamiento;

import modelo.Producto;

public class CalculadoraFinanzas 
{
	private Supermercado supermercado;
	
	public CalculadoraFinanzas(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}
	
	public double calcularRendimiento(String codigoBarras) 
	{
		Inventario inventario = getSupermercado().getInventario();
				
				
		double costoProducto = inventario.getCostoProducto(codigoBarras);
		double gananciaTotalProducto = inventario.getGananciaProducto(codigoBarras);
		
		double neto = gananciaTotalProducto - costoProducto;
		
		return neto;
	}
	
	public Supermercado getSupermercado()
	{
		return this.supermercado;
	}
	
	
	
}
