/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 16.03.2019
 * @Description: BarChart object by XChart Library
 */


package Project.barCharts;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

public class barChart {
    private int height;
    private int width;
    private String title;
    private String xAxisTitle;
    private String yAxisTitle;
    public ArrayList<barChartElVal> series ;

    public CategoryChart chart;


    public barChart(int width, int height, String title, String xAxisTitle, String yAxisTitle){
        this.width = width;
        this.height = height;
        this.title = title;
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;

        series = new ArrayList<barChartElVal>();
        chart = new CategoryChartBuilder().width(width).height(height).title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setAvailableSpaceFill(.96);
        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipType(Styler.ToolTipType.yLabels);
    }


    public void addElements(String seriesName ,ArrayList<String> xLabel, ArrayList<Double> yLabel ){
        for(int i = 0; i<xLabel.size();i++){
            series.add(new barChartElVal(seriesName,xLabel.get(i),yLabel.get(i)));
        }
        chart.addSeries(seriesName, xLabel, yLabel).setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);
    }

    //getters
    public CategoryChart getChart() { return chart; }

    public String getTitle() { return title; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public ArrayList<barChartElVal> getSeries() { return series; }

    public String getxAxisTitle() { return xAxisTitle; }

    public String getyAxisTitle() { return yAxisTitle; }


    //setters
    public void setTitle(String title) { this.title = title; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

    public void setChart(CategoryChart chart) { this.chart = chart; }

    public void setSeries(ArrayList<barChartElVal> series) { this.series = series; }

    public void setxAxisTitle(String xAxisTitle) { this.xAxisTitle = xAxisTitle; }

    public void setyAxisTitle(String yAxisTitle) { this.yAxisTitle = yAxisTitle; }
}
