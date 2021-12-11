package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;

public class PromocionPuntos extends Promocion
{

	//private Double descuento;

	public PromocionPuntos(Date fechaInicio, Date fechaFinal, String productoAplicado, Double descuento
			, Inventario inventario) 
	{
		super(fechaInicio, fechaFinal, productoAplicado, inventario);
	//	this.descuento = descuento;

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