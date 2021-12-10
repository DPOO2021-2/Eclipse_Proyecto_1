
package interfaz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import modelo.Producto;
import procesamiento.Supermercado;

public class VentanaGraficaPRODUCTO extends JFrame
{
	
	
	public VentanaGraficaPRODUCTO(String codigoBarras, String fecha1, String fecha2, Supermercado supermercado)
	{
        DefaultCategoryDataset series = new DefaultCategoryDataset();
        
        try
        {
        	Producto producto = supermercado.getInventario().getProducto(codigoBarras);
        	HashMap <Date, Double> datos = producto.getActividad(fecha1, fecha2);
        	 //ArrayList<Double> puntos = supermercado.puntosComprasCliente(cedula);
             // Introduccion de datos
             for(Date fecha: datos.keySet())
             {
            	String fechaStr = fecha.toString();
            	Double valor = datos.get(fecha);
            	series.setValue(valor, "Cantidad en "+fechaStr, fechaStr);
//            	System.out.println(fechaStr);

            	
             }             
             JFreeChart graficoBarras = ChartFactory.createBarChart3D("Actividad Producto: "+codigoBarras, 
            		 "Fecha", "Cantidad en Inventario", series);
             
             ChartFrame frame = new ChartFrame("Grafica Actividad Producto", graficoBarras);
             frame.getContentPane().setEnabled(false);
             frame.pack();
             frame.setVisible(true);
             
             
        }
        
        catch(NullPointerException npe)
        {
        	JOptionPane.showMessageDialog(this, "No se encuentra este producto.",
					"Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) 
        {
        	JOptionPane.showMessageDialog(this, "Alguna de las fechas no tiene el formato correcto.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
       
	}

}