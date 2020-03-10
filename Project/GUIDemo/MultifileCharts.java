/**@Author Ramazan Yetismis
 * @Date 02.04.2019
 * @Description: Reading datas from the Fle_f object and creating multi file graphs methods
 */


package Project.GUIDemo;

import Project.barCharts.barChart;
import Project.mainObjects.File_f;
import Project.pieCharts.pieChart;
import Project.radarCharts.radarChart;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.RadarChart;

import java.util.ArrayList;

import static Project.GUIDemo.Project.*;

public class MultifileCharts {

    public static ArrayList<File_f> getSelectedFilesByCourseCode(){
        ArrayList<File_f> files = new ArrayList<>();
        for (int i=0; i<multifileGoingToBeUsedInGraphs.size();i++){
            if (multifileGoingToBeUsedInGraphs.get(i).getDersKodu().equals(SelectedCourseCode)){
                files.add(multifileGoingToBeUsedInGraphs.get(i));
            }
        }
        return files;
    }

    public static CategoryChart createMultifileYearSectionGivenCourseCodeBarGraph(){
        ArrayList<File_f> selectedFilesByCourseCode = getSelectedFilesByCourseCode();
        double [][] allSectionAverages= MultifileExtractDatas.givenCourseCodeAveragesOfAllSections(selectedFilesByCourseCode);

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();


        for(int i=0; i<allSectionAverages.length; i++){
            if (SelectedYears.contains(2014)){
                yAxis2014.add(allSectionAverages[i][0]);
            }
            if (SelectedYears.contains(2015)){
                yAxis2015.add(allSectionAverages[i][1]);
            }
            if (SelectedYears.contains(2016)){
                yAxis2016.add(allSectionAverages[i][2]);
            }
            if (SelectedYears.contains(2017)){
                yAxis2017.add(allSectionAverages[i][3]);
            }

        }

        barChart chart = new barChart(500,500,"Averages","Sections","Values");
        if(SelectedYears.contains(2014)){
            chart.addElements("2014", SingleFileCharts.sectionNames,yAxis2014);
        }
        if(SelectedYears.contains(2015)){
            chart.addElements("2015", SingleFileCharts.sectionNames,yAxis2015);
        }
        if(SelectedYears.contains(2016)){
            chart.addElements("2016", SingleFileCharts.sectionNames,yAxis2016);
        }
        if(SelectedYears.contains(2017)){
            chart.addElements("2017", SingleFileCharts.sectionNames,yAxis2017);
        }

        return chart.getChart();
    }


    public static CategoryChart createMultifileYearSectionGivenBarGraph(){
        double [] allSectionAverages= MultifileExtractDatas.givenCourseSection(multifileGoingToBeUsedInGraphs,SelectedSection);
        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        name.add(SelectedSection);

            if (SelectedYears.contains(2014)){
                yAxis2014.add(allSectionAverages[0]);
            }
            if (SelectedYears.contains(2015)){
                yAxis2015.add(allSectionAverages[1]);
            }
            if (SelectedYears.contains(2016)){
                yAxis2016.add(allSectionAverages[2]);
            }
            if (SelectedYears.contains(2017)){
                yAxis2017.add(allSectionAverages[3]);
                }
                barChart chart = new barChart(500,500,SelectedSection,"Years","Averages");
        if(SelectedYears.contains(2014)){
            chart.addElements("2014",name,yAxis2014);
        }
        if(SelectedYears.contains(2015)){
            chart.addElements("2015",name,yAxis2015);
        }
        if(SelectedYears.contains(2016)){
            chart.addElements("2016",name,yAxis2016);
        }
        if(SelectedYears.contains(2017)){
            chart.addElements("2017",name,yAxis2017);
        }
        return  chart.getChart();
    }



    public static CategoryChart createMultifileYearSubSectionGivenBarGraph(){
        double [] allSectionAverages= MultifileExtractDatas.givenCourseSubSection(multifileGoingToBeUsedInGraphs,SelectedSubSection);
        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        name.add(SelectedSubSection);

        if (SelectedYears.contains(2014)){
            yAxis2014.add(allSectionAverages[0]);
        }
        if (SelectedYears.contains(2015)){
            yAxis2015.add(allSectionAverages[1]);
        }
        if (SelectedYears.contains(2016)){
            yAxis2016.add(allSectionAverages[2]);
        }
        if (SelectedYears.contains(2017)){
            yAxis2017.add(allSectionAverages[3]);
        }
        barChart chart = new barChart(500,500,SelectedSubSection,"Years","Averages");
        if(SelectedYears.contains(2014)){
            chart.addElements("2014",name,yAxis2014);
        }
        if(SelectedYears.contains(2015)){
            chart.addElements("2015",name,yAxis2015);
        }
        if(SelectedYears.contains(2016)){
            chart.addElements("2016",name,yAxis2016);
        }
        if(SelectedYears.contains(2017)){
            chart.addElements("2017",name,yAxis2017);
        }
        return  chart.getChart();
    }



    // ###################################################################################################################
    // ###################################################################################################################
    // ###################################################################################################################
    // ###################################################################################################################
    // ###################################################################################################################


    public static RadarChart createMultifileYearSectionGivenCourseCodeRadarGraph(){
        ArrayList<File_f> selectedFilesByCourseCode = getSelectedFilesByCourseCode();
        double [][] allSectionAverages= MultifileExtractDatas.givenCourseCodeAveragesOfAllSections(selectedFilesByCourseCode);

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();


        for(int i=0; i<allSectionAverages.length; i++){
            if (SelectedYears.contains(2014)){
                yAxis2014.add(allSectionAverages[i][0]/5);
            }
            if (SelectedYears.contains(2015)){
                yAxis2015.add(allSectionAverages[i][1]/5);
            }
            if (SelectedYears.contains(2016)){
                yAxis2016.add(allSectionAverages[i][2]/5);
            }
            if (SelectedYears.contains(2017)){
                yAxis2017.add(allSectionAverages[i][3]/5);
            }

        }

        radarChart chart = new radarChart(500,500,SelectedSection);
        chart.setLabelNames(SingleFileCharts.sectionNames);
        if(SelectedYears.contains(2014)){
            chart.addElements("2014",yAxis2014);
        }
        if(SelectedYears.contains(2015)){
            chart.addElements("2015",yAxis2015);
        }
        if(SelectedYears.contains(2016)){
            chart.addElements("2016",yAxis2016);
        }
        if(SelectedYears.contains(2017)){
            chart.addElements("2017",yAxis2017);
        }

        return chart.getChart();
    }





    public static RadarChart createMultifileYearSectionGivenRadarGraph(){
        double [] allSectionAverages= MultifileExtractDatas.givenCourseSection(multifileGoingToBeUsedInGraphs,SelectedSection);
        ArrayList<Double> yAxis2014 = new ArrayList<>();

        ArrayList<String> name = new ArrayList<>();


        if (SelectedYears.contains(2014)){
            yAxis2014.add(allSectionAverages[0]/5);

            name.add("2014");
            System.out.println(allSectionAverages[0]/5);
        }
        if (SelectedYears.contains(2015)){
            yAxis2014.add(allSectionAverages[1]/5);
            name.add("2015");
            System.out.println(allSectionAverages[1]/5);
        }
        if (SelectedYears.contains(2016)){
            yAxis2014.add(allSectionAverages[2]/5);
            name.add("2016");
            System.out.println(allSectionAverages[2]/5);
        }
        if (SelectedYears.contains(2017)){
            yAxis2014.add(allSectionAverages[3]/5);
            name.add("2017");
            System.out.println(allSectionAverages[3]/5);
        }
        radarChart chart = new radarChart(500,500,SelectedSection);
        chart.setLabelNames(name);

            chart.addElements(SelectedSection, yAxis2014);

        return chart.getChart();


    }





    public static RadarChart createMultifileYearSubSectionGivenRadarGraph(){
        double [] allSectionAverages= MultifileExtractDatas.givenCourseSubSection(multifileGoingToBeUsedInGraphs,SelectedSubSection);
        ArrayList<Double> yAxis2014 = new ArrayList<>();

        ArrayList<String> name = new ArrayList<>();

        if (SelectedYears.contains(2014)){
            yAxis2014.add(allSectionAverages[0]/5);

            name.add("2014");
//            System.out.println(allSectionAverages[0]/5);
        }
        if (SelectedYears.contains(2015)){
            yAxis2014.add(allSectionAverages[1]/5);
            name.add("2015");
//            System.out.println(allSectionAverages[1]/5);
        }
        if (SelectedYears.contains(2016)){
            yAxis2014.add(allSectionAverages[2]/5);
            name.add("2016");
//            System.out.println(allSectionAverages[2]/5);
        }
        if (SelectedYears.contains(2017)){
            yAxis2014.add(allSectionAverages[3]/5);
            name.add("2017");
//            System.out.println(allSectionAverages[3]/5);
        }
        radarChart chart = new radarChart(500,500,SelectedSubSection);
        chart.setLabelNames(name);

        chart.addElements(SelectedSubSection, yAxis2014);

        return chart.getChart();


    }


    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################

    public static PieChart createMultifileYearSectionGivenSectionPieGraph() {
        double[] allSectionAverages = MultifileExtractDatas.givenCourseSection(multifileGoingToBeUsedInGraphs, SelectedSection);
        ArrayList<String> xAxis2014 = new ArrayList<>();
        ArrayList<String> xAxis2015 = new ArrayList<>();
        ArrayList<String> xAxis2016 = new ArrayList<>();
        ArrayList<String> xAxis2017 = new ArrayList<>();

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();



        if (SelectedYears.contains(2014)){
            yAxis2014.add(allSectionAverages[0]);
            xAxis2014.add("2014");
        }
        if (SelectedYears.contains(2015)){
            yAxis2015.add(allSectionAverages[1]);
            xAxis2015.add("2015");
        }
        if (SelectedYears.contains(2016)){
            yAxis2016.add(allSectionAverages[2]);
            xAxis2016.add("2016");
        }
        if (SelectedYears.contains(2017)){
            yAxis2017.add(allSectionAverages[3]);
            xAxis2017.add("2017");
        }


        pieChart chart0= new pieChart(500,450,SelectedSection);

        if (SelectedYears.contains(2014)) {
            chart0.addElements(xAxis2014,yAxis2014 );
        }if (SelectedYears.contains(2015)){
            chart0.addElements(xAxis2015,yAxis2015 );        }
        if (SelectedYears.contains(2016)){
            chart0.addElements(xAxis2016,yAxis2016 );        }
        if (SelectedYears.contains(2017)){
            chart0.addElements(xAxis2017,yAxis2017 );        }

        return chart0.getChart();
    }

    public static PieChart createMultifileYearSubSectionGivenPieGraph(){
        double [] allSectionAverages= MultifileExtractDatas.givenCourseSubSection(multifileGoingToBeUsedInGraphs,SelectedSubSection);
        ArrayList<String> xAxis2014 = new ArrayList<>();
        ArrayList<String> xAxis2015 = new ArrayList<>();
        ArrayList<String> xAxis2016 = new ArrayList<>();
        ArrayList<String> xAxis2017 = new ArrayList<>();

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();



        if (SelectedYears.contains(2014)){
            yAxis2014.add(allSectionAverages[0]);
            xAxis2014.add("2014");
        }
        if (SelectedYears.contains(2015)){
            yAxis2015.add(allSectionAverages[1]);
            xAxis2015.add("2015");
        }
        if (SelectedYears.contains(2016)){
            yAxis2016.add(allSectionAverages[2]);
            xAxis2016.add("2016");
        }
        if (SelectedYears.contains(2017)){
            yAxis2017.add(allSectionAverages[3]);
            xAxis2017.add("2017");
        }


        pieChart chart0= new pieChart(500,450,SelectedSubSection);

        if (SelectedYears.contains(2014)) {
            chart0.addElements(xAxis2014,yAxis2014 );
        }if (SelectedYears.contains(2015)){
            chart0.addElements(xAxis2015,yAxis2015 );        }
        if (SelectedYears.contains(2016)){
            chart0.addElements(xAxis2016,yAxis2016 );        }
        if (SelectedYears.contains(2017)){
            chart0.addElements(xAxis2017,yAxis2017 );        }
        return chart0.getChart();
    }


    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################
    // ##############################################################################################################################


    public static ArrayList<barChart> createBarChartArrayForAllSectionBarGraph(){
        ArrayList<barChart> multiCharts = new ArrayList<>();

        ArrayList<File_f> selectedFilesByCourseCode = getSelectedFilesByCourseCode();
        double[][] allSectionAverages = MultifileExtractDatas.givenCourseCodeAveragesOfAllSections(selectedFilesByCourseCode);

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();


        for (int i = 0; i < allSectionAverages.length; i++) {
            if (SelectedYears.contains(2014)) {
                yAxis2014.add(allSectionAverages[i][0]);
            }
            if (SelectedYears.contains(2015)) {
                yAxis2015.add(allSectionAverages[i][1]);
            }
            if (SelectedYears.contains(2016)) {
                yAxis2016.add(allSectionAverages[i][2]);
            }
            if (SelectedYears.contains(2017)) {
                yAxis2017.add(allSectionAverages[i][3]);
            }

        }


        if (SelectedYears.contains(2014)) {
            barChart chart1 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart1.addElements("2014", SingleFileCharts.sectionNames, yAxis2014);
            multiCharts.add(chart1);
        }
        if (SelectedYears.contains(2015)) {
            barChart chart2 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart2.addElements("2015", SingleFileCharts.sectionNames, yAxis2015);
            multiCharts.add(chart2);
        }
        if (SelectedYears.contains(2016)) {
            barChart chart3 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart3.addElements("2016", SingleFileCharts.sectionNames, yAxis2016);
            multiCharts.add(chart3);
        }
        if (SelectedYears.contains(2017)) {
            barChart chart4 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart4.addElements("2017", SingleFileCharts.sectionNames, yAxis2017);
            multiCharts.add(chart4);
        }
        return multiCharts;
    }

    public static ArrayList<barChart> createBarChartArrayForGivenSection(){

        ArrayList<barChart> multiCharts= new ArrayList<>();
        double[] allSectionAverages = MultifileExtractDatas.givenCourseSection(multifileGoingToBeUsedInGraphs, SelectedSection);
        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        name.add(SelectedSubSection);

        if (SelectedYears.contains(2014)) {
            yAxis2014.add(allSectionAverages[0]);
            barChart chart1 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart1.addElements("2014",name,yAxis2014);
            multiCharts.add(chart1);

        }
        if (SelectedYears.contains(2015)) {
            yAxis2015.add(allSectionAverages[1]);
            barChart chart2 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart2.addElements("2015",name,yAxis2015);
            multiCharts.add(chart2);
        }
        if (SelectedYears.contains(2016)) {
            yAxis2016.add(allSectionAverages[2]);
            barChart chart3 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart3.addElements("2016",name,yAxis2016);
            multiCharts.add(chart3);
        }
        if (SelectedYears.contains(2017)) {
            yAxis2017.add(allSectionAverages[3]);
            barChart chart4 = new barChart(500, 500, "title", "xAxis", "yAxis");
            chart4.addElements("2017",name,yAxis2017);
            multiCharts.add(chart4);
        }

        return multiCharts;
    }

    public static ArrayList<radarChart> createRadarChartArrayForAllSection(){
        ArrayList<File_f> selectedFilesByCourseCode = getSelectedFilesByCourseCode();
        double [][] allSectionAverages= MultifileExtractDatas.givenCourseCodeAveragesOfAllSections(selectedFilesByCourseCode);
        ArrayList<radarChart> multiCharts= new ArrayList<>();

        ArrayList<Double> yAxis2014 = new ArrayList<>();
        ArrayList<Double> yAxis2015 = new ArrayList<>();
        ArrayList<Double> yAxis2016 = new ArrayList<>();
        ArrayList<Double> yAxis2017 = new ArrayList<>();


        for(int i=0; i<allSectionAverages.length; i++){
            if (SelectedYears.contains(2014)){
                yAxis2014.add(allSectionAverages[i][0]/5);

            }
            if (SelectedYears.contains(2015)){
                yAxis2015.add(allSectionAverages[i][1]/5);
            }
            if (SelectedYears.contains(2016)){
                yAxis2016.add(allSectionAverages[i][2]/5);
            }
            if (SelectedYears.contains(2017)){
                yAxis2017.add(allSectionAverages[i][3]/5);
            }

        }

        if(SelectedYears.contains(2014)){
            radarChart chart1 = new radarChart(500,500,"title");
            chart1.setLabelNames(SingleFileCharts.sectionNames);
            chart1.addElements("2014",yAxis2014);
            multiCharts.add(chart1);

        }
        if(SelectedYears.contains(2015)){
            radarChart chart2 = new radarChart(500,500,"title");
            chart2.setLabelNames(SingleFileCharts.sectionNames);
            chart2.addElements("2014",yAxis2014);
            multiCharts.add(chart2);

        }
        if(SelectedYears.contains(2016)){
            radarChart chart3 = new radarChart(500,500,"title");
            chart3.setLabelNames(SingleFileCharts.sectionNames);
            chart3.addElements("2014",yAxis2014);
            multiCharts.add(chart3);

        }
        if(SelectedYears.contains(2017)){
            radarChart chart4 = new radarChart(500,500,"title");
            chart4.setLabelNames(SingleFileCharts.sectionNames);
            chart4.addElements("2014",yAxis2014);
            multiCharts.add(chart4);

        }
        return multiCharts;
    }
}

