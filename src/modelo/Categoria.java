package modelo;

import java.util.ArrayList;
import java.util.Collection;

public class Categoria 
{
	private String nombre;
	
	private Collection<Categoria> subcategorias;
	
	public Categoria(String nombre)
	{
		this.nombre = nombre;
		this.subcategorias = new ArrayList<Categoria>();
	}
	
	public void agregarSubcategorias(Collection<Categoria> subcategorias)
	{
		for (Categoria categoria : subcategorias)
		{
			this.subcategorias.add(categoria);
		}
	}
	
	public Collection<Categoria> getSubcategorias()
	{
		return this.subcategorias;
		
	}
	

}
