/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 16.03.2019
 * @Description: Simple demonstration for PieChart
 */


package Project.pieCharts;

import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;

public class PieChartDemo  {

    public static PieChart createPieChart(int width, int height, String text){
        pieChart chart1 = new pieChart(width,height,text);
        chart1.addSingleElement("Gold", 35.7);
        chart1.addSingleElement("Plat", 64.3);
        return chart1.getChart();
    }

    public static void main(String[] args) {

        PieChart chart = createPieChart(250,250,"Hello");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Create and set up the window.
                JFrame frame = new JFrame("SingleFileCharts");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // chart
                JPanel chartPanel = new XChartPanel<PieChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);

                // label
//                JLabel label = new JLabel("Blah blah blah.", SwingConstants.CENTER);
//                frame.add(label, BorderLayout.SOUTH);

                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}