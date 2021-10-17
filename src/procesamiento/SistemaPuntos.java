package procesamiento;

import java.util.HashMap;

import modelo.Cliente;
import modelo.ClienteRegistrado;

public class SistemaPuntos 
{
	
	private HashMap<String, ClienteRegistrado> clientes;
	
	public boolean registrarCliente(String nombre, String cedula, String sexo, int edad, String estadoCivil, String situacionLaboral) 
	{
		ClienteRegistrado cliente = new ClienteRegistrado(nombre, cedula, sexo, edad, estadoCivil, situacionLaboral);
		
		if(clientes.containsKey(cedula))
		{
			return false;
		}
		else
		{
			clientes.put(cedula, cliente);
			return true;
		}
	}
	
	public ClienteRegistrado getCliente(String cedula) 
	{
		return clientes.get(cedula);
	}
	
	public double calcularPuntos(double precioTotal) 
	{
		//tasa = 1 punto / 1000 pesos
		double tasa = 1/1000;
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
	
	
	
	

	

}
