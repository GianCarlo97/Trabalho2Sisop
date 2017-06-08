/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.logging.Level;
import java.util.logging.Logger;


public class SSTF implements DiskScheduler{
    private int[] requestString;
    private int numCilindros;
    private int initCilindro;
    
    public SSTF(int[] requestString, int numCilindros, int initCilindro){
        this.requestString = requestString;
        this.numCilindros = numCilindros;
        this.initCilindro = initCilindro;
    }

    @Override
    public int serviceRequests() {
        int total = 0, i;
        
        /* Determine o número de cilindros percorridos pelo cabeçote de leitura
           para o algoritmo FCFS. 
           Observe que no algorithm FCFS o número de cilindros do disco não é 
           considerado (numCilindros)
        */
        int [] subs = new int[requestString.length];
        
        for(int j=0; j< requestString.length ; j++){
            if(initCilindro<requestString[j]){ 
                subs[j] = requestString[j] - initCilindro;
            }else{
                subs[j] = initCilindro - requestString[j];
            }
        }
        int swap;
        int swap2;
        for (int c = 0; c < subs.length; c++) {
            for (int d = 0; d < subs.length - c - 1; d++) {
                if (subs[d] > subs[d+1]) /* For descending order use < */
            {
                    swap       = subs[d];
                    subs[d]   = subs[d+1];
                    subs[d+1] = swap;
                    swap2       = requestString[d];
                    requestString[d]   = requestString[d+1];
                    requestString[d+1] = swap2;
          
            }
            }
        }

        
        
      
        total = abs(initCilindro - requestString[0]);
        
        for(i=0;i<requestString.length-1;i++){
            total += abs(requestString[i]-requestString[i+1]);
        }
        
        return total;
    }

    @Override
    public void printGraph(String filename) {
        int i;
        int y_axis = requestString.length * 10;
        
        XYSeries series = new XYSeries("FCFS");
        
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
            "FCFS Scheduler Algorithm",
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
            Logger.getLogger(SSTF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
