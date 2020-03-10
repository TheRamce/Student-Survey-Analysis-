/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 31.03.2019-31.03.2019
 * @Description: Comment Table, CourseTableForGUI Table on GUI.
 */


package Project.GUIDemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Project.mainObjects.File_f;

import java.io.File;
import java.util.ArrayList;

public class GUITableMethods {

    // Getting the chosen files to add course table on first screen.
    public static ObservableList<CourseTableForGUI> getCourse(){
        //Must be ObservableList type
        ObservableList<CourseTableForGUI> courses = FXCollections.observableArrayList();

        ArrayList<File> tempFileArray= new ArrayList<>();
        Project.usableArray = new ArrayList<>();

        for(int i = 0; i<Project.filesArray.size(); i++){
            tempFileArray.add(Project.filesArray.get(i));
        }

        try{
            int counter = 0;
            for (int i = 0; i < tempFileArray.size(); i++) {
                String name = tempFileArray.get(i).getName();
                String temp = name.split("x")[0];
                try{
                    String[] nameParts = temp.split("_");
                    String courseCode = (nameParts[0]+nameParts[1]);
                    String tableSection = nameParts[2];
                    String year = nameParts[3];
                    String term = nameParts[4];
                    CourseTableForGUI oneCourse = new CourseTableForGUI(courseCode, tableSection, year, term);
                    oneCourse.setFileJustFile(tempFileArray.get(i));
                    courses.add(oneCourse);

                    counter++;
                    Project.usableArray.add(tempFileArray.get(i));

                }
                catch (Exception exception){
                    System.out.println("Please enter file name on CourseCode_Section_Year_Term.xlsx format");
                    System.out.println(tempFileArray.get(counter).getName() + " is the problem.");
                    counter++;
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception);
        }

        return courses;
    }



    //Getting the comments to add comment table on first screen
    public static ObservableList<CommentsTableForGUI> getComments(ArrayList<File_f> usable){
        //Must be ObservableList type to use in GUI Table.
        ObservableList<CommentsTableForGUI> comments = FXCollections.observableArrayList();

        try{
            for(int i =0; i<usable.size(); i++){
                int commentsSize = usable.get(i).comments.size(); //Size of comments arraylist
                ArrayList<String> commentsS= usable.get(i).comments; // Adding comments to an string arraylist

                CommentsTableForGUI fileName = new CommentsTableForGUI(usable.get(i).getName(),"");
                comments.add(fileName);
                //Adding comments to comments observable arraylist
                for(int j=0; j<commentsSize; j++){
                    String comment = commentsS.get(j);
                    CommentsTableForGUI oneComment = new CommentsTableForGUI(comment,"");
                    comments.add(oneComment);
                }
                //If size of comments is 0 add "No Comment" Exception
                if (commentsSize==0){
                    comments.add(new CommentsTableForGUI("No comment",""));
                }
                //for a space between file name and comments above it add an empty element.
                comments.add(new CommentsTableForGUI("",""));



            }
        }
        catch (Exception exception){
            System.out.println(exception);
        }

        return comments;
    }


}
