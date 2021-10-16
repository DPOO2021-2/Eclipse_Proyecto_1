package procesamiento;

import modelo.Producto;

public class CalculadoraFinanzas {
	
	public double calcularRendimiento(String codigoBarras) {
		double producto = Inventario.getCostoProducto(codigoBarras);
		double gananciaTotalProducto = Producto.getGanaciaTotal;
		
		double neto = gananciaTotalProducto - producto;
		return neto;
	}
}
