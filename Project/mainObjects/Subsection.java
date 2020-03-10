/**
 * @Author Muhammed Rahmetullah Kartal / Ramazan Yetişmiş
 * @Date 11.03.2019 / 21.03.2019
 * @Description: SubSection Object
 */


package Project.mainObjects;

public class Subsection {
    public String text;
    public double average;
    public double stddev;
    public double na;
    public double no;
    public double one;
    public double two;
    public double three;
    public double four;
    public double five;
    public double universityAverage;

    public Subsection(){}

    public Subsection(String arr[]){
        arr = new String[10];
        text = arr[0];
        average = Double.parseDouble(arr[1]);
        stddev = Double.parseDouble(arr[2]);
        na = Double.parseDouble(arr[3]);
        no = Double.parseDouble(arr[4]);
        one = Double.parseDouble(arr[5]);
        two = Double.parseDouble(arr[6]);
        three = Double.parseDouble(arr[7]);
        four = Double.parseDouble(arr[8]);
        five = Double.parseDouble(arr[9]);
        universityAverage = Double.parseDouble(arr[10]);
    }

}
