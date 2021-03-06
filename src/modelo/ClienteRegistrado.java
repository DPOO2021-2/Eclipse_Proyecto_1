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
	
	
	public String getNombre() 
	{
		return nombre;
	}

	public String getCedula() 
	{
		return cedula;
	}

	public String getSexo() 
	{
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
		this.cedula = cedula;
		this.nombre = nombre;
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
		//Double i = puntos_originales + puntos;
//		System.out.println("qbo pues"+i.toString());
	}
	
	public void setPuntos(double puntos)
	{
		this.puntos = puntos;
	}
	
	public Double getPuntos()
	{
		return this.puntos;
	}
	
	@Override
	public boolean restarPuntos(Integer puntosRestar)
	{ 
		if(getPuntos() >= puntosRestar)
		{
			this.puntos = getPuntos() -puntosRestar;
			return true;
		}
		
		else
		{
			return false;
		}
		
		
	}
	
}
