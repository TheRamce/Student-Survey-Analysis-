/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 16.03.2019
 * @Description: Simple Demonstration of Barchart
 */


package Project.barCharts;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;

public class BarChartDemo {

    public static CategoryChart createBarChart(int width, int height, String title, String xAxisTitle, String yAxisTitle) {

        barChart chart = new barChart(width, height, title, xAxisTitle, yAxisTitle);
        ArrayList<String> x = new ArrayList<>();
        x.add("A");
        x.add("B");
        x.add("B");
        ArrayList<Double> y = new ArrayList<>();
        y.add((double) 11);
        y.add((double) 22);
        y.add((double) 32);
        ArrayList<Double> t = new ArrayList<>();
        t.add((double) 33);
        t.add((double) 44);
        t.add((double) 55);


        chart.addElements("Yo",x,y);
        chart.addElements("World",x,t);

        return chart.getChart();
    }

    public static void main(String[] args){
        CategoryChart chart = createBarChart(300,300,"Try","Hope","Works");
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

}
