package modelo;

public class ClienteRegistrado extends Cliente
{
	
	private String nombre;
	private String cedula;
	
	private double puntos;
	private String sexo;
	private int edad;
	private String estadoCivil;
	private String situacionLaboral;
	
	
	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public String getSexo() {
		return sexo;
	}

	public Integer getEdad() {
		return edad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getSituacionLaboral() {
		return situacionLaboral;
	}

	
		
	
	public ClienteRegistrado(String nombre, String cedula, String sexo, int edad, String estadoCivil, String situacionLaboral) 
	{
		super(nombre, cedula, 0);
		this.sexo = sexo;
		this.edad = edad;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
	}
	
	@Override 
	public void sumarPuntos(double puntos)
	{
		double puntos_originales = getPuntos();
		setPuntos(puntos_originales + puntos);
	}
	
	public void setPuntos(double puntos)
	{
		this.puntos = puntos;
	}
	
	public Double getPuntos()
	{
		return this.puntos;
	}
}
