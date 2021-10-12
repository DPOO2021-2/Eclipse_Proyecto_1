package modelo;

public class ClienteRegistrado extends Cliente{
	private String nombre;
	private String cedula;
	private int puntos;
	
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
