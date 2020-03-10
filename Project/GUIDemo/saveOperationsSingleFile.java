/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 31.03.2019
 * @Description: Saving graphs to PDF.
 */


package Project.GUIDemo;

import Project.mainObjects.File_f;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class saveOperationsSingleFile {

    public static String currentPath = ""; //Global variable for current path.

    //This method saves the graphs to PDF file.
    //Allows user to chose save directory.
    public static void saveSingleFileToPDF(File_f file) throws IOException {
        //Asking for save directory.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to select save directory?");
        alert.setTitle("Question");
        alert.showAndWait();

        //Chosing the save path.
        Path currentRelativePath = Paths.get("");
        if (alert.getResult() == ButtonType.OK){
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Path");
            File directory = directoryChooser.showDialog(null);
            currentRelativePath = Paths.get(directory.getPath());
        }
        currentPath= currentRelativePath.toAbsolutePath().toString();


        ArrayList<CategoryChart> allSectionsVoteBCS = SingleFileCharts.createAllSectionsVoteBCS(file);
        ArrayList<PieChart> allSectionsVotePCS = SingleFileCharts.createAllSectionsVotePCS(file);

        ArrayList<CategoryChart> allSectionsAveragesBCS = SingleFileCharts.createAllSectionsAverageBCS(file);
        ArrayList<PieChart> allSectionsAveragesPCS = SingleFileCharts.createAllSectionsAveragePCS(file);


       // CourseTableForGUI name, section,  term, year, instructor

        //Single file save operations, it firstly saves the graphs on PNG format then adds that PNGs to PDF file.
//        if (usableArray.size()==1){

        Document document = new Document();

        String output = currentPath+"\\"+file.getName() +" Analysis.pdf";

        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = (PdfWriter) PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            String path;

            String[] nameParts = file.getName().split("x")[0].split("_");
            String term = nameParts[4];

            //Adding the first page elements.
            document.add(new Paragraph("Course Code: " +file.getDersKodu()));
            document.add(new Paragraph("Section: " +file.getSect()));
            document.add(new Paragraph("Year: " +file.year));
            document.add(new Paragraph("Term: " +term));
            document.add(new Paragraph("Instructor: "+file.getÖğretimElemanı()));
            document.newPage();


            //Creating figure 1 page.
            path = String.format("%s\\Figure1.png", currentPath);
            document.add(new Paragraph("Figure1")); //Add figure name
            document.add(new Paragraph("PieChart Answering")); //Add figure description
            BitmapEncoder.saveBitmap(SingleFileCharts.createCevaplamaOraniPieChart(file), path, BitmapEncoder.BitmapFormat.PNG); //Create figure as PNG.
            document.add(com.itextpdf.text.Image.getInstance(path));// Add figure
            document.newPage(); //Go to new page

            //Creating figure 2 page.
            path = String.format("%s\\Figure2.png", currentPath);
            document.add(new Paragraph("Figure2"));
            document.add(new Paragraph("PieChart Section-Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createSectionOrtalamariPieChart(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            //Creating figure 3 page.
            path = String.format("%s\\Figure3.png", currentPath);
            document.add(new Paragraph("Figure3"));
            document.add(new Paragraph("BarChart Section-Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createSectionOrtalamalariBarChart(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            //Creating multple figures for barcharts of subsection votes.
            for (int i = 0; i<allSectionsVoteBCS.size(); i++) {

                path = String.format("%s\\Figure%d.png", currentPath, i+4);
                document.add(new Paragraph("Figure"+(i+4)));
                document.add(new Paragraph("BarChart Subsections of"+allSectionsVoteBCS.get(i).getTitle()));
                BitmapEncoder.saveBitmap(allSectionsVoteBCS.get(i), path, BitmapEncoder.BitmapFormat.PNG);
                document.add(com.itextpdf.text.Image.getInstance(path));
                document.newPage();
            }
            //Creating multple figures for piecharts of subsection votes.
            for (int i = 0; i<allSectionsVoteBCS.size(); i++) {
                path = String.format("%s\\Figure%d.png", currentPath, i+4+allSectionsVoteBCS.size());
                document.add(new Paragraph("Figure"+(i+4+allSectionsVoteBCS.size())));
                document.add(new Paragraph("PieChart Subsections of"+allSectionsVoteBCS.get(i).getTitle()));
                BitmapEncoder.saveBitmap(allSectionsVotePCS.get(i), path, BitmapEncoder.BitmapFormat.PNG);
                document.add(com.itextpdf.text.Image.getInstance(path));
                document.newPage();
            }

            path = String.format("%s\\Figure%d.png", currentPath, (4+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())); //this is for creating the figure name.
            document.add(new Paragraph("Figure"+(4+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("RadarChart All Subsections-Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createAllSubsectionAveragesRC(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();


            path = String.format("%s\\Figure%d.png", currentPath, (5+allSectionsVoteBCS.size()+allSectionsVoteBCS.size()));
            document.add(new Paragraph("Figure"+(5+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("RadarChart All Subsections-Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createAllSubsectionAveragesUniAveragesRC(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            path = String.format("%s\\Figure%d.png", currentPath, (6+allSectionsVoteBCS.size()+allSectionsVoteBCS.size()));
            document.add(new Paragraph("Figure"+(6+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("BarChart Subsection-Averages,University Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createAllSubsectionAveragesUniAveragesBC(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            path = String.format("%s\\Figure%d.png", currentPath, (7+allSectionsVoteBCS.size()+allSectionsVoteBCS.size()));
            document.add(new Paragraph("Figure"+(7+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("RadarChart Section Averages"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createSectionOrtalamalariRadarChart(file),path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            path = String.format("%s\\Figure%d.png", currentPath, (8+allSectionsVoteBCS.size()+allSectionsVoteBCS.size()));
            document.add(new Paragraph("Figure"+(8+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("BarChart Votes-Section"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createScoringForAllSectionsBarChart(file), path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            path = String.format("%s\\Figure%d.png", currentPath, (9+allSectionsVoteBCS.size()+allSectionsVoteBCS.size()));
            document.add(new Paragraph("Figure"+(9+allSectionsVoteBCS.size()+allSectionsVoteBCS.size())));
            document.add(new Paragraph("RadarChart Votes-Section"));
            BitmapEncoder.saveBitmap(SingleFileCharts.createScoringOfLearningOutComesRadarChart(file),path, BitmapEncoder.BitmapFormat.PNG);
            document.add(com.itextpdf.text.Image.getInstance(path));
            document.newPage();

            //Creating multple figures for barcharts of subsection averages.
            for (int i = 0; i<allSectionsAveragesBCS.size(); i++) {

                path = String.format("%s\\Figure%d.png", currentPath, i+9+allSectionsAveragesBCS.size()+allSectionsAveragesBCS.size());
                document.add(new Paragraph("Figure"+(i+9+allSectionsAveragesBCS.size()+allSectionsAveragesBCS.size())));
                document.add(new Paragraph("BarChart Subsections of "+allSectionsAveragesBCS.get(i).getTitle()));
                BitmapEncoder.saveBitmap(allSectionsAveragesBCS.get(i), path, BitmapEncoder.BitmapFormat.PNG);
                document.add(com.itextpdf.text.Image.getInstance(path));
                document.newPage();
            }

            //Creating multple figures for piecharts of subsection averages.
            for (int i = 0; i<allSectionsAveragesBCS.size(); i++) {
                path = String.format("%s\\Figure%d.png", currentPath, i+9+allSectionsAveragesBCS.size()+allSectionsAveragesBCS.size()+allSectionsAveragesBCS.size());
                document.add(new Paragraph("Figure"+(i+9+allSectionsAveragesBCS.size()+allSectionsAveragesBCS.size())));
                document.add(new Paragraph("PieChart Subsections of "+allSectionsAveragesBCS.get(i).getTitle()));
                BitmapEncoder.saveBitmap(allSectionsAveragesPCS.get(i), path, BitmapEncoder.BitmapFormat.PNG);
                document.add(com.itextpdf.text.Image.getInstance(path));
                document.newPage();
            }

            //Adding the comments to PDF.
            document.add(new Paragraph("Comments-"));
            for (String comment:file.comments){
                document.add(new Paragraph(comment));
            }

            document.close();

            writer.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        }

//        else{
        //multifile grafikleri
//        }

    }
}
