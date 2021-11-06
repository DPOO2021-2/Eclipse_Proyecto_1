package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Categoria 
{
	private String nombre;
	
	private Collection<String> subcategorias;
	
	public static HashMap<String, Categoria> categorias;
	
	public Categoria(String nombre)
	{
		if(categorias.equals(null))
		{
			categorias = new HashMap<String, Categoria>();
		}
		
		this.nombre = nombre;
		this.subcategorias = new ArrayList<String>();
		categorias.put(nombre, this);
		
		//mejpr tomar strings desde aca y hacer toda la conversion
	}
	
	public void agregarSubcategoria(String subcategoria)
	{
		
		this.subcategorias.add(subcategoria);
		
	}
	
	public Collection<String> getSubcategorias()
	{
		return this.subcategorias;
		
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	

}
