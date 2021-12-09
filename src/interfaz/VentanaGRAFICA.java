package interfaz;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import procesamiento.Supermercado;

public class VentanaGRAFICA extends JFrame
{
	
	
	public VentanaGRAFICA(String nombre, String cedula, Supermercado supermercado)
	{
        XYSeries series = new XYSeries("Puntos de cada compra");
        
        try
        {
        	 ArrayList<Double> puntos = supermercado.puntosComprasCliente(cedula);
             // Introduccion de datos
             for(int i = 0; i<puntos.size(); i = i+1)
             {
             	series.add(i,puntos.get(i));
             	System.out.println(i);
             	System.out.println(puntos.get(i));
             }
             
             //series.add(1,4);
             //series.add(2,3);
             //series.add(10,42);
             

             XYSeriesCollection dataset = new XYSeriesCollection();
             dataset.addSeries(series);

             JFreeChart chart = ChartFactory.createXYLineChart
             (
                     "Puntos del cliente: "+ nombre, // Título
                     "Numero de Compras", // Etiqueta Coordenada X
                     "Puntos", // Etiqueta Coordenada Y
                     dataset, // Datos
                     PlotOrientation.VERTICAL,
                     true, // Muestra la leyenda de los productos (Producto A)
                     false,
                     false
             );

             // Mostrar la grafica en pantalla
             ChartFrame frame = new ChartFrame("Grafica Actividad Cliente", chart);
             frame.getContentPane().setEnabled(false);
             frame.pack();
             frame.setVisible(true);
        }
        
        catch(NullPointerException npe)
        {
        	JOptionPane.showMessageDialog(this, "No se encuentran compras de este cliente.",
					"Error", JOptionPane.ERROR_MESSAGE);
        }
       
	}

}





