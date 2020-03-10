/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 17.03.2019
 * @Description: RadarChart object by XChart Library
 */


package Project.radarCharts;

import org.knowm.xchart.RadarChart;
import org.knowm.xchart.RadarChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

public class radarChart {
    private int height;
    private int width;
    private String title;
    public ArrayList<Double> series;
    public ArrayList<String> labelNames;

    public RadarChart chart;

    public radarChart(int width, int height, String title) {
        this.width=width;
        this.height = height;
        this.title = title;
        chart = new RadarChartBuilder().width(width).height(height).title(title).build();
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
    }

    public void setLabelNames(ArrayList<String> labelNames) {
        this.labelNames = labelNames;
        String[] tempNames = new String[labelNames.size()];
        for (int i = 0; i < labelNames.size(); i++) {
            tempNames[i] = labelNames.get(i);
        }
        try{
            chart.setVariableLabels(tempNames);
            chart.setVariableLabels(tempNames);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void addElements(String seriesName, ArrayList<Double> series) {
        this.series = series;
        double[] tempSeries = new double[series.size()];
        for (int i = 0; i < series.size(); i++) {
            tempSeries[i] = series.get(i);
        }
        chart.addSeries(seriesName, tempSeries);
    }

    //setters
    public void setSeries(ArrayList<Double> series) { this.series = series; }

    public void setChart(RadarChart chart) { this.chart = chart; }

    public void setHeight(int height) { this.height = height; }

    public void setWidth(int width) { this.width = width; }

    public void setTitle(String title) { this.title = title; }


    //getters
    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public String getTitle() { return title; }

    public ArrayList<Double> getSeries() { return series; }

    public ArrayList<String> getLabelNames() { return labelNames; }

    public RadarChart getChart() { return chart; }
}

