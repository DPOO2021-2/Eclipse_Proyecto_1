package modelo;

import java.util.Date;

public class Lote 
{
		private double cantidadOriginal;
		private double cantidadActual;
		private Date fechaVencimiento;
		private double costoTotal;
		private double precio_publico_unidad;
		private String precio_publico_unidad_medida;
		

		private Date fechaLote;
		
//		este será el identificador
		private String codigo;
		
		
		public Lote(String codigoLote, double cantidadOriginal, Date fechaVencimiento, double costoTotal,
				double precio_publico_unidad, String precio_publico_unidad_medida)
		{
			this.cantidadOriginal = cantidadOriginal;
			this.cantidadActual = cantidadOriginal;
			this.fechaVencimiento = fechaVencimiento;
			this.costoTotal = costoTotal;
			this.precio_publico_unidad = precio_publico_unidad;
			this.precio_publico_unidad_medida = precio_publico_unidad_medida;
			
			
			//esta es la fecha del momento en el que se llama este metodo
			this.fechaLote = new Date();
			
			
			this.codigo = codigoLote;
			
			
		}
		
		
		
		
		/*
		 * 
		 *  import java.text.SimpleDateFormat;
		    import java.util.Date;
			import java.text.ParseException;
			public class CurrentDateTimeExample2 
			{
				public static void main(String[] args) 
				{
					try{
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();
			        	Date date2 = formatter.parse("14/10/2021");
						System.out.println(formatter.format(date));
				        System.out.println(formatter.format(date2));
				        System.out.println(date.before(date2));
			
						}
						catch (ParseException ex) {}
				}
			}

		 */
		
		
		public double getCostoLote() 
		{
			return this.costoTotal;
		}
		
		
		public double restarCantidad(double cantidad)
		{
			cantidad = Math.abs(cantidad);
			double loQueFaltaRestar = 0;
			double actualC = getCantidadActual();
			if (actualC>0)
			{
				double restante = actualC-cantidad;
				if (restante>0 || restante==0)
				{
					setCantidadActual(restante);
				}
				else
				{
					setCantidadActual(0);
					loQueFaltaRestar = -restante;
				}
				
			}
			else 
			{
				//no se pudo restar porque actualC era 0
				//o era negativa(esto deberia ser imposible de todas formas)
				loQueFaltaRestar = cantidad;
			}
			
			return loQueFaltaRestar;
		}
		
		public void setCantidadActual(double cantidad)
		{
			this.cantidadActual = cantidad;
		}
		
		public void setFechaLote(Date fecha)
		{
			this.fechaLote = fecha;
		}
		
		public double getCantidadActual()
		{
			return this.cantidadActual;
		}
		
		public Date getFecha()
		{
			return this.fechaLote;
		}




		public String getCodigo() 
		{
			return this.codigo;
		}


}
