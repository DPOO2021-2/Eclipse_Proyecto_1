package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;
import procesamiento.Supermercado;



public abstract class Promocion 
{
	protected Date fechaInicio;
	protected Date fechaFinal;
	protected String productoAplicado;
	protected Supermercado supermercado;
	
	public Promocion(Date fechaInicio, Date fechaFinal, String productoAplicado, Supermercado supermercado)
	{
		this.fechaFinal = fechaFinal;
		this.fechaInicio = fechaInicio;
		this.productoAplicado =  productoAplicado;
		this.supermercado = supermercado;
	}
	
	public boolean aplicable()
	{
		Date hoy = new Date();
		
		boolean aplicable = hoy.after(fechaInicio) && hoy.before(fechaFinal);
		
		return aplicable;
		
	}
	
	abstract public void aplicarPromocion(Compra compra);
	
	abstract public String toStringARCHIVO();

}
