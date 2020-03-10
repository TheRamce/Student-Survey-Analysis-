/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 16.03.2019
 * @Description: BarChart elements and values object to use in BarCharts.
 */

package Project.barCharts;

public class barChartElVal {
    private String element;
    private double value;
    private String seriesName;

    barChartElVal(){}

    barChartElVal(String seriesName, String element, Number value){
        this.seriesName = seriesName;
        this.element = element;
        this.value = (double) value;
    }

    public double getValue() {
        return value;
    }

    public String getElement() {
        return element;
    }

    public String getSeriesName() { return seriesName; }

    public void setElement(String element) { this.element = element; }

    public void setValue(double value) { this.value = value; }

    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
}
