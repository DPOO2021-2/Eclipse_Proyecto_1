package procesamiento;

import java.util.HashMap;

import modelo.Cliente;
import modelo.ClienteRegistrado;

public class SistemaPuntos 
{
	
	private HashMap<String, ClienteRegistrado> clientes;
	
	public void registrarCliente(int infoCliente) 
	{
		
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
	
	
	
	
	
	public void crearArchivos() 
	{
		
	}
	
	public void leerInfoArchivos() 
	{
		
	}
	
	public void actualizarArchivos() 
	{
		
	}

	

}
