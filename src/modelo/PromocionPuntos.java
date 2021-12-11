package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;
import procesamiento.Supermercado;

public class PromocionPuntos extends Promocion
{

	private Double multiplicadorPuntos;

	public PromocionPuntos(Date fechaInicio, Date fechaFinal, String productoAplicado, Double multiplicadorPuntos
			, Supermercado supermercado) 
	{
		super(fechaInicio, fechaFinal, productoAplicado, supermercado);
		this.multiplicadorPuntos = multiplicadorPuntos;

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
	
	
	@Override
	public String toString() 
	{
		String resultado = this.getClass().toString();
		resultado = resultado + ":"+productoAplicado;
		resultado = resultado + "("+multiplicadorPuntos.toString()+")";
				
		return null;
	}
	

}