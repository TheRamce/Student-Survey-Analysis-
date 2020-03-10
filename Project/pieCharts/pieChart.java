/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 16.03.2019
 * @Description: PieChart object by XChart Library
 */


package Project.pieCharts;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;
import java.util.ArrayList;

public class pieChart {
    private int height;
    private int width;
    private String title;
    public ArrayList<pieChartElVal> series;
    public PieChart chart;


    public pieChart(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        series = new ArrayList<>();
        chart = new PieChartBuilder().width(width).height(height).title(title).build();
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
    }

    public void addSingleElement(String element, double value){
        pieChartElVal newElement = new pieChartElVal(element, value);
        series.add(newElement);
        chart.addSeries(newElement.getElement(), (Number) newElement.getValue());
    }

    public void addElements(ArrayList<String> elements, ArrayList<Double> values){
        for(int i =0; i<elements.size(); i++){
            addSingleElement(elements.get(i),values.get(i));
        }
    }

    // Getter methods.
    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public String getTitle() { return title; }

    public ArrayList<pieChartElVal> getSeries() { return series; }

    public pieChartElVal getElement(int i){ return series.get(i); }

    public PieChart getChart() { return chart; }

    //Setter methods.
    public void setHeight(int height) { this.height = height; }

    public void setWidth(int width) { this.width = width; }

    public void setTitle(String title) { this.title = title; }
}
