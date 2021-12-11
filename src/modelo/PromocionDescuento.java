package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;
import procesamiento.Supermercado;

public class PromocionDescuento extends Promocion
{
	private Double descuento;

	public PromocionDescuento(Date fechaInicio, Date fechaFinal, String productoAplicado, Double descuento
			, Supermercado supermercado) 
	{
		super(fechaInicio, fechaFinal, productoAplicado, supermercado);
		this.descuento = descuento;

	}

	@Override
	public void aplicarPromocion(Compra compra) 
	{
		boolean aplicada = false;
		for (String codigoBarras : compra.getProductos().keySet())
		{
			
			if(codigoBarras.equals(this.productoAplicado))
			{
				Double precioProd =  supermercado.getInventario().getCostoProducto(codigoBarras);
				Double descuentoRealizado = precioProd*descuento;
				Double costoFinal = compra.getCostoFinal() - descuentoRealizado;
				
				compra.setCostoFinal(costoFinal);
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
		resultado = resultado + "("+descuento.toString()+"%)";
				
		return null;
	}
	
	public String toStringARCHIVO()
	{
		String resultado = "2,"+productoAplicado+","+
				TransformadorFechas.fechaTransf(fechaInicio)+","+TransformadorFechas.fechaTransf(fechaFinal)
				+","+descuento.toString()+",0";
				
		
		return resultado;
	}
	

}
