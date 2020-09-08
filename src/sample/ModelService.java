package sample;

public class ModelService {

    private String LiscPlate,Date,Kilometers,Type;

    public ModelService(String liscPlate,  String kilometers,String date, String type) {
        LiscPlate = liscPlate;
        Date = date;
        Kilometers = kilometers;
        Type = type;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getKilometers() {
        return Kilometers;
    }

    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }



}
