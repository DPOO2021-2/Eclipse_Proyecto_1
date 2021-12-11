package modelo;

import java.util.ArrayList;
import java.util.Date;

import procesamiento.Inventario;



public abstract class Promocion 
{
	private Date fechaInicio;
	private Date fechaFinal;
	protected String productoAplicado;
	protected Inventario inventario;
	
	public Promocion(Date fechaInicio, Date fechaFinal, String productoAplicado, Inventario inventario)
	{
		this.fechaFinal = fechaFinal;
		this.fechaInicio = fechaInicio;
		this.productoAplicado =  productoAplicado;
		this.inventario = inventario;
	}
	
	public boolean aplicable()
	{
		Date hoy = new Date();
		
		boolean aplicable = hoy.after(fechaInicio) && hoy.before(fechaFinal);
		
		return aplicable;
		
	}
	
	abstract public void aplicarPromocion(Compra compra);

}
