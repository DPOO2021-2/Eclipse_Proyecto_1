package interfaz;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class VentanaGRAFICA extends JFrame
{
	public VentanaGRAFICA(String nombre, String cedula)
	{
        XYSeries series = new XYSeries("Puntos de cada compra");

        // Introduccion de datos
        series.add(1, 1);
        series.add(2, 6);
        series.add(3, 3);
        series.add(4, 10);

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

}





