package procesamiento;

import modelo.Producto;

public class CalculadoraFinanzas 
{
	private Supermercado supermercado;
	
	public CalculadoraFinanzas(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}
	
	public Double calcularRendimiento(String codigoBarras) throws NullPointerException
	{
		Inventario inventario = getSupermercado().getInventario();
				
		try
		{
		Double costoProducto = inventario.getCostoProducto(codigoBarras);
		Double gananciaTotalProducto = inventario.getGananciaProducto(codigoBarras);
//		System.out.println("ganancia:"+gananciaTotalProducto.toString());
//		System.out.println("costo:"+costoProducto.toString());
		
		Double neto = gananciaTotalProducto - costoProducto;
		
		return neto;
		}
		catch(NullPointerException ne)
		{
			throw ne;
		}

	}
	
	public Supermercado getSupermercado()
	{
		return this.supermercado;
	}
	
	
	
}
