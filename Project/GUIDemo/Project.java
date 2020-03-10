/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 02.04.2019
 * @Description: Main program for GUI
 */


package Project.GUIDemo;

import Project.mainObjects.File_f;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


public class Project extends Application implements Initializable {

    //JavaFX Screen Start
    public void start(Stage stage) throws Exception {
        Parent root =  FXMLLoader.load(getClass().getResource("firstScreenReal.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Student Survey Analysis");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }

    //Global variables to store file names.
    public static List<File> filesArray = new LinkedList<>(); //ArrayList for chosing pop-up screen.
    public static ArrayList<File> usableArray; //ArrayList for just accepted typed files.
    public static ArrayList<File_f> multifileGoingToBeUsedInGraphs; //File_f object casted usableArray.

    //Global variables to get selected items.
    public static String SelectedCourseCode = "";
    public static String SelectedSection = "";
    public static String SelectedSubSection = "";
    public static Set<Integer> SelectedYears;

    //Class variables for Multiple file save operations.
    public Path currentPathMF = null;
    public String outputFileNameMF = "";
    public String outputFigureNameMF;
    public int outputFigureCounterMF=0;
    public int outputSaveCounterMF=1;
    public Document document;
    public PdfWriter writer;


    //FXML CourseTableForGUI Table elements for GUI (top-right table)
    @FXML private TableView<CourseTableForGUI> courseTable;
    @FXML public TableColumn<CourseTableForGUI, String> courseCode;
    @FXML public TableColumn<CourseTableForGUI,String> tableSection;
    @FXML public TableColumn<CourseTableForGUI,String> year;
    @FXML public TableColumn<CourseTableForGUI,String> term;

    //FXML Comment Table elements for GUI (bottom-left table)
    @FXML private TableView<CommentsTableForGUI> commentTable;
    @FXML public TableColumn<CommentsTableForGUI,String> comment;
    @FXML public TableColumn<CommentsTableForGUI,String> userAddedComment;

    //FXML Radio button elements for GUI, setting label to path when clicked etc.
    @FXML public RadioButton enableSavePathText;
    @FXML public Label savePathText;
    @FXML public Label savedText;

    //FXML ListView of Years
    @FXML public ListView listView;

    //FXML ChoiceBox of CourseCode
    @FXML public ChoiceBox choiceBoxCourseCodesBC;
    @FXML public ChoiceBox choiceBoxSectionsBC;
    @FXML public ChoiceBox choiceBoxSubSectionsBC;

    //Text for visible section or subsection
    @FXML public Label sectionsText;
    @FXML public Label subsectionsText;

    //Buttons for choosing between section or subsection and for visibleness
    @FXML private RadioButton enableSectionSelect;
    @FXML private RadioButton enableSubSectionSelect;
    public ToggleGroup enableSOrSubSelectToggleGroup = new ToggleGroup();


    //Initializing GUI FXML elements.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up table columns
        courseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tableSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        term.setCellValueFactory(new PropertyValueFactory<>("term"));

        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        userAddedComment.setCellValueFactory(new PropertyValueFactory<>("userAddedComment"));

        userAddedComment.setCellFactory(TextFieldTableCell.forTableColumn());
        commentTable.setEditable(true); //To edit comment table.

        courseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //To select single file from course table.

        savePathText.setText("");
        savedText.setText("");

        sectionsText.setText("Sections");
        subsectionsText.setText("Subsections");

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //For selecting multiple years on multiple file operations.

        this.enableSectionSelect.setToggleGroup(enableSOrSubSelectToggleGroup);
        this.enableSubSectionSelect.setToggleGroup(enableSOrSubSelectToggleGroup);

    }


    //Selects course code to use it in other sections.
    public void selectCourseButtonPushed(ActionEvent event){
        SelectedCourseCode=getSelectedCourseCode();

        //If the course on multiple is selected ALL COURSES set visible usable things on ALL COURSES.
        if (SelectedCourseCode.equals("ALL COURSES")){
            choiceBoxSectionsBC.setVisible(true);
            choiceBoxSubSectionsBC.setVisible(true);
            sectionsText.setVisible(true);
            subsectionsText.setVisible(true);
            enableSectionSelect.setVisible(true);
            enableSubSectionSelect.setVisible(true);
        }
        //Else set them invisible.
        else{
            choiceBoxSectionsBC.setVisible(false);
            choiceBoxSubSectionsBC.setVisible(false);
            sectionsText.setVisible(false);
            subsectionsText.setVisible(false);
            enableSectionSelect.setVisible(false);
            enableSubSectionSelect.setVisible(false);
        }

        //Filling the years listview.
        ObservableList<Integer> yearsBC = FXCollections.observableArrayList(getYearsOfUsableArray(SelectedCourseCode));
        listView.setItems(yearsBC);

        //Filling the Sections choicebox
        ObservableList<String> sectionsBC = FXCollections.observableArrayList(getSectionsOfCourseCode(SelectedCourseCode));
        choiceBoxSectionsBC.setItems(sectionsBC);

        //Filling the SubSections choicebox
        ObservableList<String> subsectionsBC = FXCollections.observableArrayList(getsubsectionsOfCourseCode(SelectedCourseCode));
        choiceBoxSubSectionsBC.setItems(subsectionsBC);
    }


//#############################################################################################################################

    /*
    When save multifile button pushed ask for choose a save directory
    then create a pdf file on selected directory and open it and its writer
     */
    public void saveMFButtonPushed(ActionEvent event) throws FileNotFoundException, DocumentException {
        DirectoryChooser directoryChooser = new DirectoryChooser(); //Directorychooser object
        directoryChooser.setTitle("Select Folder");
        File directory = directoryChooser.showDialog(null); //Pop up a screen for selecting save directory.

        //Setting the path of output pdf
        currentPathMF = Paths.get(directory.getPath());
        outputFileNameMF = currentPathMF.toAbsolutePath().toString()+"\\MultifileOutputs"+outputSaveCounterMF+".pdf";

        document = new Document(); //creating a new document
        FileOutputStream fos = new FileOutputStream(outputFileNameMF); //Giving the output stream to document

        writer = (PdfWriter) PdfWriter.getInstance(document, fos); //Opening a pdfwriter for that output document

        //Opening the document and writer .
        document.open();
        writer.open();
    }

    //If user presses end save operations button close the writer and close the document.
    public void saveEndButtonPushed(ActionEvent event){
        try{
            writer.close();
        }
        catch (Exception ex){ }
        document.close();
        outputSaveCounterMF++; //This is for if user wants to create a new output file after ending save it reserves a new name for new output file.
    }


    //Makes ready the parameters BarChart Multifile.
    public void drawBarChartButtonPushed(ActionEvent event) throws IOException, DocumentException {

        //Setting the selected items.
        try{
            SelectedYears = getSelectedYears();
        }
        catch (Exception ex){
        }
        try{
            SelectedSubSection = getSelectedSubSection();
        }
        catch (Exception ex){
        }
        try{
            SelectedSection = getSelectedSection();
        }
        catch (Exception ex){
        }
        SelectedCourseCode=getSelectedCourseCode();



        // Create and set up the window.
        JFrame frame = new JFrame("Chart");
        frame.setLayout(new BorderLayout());

        // if selected course code is not ALL COURSES
        if (!SelectedCourseCode.equals("ALL COURSES")) {

            //Create given section name barchart.
            CategoryChart barChart = MultifileCharts.createMultifileYearSectionGivenCourseCodeBarGraph();

            //Create a panel to pop up barchart on screen.
            JPanel chartPanel = new XChartPanel<CategoryChart>(barChart); //This is a Swing operation not a JavaFX.

            try{
                if (SelectedYears.size()==0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select year(s).");
                    alert.showAndWait();
                }
                else{
                    outputFigureCounterMF++; //This is for figure names Figure(outputFigureCounterMF), Figure(outputFigureCounterMF+1) etc.
                    outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png"; //Creating the path for Figure name.

                    document.add(new Paragraph("Figure"+outputFigureCounterMF)); //Adding the figure name to PDF to find it on PDF File.
                    //Making year names to a string to add PDF.
                    String yearsToString="";
                    for (int x: SelectedYears){
                        yearsToString = yearsToString + ""+x + ", ";
                    }

                    //Adding chart name for figure definition.
                    document.add(new Paragraph("BarChart Section Averages-" + SelectedCourseCode + " on " + yearsToString + "years"));
                    BitmapEncoder.saveBitmap(barChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG); //Saving figure as a PNG file.
                    document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF)); //Adding the PNG file to PDF.
                    document.newPage(); //Moving throught to new page on PDF file.
                    frame.getContentPane().removeAll(); //Removing the current frame items to write new.
                    frame.add(chartPanel); //Adding our chart to frame to pop up it on screen.

                    frame.pack(); //Packing the frame
                    frame.setVisible(true); //Making frame visible.
                }

            }
            catch (Exception ex){
                //If the save directory for multiple files is not selected create a warning, it will cause and error because we need to create
                // PNGs first on that directory after that we are adding them to PDF.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Please select a save destination.");
                alert.showAndWait();
            }


        }
        //If course code is selected as ALL COURSES
        else{
            //If select section checkbox is enabled do the operations below.
            if (this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSectionSelect)){
                //Create a section given barchart.
                CategoryChart barChart = MultifileCharts.createMultifileYearSectionGivenBarGraph();

                //Below is same operations with first if.
                JPanel chartPanel = new XChartPanel<CategoryChart>(barChart);

                try{
                    //if years aren't selected create a warning.
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }

                        document.add(new Paragraph("BarChart Section Averages-" + SelectedSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(barChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }


                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }


            }
            //If select subsection checkbox is enabled do the operations below.
            if(this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSubSectionSelect)) {
                //Create a subsection given barchart.
                CategoryChart barChart = MultifileCharts.createMultifileYearSubSectionGivenBarGraph();

                //Below is same operations with the first if.
                JPanel chartPanel = new XChartPanel<CategoryChart>(barChart);

                try{
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }

                        document.add(new Paragraph("BarChart Section Averages-" + SelectedSubSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(barChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }

                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }
            }
        }

    }

//#############################################################################################################################

    //Nearly same operations with barchart.
    public void drawRadarChartButtonPushed(ActionEvent event) throws FileNotFoundException, DocumentException {

        try{
            SelectedYears = getSelectedYears();
        }
        catch (Exception ex){
        }
        try{
            SelectedSubSection = getSelectedSubSection();
        }
        catch (Exception ex){
        }
        try{
            SelectedSection = getSelectedSection();
        }
        catch (Exception ex){
        }
        SelectedCourseCode=getSelectedCourseCode();



        // Create and set up the window.
        JFrame frame = new JFrame("Charts");
        frame.setLayout(new BorderLayout());

        // chart
        if (!SelectedCourseCode.equals("ALL COURSES")) {


            RadarChart radarChart = MultifileCharts.createMultifileYearSectionGivenCourseCodeRadarGraph();

            JPanel chartPanel = new XChartPanel<RadarChart>(radarChart);

            try{
                if (SelectedYears.size()==0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select year(s).");
                    alert.showAndWait();
                }
                else{
                    outputFigureCounterMF++;
                    outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                    document.add(new Paragraph("Figure"+outputFigureCounterMF));
                    String yearsToString="";
                    for (int x: SelectedYears){
                        yearsToString = yearsToString + ""+x + ", ";
                    }

                    document.add(new Paragraph("RadarChart Section Averages-" + SelectedCourseCode + " on " + yearsToString + "years"));
                    BitmapEncoder.saveBitmap(radarChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                    document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                    document.newPage();
                    frame.getContentPane().removeAll();
                    frame.add(chartPanel);

                    frame.pack();
                    frame.setVisible(true);
                }

            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Please select a save destination.");
                alert.showAndWait();
            }


        }
        else{
            if (this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSectionSelect)){


                RadarChart radarChart = MultifileCharts.createMultifileYearSectionGivenRadarGraph();

                JPanel chartPanel = new XChartPanel<RadarChart>(radarChart);

                try{
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }

                        document.add(new Paragraph("RadarChart Section Averages-" + SelectedSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(radarChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }
                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }


            }
            if(this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSubSectionSelect)) {


                RadarChart radarChart = MultifileCharts.createMultifileYearSubSectionGivenRadarGraph();

                JPanel chartPanel = new XChartPanel<RadarChart>(radarChart);

                try{
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }
                        document.add(new Paragraph("RadarChart Section Averages-" + SelectedSubSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(radarChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }

                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }
            }
        }
    }

    //#############################################################################################################################

    //Nearly same operations with barchart except it can't create if course code is selected as ALL COURSES because it will be meaningless.
    public void drawPieChartButtonPushed(ActionEvent event) {
        Path tempPath = currentPathMF;

        try{
            SelectedYears = getSelectedYears();
        }
        catch (Exception ex){
        }
        try{
            SelectedSubSection = getSelectedSubSection();
        }
        catch (Exception ex){
        }
        try{
            SelectedSection = getSelectedSection();
        }
        catch (Exception ex){
        }
        SelectedCourseCode=getSelectedCourseCode();



        // Create and set up the window.
        JFrame frame = new JFrame("Charts");
        frame.setLayout(new BorderLayout());


        if (SelectedCourseCode.equals("ALL COURSES")) {

            if (this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSectionSelect)){
                PieChart pieChart = MultifileCharts.createMultifileYearSectionGivenSectionPieGraph();

                JPanel chartPanel = new XChartPanel<PieChart>(pieChart);

                try{
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }

                        document.add(new Paragraph("PieChart Section Averages-" + SelectedSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(pieChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }


                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }
            }
            if (this.enableSOrSubSelectToggleGroup.getSelectedToggle().equals(this.enableSubSectionSelect)) {

                PieChart pieChart = MultifileCharts.createMultifileYearSubSectionGivenPieGraph();

                JPanel chartPanel = new XChartPanel<PieChart>(pieChart);

                try{
                    if (SelectedYears.size()==0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select year(s).");
                        alert.showAndWait();
                    }
                    else{
                        outputFigureCounterMF++;
                        outputFigureNameMF = currentPathMF.toAbsolutePath().toString()+"\\Figure"+outputFigureCounterMF+".png";

                        document.add(new Paragraph("Figure"+outputFigureCounterMF));
                        String yearsToString="";
                        for (int x: SelectedYears){
                            yearsToString = yearsToString + ""+x + " ";
                        }

                        document.add(new Paragraph("PieChart Section Averages-" + SelectedSubSection + "on " + yearsToString));
                        BitmapEncoder.saveBitmap(pieChart,outputFigureNameMF, BitmapEncoder.BitmapFormat.PNG);
                        document.add(com.itextpdf.text.Image.getInstance(outputFigureNameMF));
                        document.newPage();

                        frame.getContentPane().removeAll();
                        frame.add(chartPanel);

                        frame.pack();
                        frame.setVisible(true);
                    }

                }
                catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a save destination.");
                    alert.showAndWait();
                }
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("This chart is meaningless so it isn't drawable!");
            alert.setTitle("ERROR");
            alert.showAndWait();
        }


    }





    //################################################################################################################################################
    //################################################################################################################################################
    //################################################################Chose File Buttons##############################################################
    //################################################################################################################################################
    //################################################################################################################################################


    //When Select File(s) button pushed it pop ups a screen and ask you for selecting file(s).
    public void choseFileButtonPushed(ActionEvent event) throws IOException{

        //Firstly it resets all selected items and tables to start fresh operations.
        SelectedCourseCode = "";
        try {
            SelectedYears = null;
        }
        catch (Exception ex){ }
        SelectedSubSection = "";
        SelectedSection = "";
        try{
            listView.getItems().clear();
            choiceBoxSectionsBC.getItems().clear();
            choiceBoxSubSectionsBC.getItems().clear();
        }
        catch (Exception ex){ }

        //Setting the File Chooser pop-up screen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File Dialog");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files .xls","*.xls"));

        filesArray = fileChooser.showOpenMultipleDialog(null); //Popping up the screen and storing selected files in the List.

        //Load Data
        ObservableList<CourseTableForGUI> courseTableItems = GUITableMethods.getCourse();
        courseTable.setItems(courseTableItems);//Load file names to course table.

        multifileGoingToBeUsedInGraphs = new ArrayList<>(); // Reseting the elements of ArrayList.
        FileReadOperations.Load_Multi_Files( multifileGoingToBeUsedInGraphs, usableArray); //Reading the file(s).
        commentTable.setItems(GUITableMethods.getComments(multifileGoingToBeUsedInGraphs)); //Adding comments of files to comment table.

        savedText.setText("");

        ObservableList<String> courseCodesBC = FXCollections.observableArrayList(getCourseCodesOfUsableArray());
        choiceBoxCourseCodesBC.setItems(courseCodesBC);

    }

    //When Select Directory button pushed it pop ups a screen and ask you for selecting directory and takes only excel files from this directory.
    public void choseDirectoryButtonPushed(ActionEvent event) throws IOException{

        //Firstly it resets all selected items and tables to start fresh operations.
        SelectedCourseCode = "";
        try {
            SelectedYears = null;
        }
        catch (Exception ex){ }
        SelectedSubSection = "";
        SelectedSection = "";
        try{
            listView.getItems().clear();
            choiceBoxSectionsBC.getItems().clear();
            choiceBoxSubSectionsBC.getItems().clear();
        }
        catch (Exception ex){ }


        //Setting the directory chooser.
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory Dialog");

        filesArray = new LinkedList<>();//Resetting the elements of List

        //Setting the pop-up screen
        File directory = directoryChooser.showDialog(null);

        //Takes every file in a directory to List.
        List<File> tempFilesArray = new LinkedList<>();
        tempFilesArray = Arrays.asList(directory.listFiles());

        //Seperates only excel files and stores them on ArrayList
        for(int i=0; i<tempFilesArray.size();i++){
            String x = tempFilesArray.get(i).getName();
            String[] splittedName = x.split("\\.");
            if (splittedName[splittedName.length-1].equals("xls") || splittedName[splittedName.length-1].equals("xlsx")){
                filesArray.add(tempFilesArray.get(i));
            }
        }

        //load data
        ObservableList<CourseTableForGUI> courseTableItems = GUITableMethods.getCourse();
        courseTable.setItems(courseTableItems);

        multifileGoingToBeUsedInGraphs = new ArrayList<>();
        FileReadOperations.Load_Multi_Files( multifileGoingToBeUsedInGraphs, usableArray);
        commentTable.setItems(GUITableMethods.getComments(multifileGoingToBeUsedInGraphs));

        savedText.setText("");

        ObservableList<String> courseCodesBC = FXCollections.observableArrayList(getCourseCodesOfUsableArray());
        choiceBoxCourseCodesBC.setItems(courseCodesBC);


    }









    //################################################################################################################################################
    //################################################################################################################################################
    //################################################################Save File Buttons##############################################################
    //################################################################################################################################################
    //################################################################################################################################################


    //Checks for single or multifile, makes the analysis of file(s) and saves the analysis on selected directory.
    public void saveSingleFileButtonPushed(ActionEvent event) throws IOException {
        if(usableArray.size()==1){
            File_f graphs = multifileGoingToBeUsedInGraphs.get(0);
            saveOperationsSingleFile.saveSingleFileToPDF(graphs);

            savedText.setText("Saved ✓");
        }
        else{
            File_f graph = getSelectedSingleFile();
            saveOperationsSingleFile.saveSingleFileToPDF(graph);

            savedText.setText("Saved ✓");
        }
    }

    //Allows user to add UserAddedComment
    public void changeUserAddedCommentCellEvent(TableColumn.CellEditEvent editedCell){
        CommentsTableForGUI commentSelected = commentTable.getSelectionModel().getSelectedItem();
        commentSelected.setUserAddedComment(editedCell.getNewValue().toString());
    }

    //If radiobox on GUI is selected it shows the save path.
    public void savePathTextEnabled(){
        try{
            if(enableSavePathText.isSelected()){
                savePathText.setText(saveOperationsSingleFile.currentPath);
            }
            else{
                savePathText.setText("");
            }
        }
        catch (Exception ex){
        }
    }







    //################################################################################################################################################
    //################################################################################################################################################
    //################################################################Draw Barchart Elements##########################################################
    //################################################################################################################################################
    //################################################################################################################################################



    //Gets unique years from selected files.
    public Set<Integer> getYearsOfUsableArray(String courseCode) {
        Set<Integer> years = new HashSet<>();
        try{

            for (int i = 0; i < multifileGoingToBeUsedInGraphs.size(); i++) {
                if (courseCode.equals("ALL COURSES")){
                    years.add(multifileGoingToBeUsedInGraphs.get(i).year);
                }
                else if (multifileGoingToBeUsedInGraphs.get(i).getDersKodu().equals(SelectedCourseCode)) {
                    years.add(multifileGoingToBeUsedInGraphs.get(i).year);
                }
            }
        }
        catch (Exception ex){
        }
        return years;
    }


    //Gets unique course codes from selected files.
    public Set<String> getCourseCodesOfUsableArray(){
        Set<String> courseCodes = new HashSet<>();
        try{
            courseCodes.add("ALL COURSES");
            for (int i=0; i<multifileGoingToBeUsedInGraphs.size(); i++){
                courseCodes.add(multifileGoingToBeUsedInGraphs.get(i).getDersKodu());
            }
        }
        catch (Exception ex){
        }
        return courseCodes;
    }

    //Gets sections of selected course code.
    public Set<String> getSectionsOfCourseCode(String courseCode){
        Set<String> sectionsOfCourse = new HashSet<>();
        try{
            for (int i=0; i<multifileGoingToBeUsedInGraphs.size(); i++){
                for (int j=0; j<multifileGoingToBeUsedInGraphs.get(i).Sections.length;j++){
                    sectionsOfCourse.add(multifileGoingToBeUsedInGraphs.get(i).Sections[j].getText());
                }
            }
        }
        catch (Exception ex){
        }
        return sectionsOfCourse;
    }


    //Gets subsections of selected course code.
    public Set<String> getsubsectionsOfCourseCode(String courseCode){
        Set<String> subsectionsOfCourse = new HashSet<>();
        try{
            for (int i=0; i<multifileGoingToBeUsedInGraphs.size(); i++){
                for (int j=0; j<multifileGoingToBeUsedInGraphs.get(i).Sections.length;j++){
                    for (int k=0; k<multifileGoingToBeUsedInGraphs.get(i).Sections[j].subsections.size();k++){
                        subsectionsOfCourse.add(multifileGoingToBeUsedInGraphs.get(i).Sections[j].subsections.get(k).text);
                    }
                }
            }
        }
        catch (Exception ex){
        }
        return subsectionsOfCourse;
    }

    //When user selected some years it helps to get them.
    public Set<Integer> getSelectedYears(){

        ObservableList<Integer> x = listView.getSelectionModel().getSelectedItems();
        Set<Integer> y = new HashSet<>();
        for (int i=0; i<x.size(); i++){
            y.add(x.get(i));
        }
        return y;
    }

    //When user select a course name it helps to get it.
    public String getSelectedCourseCode(){
        return choiceBoxCourseCodesBC.getValue().toString();
    }

    //When user select a section it helps to get it.
    public String getSelectedSection(){
        return choiceBoxSectionsBC.getValue().toString();
    }

    //When user select a subsection it helps to get it.
    public String getSelectedSubSection(){
        return choiceBoxSubSectionsBC.getValue().toString();
    }

    //When user selects a single file from course names table it helps to get it.
    public File_f getSelectedSingleFile(){
        ObservableList<CourseTableForGUI> x = courseTable.getSelectionModel().getSelectedItems();
        return x.get(0).getFileFile_f();
    }

}