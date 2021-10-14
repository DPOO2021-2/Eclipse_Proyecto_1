package modelo;

public class ClienteRegistrado extends Cliente
{
	
	private String nombre;
	private String cedula;
	
	private int puntos;
	private String sexo;
	private int edad;
	private String estadoCivil;
	private String situacionLaboral;
		
	
	public ClienteRegistrado(String nombre, String cedula, String sexo, int edad, String estadoCivil, String situacionLaboral) 
	{
		super(nombre, cedula);
		this.puntos = 0;
		this.sexo = sexo;
		this.edad = edad;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
	}
	
	@Override 
	public void sumarPuntos(int puntos)
	{
		int puntos_originales = getPuntos();
		setPuntos(puntos_originales + puntos);
	}
	
	public void setPuntos(int puntos)
	{
		this.puntos = puntos;
	}
	
	public int getPuntos()
	{
		return this.puntos;
	}
}
