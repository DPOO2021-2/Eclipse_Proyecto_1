package modelo;

public class Cliente {
	
	private String nombre;
	private String cedula;
	
	//estos puntos son de una sola compra ya que este no es un cliente registrado
	private double puntos;
	
	public Cliente(String nombre, String cedula, double puntos)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
	public void sumarPuntos(int Puntos)
	public void sumarPuntos(double Puntos)
	{
	}

}

