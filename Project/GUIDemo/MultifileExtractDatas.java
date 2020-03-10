/**@Author Ramazan Yetismis
 * @Date 02.04.2019
 * @Description: Reading datas from the Fle_f object returning datas for multi graphs
 */

package Project.GUIDemo;

import Project.mainObjects.File_f;
import Project.mainObjects.Subsection;

import java.util.ArrayList;

public class MultifileExtractDatas {
    //This method returns 2 d array like flipped[2014,2015,2016,2017]
//                                   course[2014,2015,2016,2017] seven sections.
    public  static  double[][] givenCourseCodeAveragesOfAllSections(ArrayList<File_f> files){
//        ArrayList<File_f> x1=new ArrayList<>();

        double averagesOfAllSections[][]=new double[7][4];//0 index is for section 1 "1" is for section 2

//        for (int i = 0; i < files.size(); i++) {
//            File_f f=files.get(i);
//            if (code.equals(f.getDersKodu())){
//                x1.add(f);
//            }
//        }

        String sections[]={"Flipped Classroom","Course","Instructor","Labs/studios/recitations etc.","Teaching Assistant","Overall Evaluation","Course Learning Outcomes"};

        averagesOfAllSections[0]=givenCourseSection(files,sections[0]);
        averagesOfAllSections[1]=givenCourseSection(files,sections[1]);
        averagesOfAllSections[2]=givenCourseSection(files,sections[2]);
        averagesOfAllSections[3]=givenCourseSection(files,sections[3]);
        averagesOfAllSections[4]=givenCourseSection(files,sections[4]);
        averagesOfAllSections[5]=givenCourseSection(files,sections[5]);
        averagesOfAllSections[6]=givenCourseSection(files,sections[5]);

        return averagesOfAllSections;
    }





    //This method returns 1d array like   sectionX[2014,2015,2016,2017]
    public  static  double []  givenCourseSection(ArrayList<File_f> files,String section){
        ArrayList<File_f> x1=files;
        double average[]=new double[4];
//        for (int i = 0; i < files.size(); i++) {
//            File_f f=files.get(i);
//            if (f.getSect()==code ){
//                    x1.add(f);
//            }
//        }
        int div[]=new int[4];

        for (int i = 0; i < x1.size(); i++) {
            double total=0;
            int div1=0;

            File_f f=x1.get(i);
            for (int j=0;j<7;j++) {

                if (f.Sections[j].getText().equals(section)){
                    for (Subsection sb:f.Sections[j].subsections) {
                        total += sb.average;
//                    System.out.println(f.section[j].getText()+" total ="+total);
                        div1++;
                    }

                }
            }


            int index=(f.year-2)%4;
            div[index]+=f.getÖğrenciSayısı();
//            System.out.println("FOR GIVEN SECTION "+f.yaer+" section "+f.getSect()+"");
//            System.out.println("first "+ average[index]+"x"+f.getÖðrenciSayýsý());
            average[index]+=total/div1*f.getÖğrenciSayısı();
//            System.out.println( average[index]+"x"+f.getÖðrenciSayýsý());


        }
        for (int i = 0; i <average.length ; i++) {
//            System.out.println("total is "+average[i] +" div ="+div[i]) ;
            average[i]=average[i]/div[i];
//            System.out.println(average[i]);

        }


        return average;
    }
    // This method returns 1d array like   sectionX[2014,2015,2016,2017]
    public  static  double[] givenCourseSubSection(ArrayList<File_f> files,String subSection){
        ArrayList<File_f> x1=files;
        double average[]=new double[4];
//        for (int i = 0; i < files.size(); i++) {
//            File_f f=files.get(i);
//            if (f.getSect()==code ){
//                x1.add(f);
//            }
//        }
        int div[]=new int[4];

        for (int i = 0; i < x1.size(); i++) {

            File_f file = x1.get(i);

            for (int j = 0; j < 7; j++) {
                for (Subsection sb : file.Sections[j].subsections) {
                    if (sb.text.equals(subSection)){
                        int index=(file.year-2)%4;
                        div[index]+=file.getÖğrenciSayısı();
                        average[index]+=sb.average*file.getÖğrenciSayısı();
//                        System.out.println(average[index]+" Öğrenci Sayısı="+div[index]);
                    }
                }
            }
        }
        for (int i = 0; i <average.length ; i++) {
//            System.out.println("Total is "+average[i] +" Div ="+div[i]) ;
            average[i]=average[i]/div[i];
//            System.out.println(average[i]);
        }

        return average;
    }

}

