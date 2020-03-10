/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 30.03.2019
 * @Description: CourseTableForGUI object for FXML GUI CourseTableForGUI Table.
 */

package Project.GUIDemo;

import Project.mainObjects.File_f;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;

//This object is for JavaFX table on the first screen and to pace file names.
public class CourseTableForGUI {
    //types must be simplestringproperty
    //or simglestringint kinda
    private SimpleStringProperty courseCode;
    private SimpleStringProperty tableSection;
    private SimpleStringProperty year;
    private SimpleStringProperty term;

    private File fileJustFile;//To select single item
    private File_f fileFile_f;// To select single item


    public CourseTableForGUI(String courseCode, String section, String year, String term){
        this.courseCode= new SimpleStringProperty(courseCode);
        this.tableSection=new SimpleStringProperty(section);
        this.year=new SimpleStringProperty(year);
        this.term=new SimpleStringProperty(term);
    }



    public String getCourseCode() {
        return courseCode.get();
    }

    public String getSection() {
        return tableSection.get();
    }

    public String getTerm() {
        return term.get();
    }

    public String getYear() { return year.get(); }



    public void setYear(String year) { this.year = new SimpleStringProperty(year); }

    public void setCourseCode(String courseCode) { this.courseCode = new SimpleStringProperty(courseCode); }

    public void setTerm(String term) { this.term = new SimpleStringProperty(term); }

    public void setSection(String section) { this.tableSection = new SimpleStringProperty(section);}




    public void setFileFile_f(File_f fileFile_f) {
        this.fileFile_f = fileFile_f;
    }

    public void setFileJustFile(File fileJustFile) {
        this.fileJustFile = fileJustFile;
    }

    public File getFileJustFile() {
        return fileJustFile;
    }

    public File_f getFileFile_f() {
        return FileReadOperations.Load_File(fileJustFile);
    }
}

