/**
 * @Author Muhammed Rahmetullah Kartal / Ramazan Yetişmiş
 * @Date 11.03.2019 / 21.03.2019
 * @Description: Section Object
 */


package Project.mainObjects;

import java.util.ArrayList;

public class Section {
    public String text;
    public ArrayList<Subsection> subsections=new ArrayList<>();


    public Section(){}

    public Section(String name, ArrayList<Subsection> subsections){
        this.text = name;
        this.subsections = subsections;
    }

    public void setText(String text) { this.text = text; }

    public void setSubsections(ArrayList<Subsection> subsections) { this.subsections = subsections; }

    public ArrayList<Subsection> getSubsections() { return subsections; }

    public String getText() { return text; }
}
