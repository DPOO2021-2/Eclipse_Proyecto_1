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
	
	public void sumarPuntos(double Puntos)
	{
		//System.out.println("HOLA");
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public String getCedula() 
	{
		return this.cedula;
	}

	public boolean restarPuntos(Integer puntosRestar) 
	{
		return false;
	}
	
	
	

}

