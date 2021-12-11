package modelo;

import java.util.ArrayList;
import java.util.Date;

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
		for (String codigoBarras : compra.getProductos().keySet())
		{
			if(codigoBarras.equals(this.productoAplicado))
			{
				aplicada = true;

				
				
			}
		}
		if(aplicada)
		{
			compra.registrarPromocion(this);	
		}
	}
}