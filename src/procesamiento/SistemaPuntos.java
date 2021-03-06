package procesamiento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import modelo.Cliente;
import modelo.ClienteRegistrado;

public class SistemaPuntos 
{
	//llave, valor = cedula del cliente, clienteRegistrado
	private HashMap<String, ClienteRegistrado> clientes;
	
	
	public SistemaPuntos()
	{
		this.clientes = new HashMap<String, ClienteRegistrado>();
	}
	
	public boolean registrarCliente(String nombre, String cedula, String sexo, String edad, String estadoCivil, String situacionLaboral)
	{
		
		try 
		{
		//System.out.println(edad);
		Integer edadInt = Integer.parseInt(edad);
		ClienteRegistrado cliente = new ClienteRegistrado(nombre, cedula, sexo, edadInt, estadoCivil, situacionLaboral);
		
		
//		if(clienteRegistrado(cedula))
		
//dejar asi para que siempre registre (si ya estaba registrado lo sobreescribe)
//osea ya no hace lo que dice el comentario de Supermercado.java linea 87
			if(false)
			{
				return false;
			}
			else
			{
				clientes.put(cedula, cliente);
				return true;
			}
		
		}
		catch(NumberFormatException nfe)
		{
//			System.out.println("numero raro");
			return false;
		}
		
	}
	
	public ClienteRegistrado getCliente(String cedula) 
	{
		return clientes.get(cedula);
	}
	
	public Cliente getClienteSiempre(String cedula , String nombreCliente, double puntosCompra)
	{
		if (clientes.containsKey(cedula))
		{
			return getCliente(cedula);
		}
		else
		{
			Cliente cliente = new Cliente(nombreCliente, cedula, puntosCompra);
			return cliente;
		}
	}
	
	public double calcularPuntos(double precioTotal) 
	{
		//tasa = 1 punto / 1000 pesos
		double tasa = 0.001;
//		System.out.println(tasa);
//		System.out.println(precioTotal);
//		System.out.println(precioTotal*tasa);
		return precioTotal*tasa;
	}
	
	public Cliente registrarPuntos(String cedula, String nombre, double puntosCompra) 
	{	
		
		if(clienteRegistrado(cedula))
		{
			ClienteRegistrado cliente = getCliente(cedula);
			cliente.sumarPuntos(puntosCompra);
			return cliente;
		}
		else
		{
			Cliente cliente = new Cliente(nombre, cedula, puntosCompra);
			return cliente;
		}
		
	}
	
	public boolean clienteRegistrado(String cedula) 
	{
		return clientes.containsKey(cedula);
	}

	
	public HashMap<String, ClienteRegistrado> getClientes()
	{
		return this.clientes;
	}

	public boolean vacio() 
	{
		return this.clientes.isEmpty();
	}
	
	
	
	

	

}
