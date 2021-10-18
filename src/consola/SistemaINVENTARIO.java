package consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import procesamiento.CalculadoraFinanzas;
import procesamiento.Supermercado;

public class SistemaINVENTARIO 
{	
	private CalculadoraFinanzas calcFinzs;
	private Supermercado supermercadoYumbo;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
		
		
		SistemaINVENTARIO programa = new SistemaINVENTARIO();
		programa.correr();
		
	}
	
	
	public void correr() throws FileNotFoundException, IOException, ParseException
	{
		this.supermercadoYumbo = new Supermercado("Yumbo");
		this.calcFinzs = new CalculadoraFinanzas(supermercadoYumbo);
		
		/*
		supermercadoYumbo.registrarLotes("archivo_lotes1");
		supermercadoYumbo.registrarLotes("archivo_lotes2");
		System.out.println(supermercadoYumbo.existeArchivoUsuarioLote("archivo_lotes1"));
		System.out.println(supermercadoYumbo.getInventario().getProducto("100007").getLotes().size());
		System.out.println(supermercadoYumbo.eliminarLoteVencido("100007", "1"));
		System.out.println(supermercadoYumbo.getInventario().getProducto("100007").getLotes().size());
		System.out.println(supermercadoYumbo.eliminarLoteVencido("100007", "1"));
		System.out.println(supermercadoYumbo.getInventario().getProducto("100007").getLotes().size());
		System.out.println(supermercadoYumbo.eliminarLoteVencido("100007", "2"));
		System.out.println(supermercadoYumbo.getInventario().getProducto("100007").getLotes().size());
		
		*/

	}

}
