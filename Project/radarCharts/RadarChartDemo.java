/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 17.03.2019
 * @Description: Simple Demonstration for RadarChart
 */

package Project.radarCharts;

import org.knowm.xchart.RadarChart;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;

public class RadarChartDemo {
    public static RadarChart createRadarChart(int width,int height,String title) {
        // Create Chart
        radarChart chart = new radarChart(width,height,title);

        // Series
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Sales");
        parameters.add("Marketing");
        parameters.add(String.valueOf(2014));

        ArrayList<Double> values = new ArrayList<>();
        values.add((double) 0.33);
        values.add((double) 0.46);
        values.add((double) 0.77);
        ArrayList<Double> value = new ArrayList<>();
        value.add((double) 0.33);
        value.add((double) 0.26);
        value.add((double) 0.77);
        String s=String.valueOf(2014);
        System.out.println(s);
        chart.setLabelNames(parameters);
        chart.addElements("University Averages", values);
        chart.addElements(s, value);



        return chart.getChart();
    }

    public static void main(String[] args) {
        RadarChart chart = createRadarChart(300,300,"University Averagess");
        new SwingWrapper<RadarChart>(chart).displayChart();
    }
}
