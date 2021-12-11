package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import procesamiento.Inventario;
import procesamiento.Supermercado;

public class PromocionRegalo extends Promocion
{

	private Double cantidadAplicable;
	private Double cantidadOferta;

	public PromocionRegalo(Date fechaInicio, Date fechaFinal, String productoAplicado, Double cantidadAplicable
			, Supermercado supermercado, Double cantidadOferta) 
	{
		super(fechaInicio, fechaFinal, productoAplicado, supermercado);
		this.cantidadOferta = cantidadOferta;
		this.cantidadAplicable = cantidadAplicable;
	}

	@Override
	public void aplicarPromocion(Compra compra) 
	{
		
		boolean aplicada = false;
		Map<String, Double> prodsComprados = compra.getProductos();
		for (String codigoBarras : prodsComprados.keySet())
		{
			if(codigoBarras.equals(this.productoAplicado))
			{
				aplicada = true;

				Double vecesAplicada = 0.0;
				Double cantidadComprada = prodsComprados.get(codigoBarras);
				while (cantidadComprada-cantidadAplicable > 0)
				{
					vecesAplicada = vecesAplicada + 1;
					
					
					cantidadComprada = cantidadComprada-cantidadAplicable;
				}
				
				Double cantidadRegalar = vecesAplicada*cantidadOferta;
				
				Producto producto = supermercado.getInventario().getProducto(codigoBarras);
				
				if(producto.sePuedeComprar(cantidadRegalar))
				{
					producto.entregarProducto(cantidadRegalar);
				}
				
				prodsComprados.put(codigoBarras, prodsComprados.get(codigoBarras) + cantidadRegalar);
				
			}
		}
		if(aplicada)
		{
			compra.registrarPromocion(this);	
		}
	}

	@Override
	public String toString() 
	{
		String resultado = this.getClass().toString();
		Double lleve = cantidadAplicable+cantidadOferta;
		resultado = resultado + ":"+productoAplicado;
		resultado = resultado + "(pague "+cantidadAplicable.toString()+" lleve " +lleve.toString()+")";
				
		return null;
	}
	
	
	public String toStringARCHIVO()
	{
		String resultado = "1,"+productoAplicado+","+
				TransformadorFechas.fechaTransf(fechaInicio)+","+TransformadorFechas.fechaTransf(fechaFinal)
				+","+cantidadOferta.toString()+","+cantidadAplicable.toString();
				
		
		return resultado;
	}
}