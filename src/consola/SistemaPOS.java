package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import procesamiento.Supermercado;


public class SistemaPOS {
	
	private Supermercado supermercadoYumbo;

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
	{
		SistemaPOS programa = new SistemaPOS();
		programa.ejecutarAplicacion();
	}
	
	public void ejecutarRegistrarCliente() 
	{
		System.out.println("\n Escogió la opción de Registrar Cliente \n");
		
		String nombre = input("Por favor escriba el nombre del cliente");
		
		String cedula = input("Por favor escriba la cedula del cliente");
		
		String sexo = input("Por favor escriba el sexo del cliente");
		
		int edad = Integer.parseInt(input("Por favor escriba la edad del cliente"));
		
		String estado_civil = input("Por favor escriba el estado laboral del cliente");
		
		String situacion_laboral = input("Por favor escriba la situacion laboral del cliente");
		
		
		if(supermercadoYumbo.registrarCliente(nombre, cedula, sexo, edad, estado_civil, situacion_laboral)) 
		{
			System.out.println("\nSe ha registrado el cliente");
		}
		
		else
		{
			System.out.println("\nNo se ha registrado el cliente");
		}
	}
	
	public void ejecutarRegistrarCompra() 
	{
		System.out.println("\n Escogió la opción de Registrar Compra \n");
		
		String nombre = input("Por favor escriba el nombre del cliente");
		
		String cedula = input("Por favor escriba la cedula del cliente");
		
		Map<String, Double> productosyCantidades = new HashMap<String, Double>();
		
		boolean respuesta = true;
		
		while(respuesta)
		{
			String nombreProducto = input("Por favor escriba el codigo del producto");
			Double cantidadProducto = Double.parseDouble("Por favor escriba la cantidad del producto");
			
			productosyCantidades.put(nombreProducto, cantidadProducto);
			
			String respuestaC = input("Desea agregar otro producto?").toLowerCase();
			
			if (!("si".equals(respuestaC)))
			{
				respuesta = false;
			}
		}
		
		String registrarCompraR = supermercadoYumbo.registrarCompra(productosyCantidades, nombre, cedula);
		
		if ("no".equals(registrarCompraR))
		{
			System.out.println("\nLas cantidades de estos productos no estaban disponibles");
		}
		else
		{
			System.out.println("\nSu factura es:\n");
			System.out.println(registrarCompraR);
		}
	}
	
	public void salir() throws IOException 
	{
		supermercadoYumbo.actualizarArchivos("puntos");
		supermercadoYumbo.actualizarArchivos("inventario_productos");
	}
	

	public void ejecutarAplicacion() throws IOException, ParseException
	{
			System.out.println("Bienvenido al Supermercado Yumbo");
							
			this.supermercadoYumbo = new Supermercado("Yumbo");
			
			boolean continuar = true;
			while (continuar)
			{	
				try
				{	
					mostrarMenu();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
					
					if (opcion_seleccionada == 1)
						ejecutarRegistrarCliente();
					else if (opcion_seleccionada == 2 && supermercadoYumbo != null)
						ejecutarRegistrarCompra();
					else if (opcion_seleccionada == 3)
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
	

	public void mostrarMenu()
	{
		System.out.println("\n Opciones de la aplicacion");
		System.out.println("1. Registrar un Cliente");
		System.out.println("2. Registrar una compra");
		
		System.out.println("3. Salir de la aplicacion");
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
