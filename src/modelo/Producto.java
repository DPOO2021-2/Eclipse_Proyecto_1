package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Date;

public abstract class Producto 
{
	
	
	private String nombre;
	private String codigoBarras;
	
	private ArrayList<Categoria> categorias;
	
	private String tipoRefrigerado;
	private double precioActual;
	private String precioActualMedida;
	
	private double gananciaTotal;
	
	private HashMap<Date, Double> registroActividad;

	//las llaves seran codigos que deberan venir en el archivo csv junto el codigo de barras del producto de la forma
	//xxxx-c  donde xxxx es el cdoigo de barras del producto y c el codigo del lote
	private LinkedHashMap<String, Lote> lotes;
	
	
	
	
	private String imagen;
	
	
	public Producto(String nombre, String codigoBarras, String tipoRefrigerado, double precio_publico_unidad, String precio_publico_unidad_medida)
	{
		this.nombre = nombre;
		this.codigoBarras = codigoBarras;
		this.categorias = new ArrayList<Categoria>();
		this.tipoRefrigerado = tipoRefrigerado;
		this.precioActual = precio_publico_unidad;
		this.precioActualMedida = precio_publico_unidad_medida;
		this.gananciaTotal = 0;
		this.lotes = new LinkedHashMap<String, Lote>();
		this.imagen = "NOIMAGE";
		
		this.registroActividad = new LinkedHashMap<Date, Double>();
		
	}
	
	public Double getPrecioActual()
	{return this.precioActual;}
	
	public String getNombre()
	{return this.nombre;}
	
	public String getCodigoBarras()
	{return this.codigoBarras;}
	
	public String getImagen()
	{return this.imagen;}
	
	public double getCostoTotal()
	{
		double costoTotal = 0;
		for (Lote lote: getLotes().values())
		{
			costoTotal = costoTotal + lote.getCostoTotal();
		}
		return costoTotal;
	}
	
	
	public LinkedHashMap<String, Lote> getLotes()
	{
		return this.lotes;
	}

	public Double getGananciaTotal()
	{
		return this.gananciaTotal;
	}
	
	public void setGananciaTotal(double ganancia)
	{
		this.gananciaTotal = ganancia;
	}
	
	
	public void agregarLote(Lote lote)
	{
		lotes.put(lote.getCodigo(), lote);
		this.actualizarActividad();
		//System.out.println("hola");
	}
	
	public void agregarLote2(Lote lote)
	{
		lotes.put(lote.getCodigo(), lote);

		//System.out.println("hola");
	}
	
	public double cantidadTotal()
	{
		double cantidad = 0;
		for (Lote lote: getLotes().values())
		{
			cantidad = cantidad + lote.getCantidadActual();
		}
		return cantidad;
	}
	

	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	public double calcularPrecio(double cantidad)
	{
		
		return getPrecioActual()*cantidad;
	}
	


	abstract public boolean sePuedeComprar(double cantidad);
	
	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	abstract public double comprarProducto(double cantidad);

	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	abstract public void entregarProducto(double cantidad);

	

	
	public void agregarCategorias(String[] categorias_nuevas)
	{
		int cantidad = categorias_nuevas.length;
		ArrayList<Categoria> categorias_p = getCategorias();
		if(cantidad > 0)
		{
			categorias_p.removeAll(categorias_p);
		
		
			for (int i=0; i< cantidad; i = i + 1)
			{
				Categoria cate;
				String cateS = categorias_nuevas[i];
				if (!(Categoria.categorias.containsKey(cateS)))
				{
					cate = new Categoria(cateS);
					
				}
				else
				{
					cate = Categoria.categorias.get(cateS);
				}
				if(i+1<cantidad)
				{
					cate.agregarSubcategoria(categorias_nuevas[i+1]);
				}
				categorias_p.add(cate);
				
			}
		
		}
		
	}

	public ArrayList<Categoria> getCategorias()
	{
		return this.categorias;
	}
	
	
	public boolean eliminarLoteVencido(String codigoLote)
	{
		LinkedHashMap<String, Lote> lotes = getLotes();
		
		Date fechaHoy = new Date();
		
		if(lotes.containsKey(codigoLote))
		{
			Date fechaVenci = getLote(codigoLote).getFecha();
			if (fechaVenci.before(fechaHoy))
			{
				lotes.remove(codigoLote);
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	public Lote getLote(String codigoLote)
	{
		return getLotes().get(codigoLote);
	}

	public void actualizarPrecio(double precio_publico_unidad, String precio_publico_unidad_medida)
	{
		setPrecioActual(precio_publico_unidad);
		setPrecioActualMedida(precio_publico_unidad_medida);
	}


	public  void setPrecioActual(double precio_publico_unidad) 
	{
		this.precioActual = precio_publico_unidad;
	}
	
	private void setPrecioActualMedida(String precio_publico_unidad_medida) 
	{
		this.precioActualMedida = precio_publico_unidad_medida;
	}

	public String getTipoRefrigerado() 
	{
		// TODO Auto-generated method stub
		return this.tipoRefrigerado;
	}

	public String getPrecioActualMedida() 
	{
		// TODO Auto-generated method stub
		return this.precioActualMedida;
	}

	public String getStringCategorias() 
	{
		String str = "";
		for (Categoria c: getCategorias())
		{
			str = str  + c.getNombre() + "-";
			
		}
		str = str.substring(0, str.length() - 1);
		
		return str;
	}
	
	public boolean perteneceACategoria(String categoriaS)
	{
		
		boolean categoriaExiste = Categoria.categorias.containsKey(categoriaS);
		
		boolean resultado = false;
		
		if(categoriaExiste)
		{
			Categoria categoria = Categoria.categorias.get(categoriaS);

			ArrayList<Categoria> categorias_p = getCategorias();
			int cantidad = categorias_p.size();
			int i = 0;
			
			
			resultado = categorias_p.contains(categoria);
			
			while( (!resultado) && (i<cantidad) )
			{
				
				resultado = resultado || categoria.getSubcategorias().contains(categorias_p.get(i).getNombre());
				
				i = i + 1;
			}
			
		}
		
		return resultado;
		
		
	}

	public void setImagen(String nombreImagen) 
	{
		
		this.imagen = nombreImagen;
	}
	
	public void actualizarActividad(Date fecha)
	{
		this.registroActividad.put(fecha, cantidadTotal());
	}
	
	public void actualizarActividad()
	{
		Date fecha = new Date();
			
		actualizarActividad(fecha);
				
	}
	
	public HashMap<Date, Double> getActividad(String fecha1Str, String fecha2Str) throws ParseException
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
		
		Date fecha1 = formatter.parse(fecha1Str);
		
		Date fecha2 = formatter.parse(fecha2Str);
		
		HashMap<Date, Double> retorno = new HashMap<Date, Double>();
		
		HashMap<Date, Double> actividadTotal = this.registroActividad;
		
		for (Date fecha: actividadTotal.keySet())
		{
			if (fecha.before(fecha2) && fecha.after(fecha1))
			{
				Double valorCantidad = actividadTotal.get(fecha);
				retorno.put(fecha, valorCantidad);
			}
			
		}
		
		
		
		return retorno;
	}
	
	
	public String actividadString()
	{
		String retorno = "";
		HashMap<Date, Double> actividad = this.registroActividad;
		for(Date fecha: actividad.keySet())
		{
			retorno = retorno + TransformadorFechas.fechaTransf(fecha)+":"+actividad.get(fecha).toString()+"-";
			
			
		}
		retorno = retorno.substring(0, retorno.length() - 1);
		
		return retorno;
	}
	
	public void cargarActividad(String actividadString) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
		
		
		String[] arreglo = actividadString.split("-");
		for (String parejaStr: arreglo)
		{
			String[] pareja = parejaStr.split(":");
			
			Date fecha = formatter.parse(pareja[0]);
			
			Double cantidad = Double.parseDouble(pareja[1]);
			
			this.registroActividad.put(fecha, cantidad);
			
			
		}
	}

}
