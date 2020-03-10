/**
 * @Author Muhammed Rahmetullah Kartal / Ramazan Yetişmiş
 * @Date 11.03.2019 / 21.03.2019
 * @Description: File Object
 */

package Project.mainObjects;

import java.util.ArrayList;

public class File_f {
    private String name;
    private String ÖğretimElemanı;
    private String DersKodu;
    private int ÖğrenciSayısı;
    private int sect;
    private String CevaplamaOranı;
    private int    CevapAdedi;
    public int year;
    public ArrayList<String> comments=new ArrayList<>();
    public Section Sections[]=new Section[7];

//    private File_f file;

    public int getSect() {
        return sect;
    }

    public void setSect(int sect) {
        this.sect = sect;
    }

    public int getÖğrenciSayısı() {
        return ÖğrenciSayısı;
    }

    public void setÖğrenciSayısı(int öğrenciSayısı) {
        ÖğrenciSayısı = öğrenciSayısı;
    }

    public File_f() {
        for (int i=0;i<6;i++){
            Sections[i]=new Section();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setÖğretimElemanı(String öğretimElemanı) {
        ÖğretimElemanı = öğretimElemanı;
    }

    public void setDersKodu(String dersKoduSection) {
        DersKodu = dersKoduSection;
    }


    public void setCevaplamaOranı(String cevaplamaOranı) {
        CevaplamaOranı = cevaplamaOranı;
    }

    public void setCevapAdedi(int cevapAdedi) {
        CevapAdedi = cevapAdedi;
    }

    public String getName() {
        return name;
    }

    public String getÖğretimElemanı() {
        return ÖğretimElemanı;
    }

    public String getDersKodu() {
        return DersKodu;
    }

    public String getCevaplamaOranı() {
        return CevaplamaOranı;
    }


    public int getCevapAdedi() {
        return CevapAdedi;
    }
    public void addSection(String name,int x){
        Sections[x]=new Section();
        Sections[x].setText(name);
    }

//    public void setFile(File_f file) { this.file = file; }
//
//    public File_f getFile() { return file; }
}