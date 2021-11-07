package modelo;

import java.util.Date;
import java.util.HashMap;

public class TransformadorFechas 
{

	
	public static String fechaTransf(Date date)
	{
		
		String[] partes = date.toString().split(" ");
		String mesInutil = partes[1];
		String dia = partes[2];
		String anio = partes[5];
		
		HashMap<String, String> meses = new HashMap<String, String>();
		meses.put("Jan", "01");
		meses.put("Feb", "02");
		meses.put("Mar", "03");
		meses.put("Apr", "04");
		meses.put("May", "05");
		meses.put("Jun", "06");
		meses.put("Jul", "07");
		meses.put("Aug", "08");
		meses.put("Sep", "09");
		meses.put("Oct", "10");
		meses.put("Nov", "11");
		meses.put("Dec", "12");
		
		String mes = meses.get(mesInutil);
		
		String respuesta = dia+"_"+mes+"_"+anio;
		
		return respuesta;
	//	return date.toString();
		
	}
}
