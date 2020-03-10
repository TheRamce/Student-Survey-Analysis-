package Project.GUIDemo;
/** @Author Ramazan Yetismis
        * @Date 02.04.2019
        * @Description: Reading excel files
        */





import Project.mainObjects.File_f;
import Project.mainObjects.Section;
import Project.mainObjects.Subsection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadOperations {
    //Reading multifiles.
    public static void  Load_Multi_Files(ArrayList<File_f> files, ArrayList<File> multi) {

        for (int i = 0; i < multi.size(); i++) {
            File single = multi.get(i);
//            String single=multi.get(i);
            files.add(Load_File(single));
        }
    }


    //Reading single file.
    public static File_f Load_File(File single){
        File_f file=new File_f();

        String path=single.getName();
        String s[]=path.split("_");
        file.setName(path);
        file.year =Integer.parseInt(s[3]);
        file.setDersKodu(s[0]+s[1]);
        file.setSect(Integer.parseInt(s[2]));

//        String s[]=path.split("_");
//        file.yaer=Integer.parseInt(s[3]);
//        file.setDersKodu(s[0]+s[1]);

        file.setSect(Integer.parseInt(s[2]));

        try {

            FileInputStream excelFile = new FileInputStream(new File(single.getAbsolutePath()));
            HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
//                    Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Row row=datatypeSheet.getRow(2);
            Cell cell=row.getCell(1);
            file.setÖğretimElemanı(cell.getStringCellValue());
//            System.out.println(file.getÖğretimElemanı());
            Cell cell2=row.getCell(3);
            file.setÖğrenciSayısı((int)cell2.getNumericCellValue());
//            System.out.println(file.getÖğrenciSayısı());
            Row row2=datatypeSheet.getRow(3);
//             ----------In your code comment this 2 line as well----------------
//                Cell cell3=row2.getCell(1);
//                file.setDersKoduSection(cell3.getStringCellValue());
//
//            System.out.println(file.getDersKodu()+" "+file.getSect());
            Cell cell4=row2.getCell(3);
            file.setCevapAdedi((int)cell4.getNumericCellValue());
//            System.out.println(file.getCevapAdedi());
            Row row3=datatypeSheet.getRow(4);
            Cell cell5=row3.getCell(3);
            file.setCevaplamaOranı(cell5.getStringCellValue());
//            System.out.println(file.getCevaplamaOranı());
            String name[]=new String[10];
            int index=-1;
            int carryon=0;

            for (int i=5;i<45;i++){
                Row r=datatypeSheet.getRow(i);
                Cell control=r.getCell(1);
                Cell control1=r.getCell(0);



                if (control.getCellTypeEnum()== CellType.STRING && control.getStringCellValue().equals("Ortalama")){
                    int subindex=-1;

                    Section section=new Section();
                    section.setText(r.getCell(0).getStringCellValue());
//                    System.out.println(section.getText());
                    file.Sections[++index]=section;
                    i++;}

                r=datatypeSheet.getRow(i);

                Subsection subsection=new Subsection();
                subsection.text=r.getCell(0).getStringCellValue();
                if(i==35){
                    carryon=i;
                    break;
                }
                subsection.average=r.getCell(1).getNumericCellValue();
                subsection.stddev=r.getCell(2).getNumericCellValue();
                subsection.na=r.getCell(3).getNumericCellValue();
                subsection.no=r.getCell(4).getNumericCellValue();
                subsection.one=r.getCell(5).getNumericCellValue();
                subsection.two=r.getCell(6).getNumericCellValue();
                subsection.three=r.getCell(7).getNumericCellValue();
                subsection.four=r.getCell(8).getNumericCellValue();
                subsection.five=r.getCell(9).getNumericCellValue();
                subsection.universityAverage=r.getCell(10).getNumericCellValue();

                file.Sections[index].subsections.add(subsection);
//                    System.out.println(subsection.text);

//                    System.out.println("asdsaşk");
//
            }
            //ADDING COMMENTS

            for (int i=carryon+3;i<carryon+10;i++) {

                Row r = datatypeSheet.getRow(i);
                if (r==null)break;
                Cell c = r.getCell(0);
//                System.out.println(c.getStringCellValue());



                file.comments.add(c.getStringCellValue());




            }






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
