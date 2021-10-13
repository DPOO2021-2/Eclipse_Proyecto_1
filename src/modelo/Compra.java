package modelo;

import java.util.ArrayList;

public class Compra {
	
	private Integer costoFinal;
	
	private Cliente cliente;
	
	private ArrayList<Producto> productos;
	
	
	public Compra(Integer costoFinal, Cliente cliente, ArrayList<Producto> productos) 
	{
		this.costoFinal = costoFinal;
		this.cliente = cliente;
		this.productos = productos;
	}
	
	
	
	public Integer getCostoFinal()
	{
		return this.costoFinal;
	}
	
	public String generarfactura()
	{
		String factura = "";
		for (Producto producto:  productos)
		{
			factura = factura + producto.getNombre() + "-" + producto.getCodigoBarras() + ",";
		}
		factura = factura + "costo total: "+ getCostoFinal().toString();
		return factura;
	}

}
