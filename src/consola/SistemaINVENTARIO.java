package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import procesamiento.CalculadoraFinanzas;
import procesamiento.Supermercado;



public class SistemaINVENTARIO 
{	
	private CalculadoraFinanzas calcFinzs;
	private Supermercado supermercadoYumbo;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
		SistemaINVENTARIO programa = new SistemaINVENTARIO();
		programa.ejecutarAplicacion();
	}
	
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException, ParseException
	{
			System.out.println("Bienvenido al Inventario del Supermercado Yumbo");
							
			this.supermercadoYumbo = new Supermercado("Yumbo");
			this.calcFinzs = new CalculadoraFinanzas(supermercadoYumbo);
			
			boolean continuar = true;
			while (continuar)
			{	
				try
				{	
					mostrarMenu();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
					
					if (opcion_seleccionada == 1)
						ejecutarRegistrarArchivoLotes();
					else if (opcion_seleccionada == 2 && supermercadoYumbo != null)
						ejecutarEliminarLote();
					else if (opcion_seleccionada == 3 && supermercadoYumbo != null)
						calcularRendimiento();
					else if (opcion_seleccionada == 4 && supermercadoYumbo != null)
						ejecutarlotesYcantidades();
					else if (opcion_seleccionada == 5)
					{
						System.out.println("Saliendo de la aplicacion ...");
						salir();
						continuar = false;
					}
					else
					{
						System.out.println("Por favor seleccione una opcion valida.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los numeros de las opciones.");
				}
			}
	}
	
	
	public void ejecutarRegistrarArchivoLotes() throws FileNotFoundException, IOException, ParseException
	{
		System.out.println("\n Escogió la opción de Registrar Archivo Lotes \n");
		System.out.println("\n Por favor siga las siguientes indicaciones \n");
		 
		System.out.println("- El archivo tiene que ser de tipo .csv \n");
		System.out.println("- El archivo tiene que estar en la carpeta datos \n");
		System.out.println("- Se puede guiar para la construccion con los archivos de ejemplo existentes \n");
		System.out.println("- Ingrese solamente el nombre del archivo \n");
		
		String nombreArchivo = input("Por favor escriba el nombre del archivo");
		
				
		if(supermercadoYumbo.registrarLotes(nombreArchivo)) 
		{
			System.out.println("\nSe ha guardado el lote");
		}
		
		else
		{
			System.out.println("\nNo se ha guardado el lote");
		}
	}
	
	
	public void ejecutarEliminarLote()
	{
		System.out.println("\n Escogió la opción de Eliminar un lote \n");
		
		System.out.println("\n Ingrese el codigo del producto \n");
		
		String codigoBarras = input("Por favor escriba el nombre del archivo");
		
		System.out.println("\n Ingrese el codigo del lote \n");
		
		String codigoLote = input("Por favor escriba el nombre del archivo");
		
		
		if(supermercadoYumbo.eliminarLoteVencido(codigoBarras, codigoLote))
		{
			System.out.println("\nSe ha eliminado el lote");
		}
		
		else
		{
			System.out.println("\nNo existe el lote o no estaba vencido");
		}
	}
	
	
	public void calcularRendimiento()
	{
		System.out.println("\n Escogió la opción de Calcular el Rendimiento de un Producto \n");
		
		System.out.println("\n Ingrese el codigo del producto \n");
		
		String codigoBarras = input("Por favor escriba el nombre del archivo");
		
		double calculo = calcFinzs.calcularRendimiento(codigoBarras);
		
		System.out.println("\n La ganancia neta del producto "+ codigoBarras +" es: " + calculo);
	}
	
	
	public void ejecutarlotesYcantidades()
	{
		System.out.println("\n Escogió la opción de Calcular la cantidad de un lote \n");
		
		System.out.println("\n Ingrese el codigo del producto \n");
		
		String codigoBarras = input("Por favor escriba el nombre del archivo");
		
		Map<String, Double> lotesYcantidades = supermercadoYumbo.lotesYcantidades(codigoBarras);
		
		for (String codigoLote : lotesYcantidades.keySet())
		{
			System.out.println("\n La cantidad del lote "+ codigoLote +" es: " + lotesYcantidades.get(codigoLote));
		}
	}
	
	
	public void salir() throws IOException 
	{
		supermercadoYumbo.actualizarArchivos("puntos");
		supermercadoYumbo.actualizarArchivos("inventario_productos");
	}
	
	
	
	
	public void mostrarMenu()
	{
		System.out.println("\n Opciones de la aplicacion");
		System.out.println("1. Registrar un archivo de lotes");
		System.out.println("2. Eliminar un lote");
		System.out.println("3. Calcular rendimiento de un producto");
		System.out.println("4. Revisar cantidades lotes");
		
		System.out.println("5. Salir de la aplicacion");
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}



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
