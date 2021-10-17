package consola;

import java.io.FileNotFoundException;
import java.io.IOException;

import procesamiento.CalculadoraFinanzas;
import procesamiento.Supermercado;

public class SistemaINVENTARIO 
{	
	private CalculadoraFinanzas calcFinzs;
	private Supermercado supermercadoYumbo;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		
		
		SistemaINVENTARIO programa = new SistemaINVENTARIO();
		programa.correr();
		
	}
	
	
	public void correr() throws FileNotFoundException, IOException
	{
		this.supermercadoYumbo = new Supermercado("Yumbo");
		//supermercadoYumbo.registrarLotes("archivo_lotes1");
		//System.out.println(supermercadoYumbo.existeArchivoUsuarioLote("archivo_lotes1"));
	}

}
