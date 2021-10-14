package modelo;

public class Lote {
		private double cantidadOriginal;
		private double cantidadActual;
		private String fechaVencimiento;
		private double costoTotal;
		private double precio_publico_unidad;
		private double precio_publico_unidad_medida;
		
//		este será el identificador
		private String fechaLote;
		
		
		public Lote(double cantidadOriginal, String fechaVencimiento, double costoTotal,
				double precio_publico_unidad, double precio_publico_unidad_medida, String fechaLote)
		{
			this.cantidadOriginal = cantidadOriginal;
			this.cantidadActual = cantidadOriginal;
			this.fechaVencimiento = fechaVencimiento;
			this.costoTotal = costoTotal;
			this.precio_publico_unidad = precio_publico_unidad;
			this.precio_publico_unidad_medida = precio_publico_unidad_medida;
			this.fechaLote = fechaLote;
		}
		
		
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
		{this.cantidadActual = cantidad;}
		
		public double getCantidadActual()
		{return this.cantidadActual;}
		
		public String getFecha()
		{return this.fechaLote;}


}
