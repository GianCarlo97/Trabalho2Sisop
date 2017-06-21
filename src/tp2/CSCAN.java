
package tp2;

import java.awt.BasicStroke;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CSCAN implements DiskScheduler{
    private int[] requestString;
    private int numCilindros;
    private int initCilindro;
    
    public CSCAN(int[] requestString, int numCilindros, int initCilindro){
        this.requestString = requestString;
        this.numCilindros = numCilindros;
        this.initCilindro = initCilindro;
    }

    @Override
    public int serviceRequests() {
        int total = 0, i;
        
        int pos =0;
       
        
        // cria array e coloca posições inicial e final nele
        ArrayList<Integer> resultados = new ArrayList<>();        
        resultados.add(this.initCilindro);
        
        int swap;       
        for (int c = 0; c < requestString.length; c++) {
            for (int d = 0; d < requestString.length - c - 1; d++) {
                if (requestString[d] > requestString[d+1]){
                    swap       = requestString[d];
                    requestString[d]   = requestString[d+1];
                    requestString[d+1] = swap;                    
                    }
            }
        }
        
        //salva pos inicial;
        for(int j=0; j<requestString.length ; j++){
            if (requestString[j] > this.initCilindro) resultados.add(requestString[j]);
        }  
        
        resultados.add(this.numCilindros);
        resultados.add(0);
        //coloca os valores antes do pos no fim do array;
        for(int j=0; j<requestString.length ; j++){
            if (requestString[j] < this.initCilindro) resultados.add(requestString[j]);
        }                         
        
        requestString = new int[this.requestString.length+3];
        for(int j=0; j<requestString.length; j++){
            requestString[j] = resultados.get(j);
            System.out.println(requestString[j]);
        } 
        
        total = this.numCilindros - this.initCilindro + requestString[requestString.length-1];

        
        return total;
    }

    @Override
    public void printGraph(String filename) {
        int i;
        int y_axis = requestString.length * 10;
        
        XYSeries series = new XYSeries("CSCAN");
        
        /* Adiciona o pontos XY do gráfico de linhas. */
        series.add(y_axis, initCilindro);
        
        for(i=0;i<requestString.length;i++){
            series.add(y_axis-((i+1)*10), requestString[i]);
        }
        
        /* Adiciona a serie criada a um SeriesCollection. */
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        /* Gera o gráfico de linhas */
        JFreeChart chart = ChartFactory.createXYLineChart(
            /* Title */
            "CSCAN Scheduler Algorithm",
            /* Title x*/
            "",
            /* Title y */
            "Cilindro",
            dataset,
            /* Plot Orientation */
            PlotOrientation.HORIZONTAL,
            /* Show Legend */
            false,
            /* Use tooltips */
            false,
            /* Configure chart to generate URLs? */
            false
        );
        
        /* Configura a espessura da linha do gráfico  */
        XYPlot plot = chart.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        plot.setRenderer(renderer);
        
        try {
            ChartUtilities.saveChartAsJPEG(new File(filename), chart, 500, 300);
        } catch (IOException ex) {
            Logger.getLogger(CSCAN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}