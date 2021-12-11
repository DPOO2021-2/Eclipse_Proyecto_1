package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;

public class PromocionRegalo extends Promocion
{

	//private Double descuento;

	public PromocionRegalo(Date fechaInicio, Date fechaFinal, String productoAplicado, Double descuento
			, Inventario inventario) 
	{
		super(fechaInicio, fechaFinal, productoAplicado, inventario);
	//	this.descuento = descuento;

	}

	@Override
	public void aplicarPromocion(Compra compra) 
	{
		for (String codigoBarras : compra.getProductos().keySet())
		{
			if(codigoBarras.equals(this.productoAplicado))
			{

			}
		}
	}
}