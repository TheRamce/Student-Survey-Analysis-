/**
 * @Author Muhammed Rahmetullah Kartal
 * @Date 31.03.2019
 * @Description: CourseTableForGUI object for FXML GUI CourseTableForGUI Table.
 */


package Project.GUIDemo;

import javafx.beans.property.SimpleStringProperty;

public class CommentsTableForGUI {
    private SimpleStringProperty comment; //Comment row of table
    private SimpleStringProperty userAddedComment; //User added comment

    public CommentsTableForGUI(String comment, String userAddedComment){
        this.comment= new SimpleStringProperty(comment);
        this.userAddedComment=new SimpleStringProperty(userAddedComment);
    }

    public String getComment() { return comment.get(); }

    public String getUserAddedComment() { return userAddedComment.get(); }

    public void setComment(String comment) { this.comment.set(comment); }

    public void setUserAddedComment(String userAddedComment) { this.userAddedComment.set(userAddedComment); }
}
