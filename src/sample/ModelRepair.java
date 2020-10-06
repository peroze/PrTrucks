package sample;

public class ModelRepair {
    private String LiscPlate,Price,Kilometers,Date,Discreption,Workshop,Changes;

    public ModelRepair(String liscPlate, String price, String kilometers, String date, String discreption, String workshop, String changes) {
        LiscPlate = liscPlate;
        Price = price;
        Kilometers = kilometers;
        Date = date;
        Discreption = discreption;
        Workshop = workshop;
        Changes = changes;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getKilometers() {
        return Kilometers;
    }

    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDiscreption() {
        return Discreption;
    }

    public void setDiscreption(String discreption) {
        Discreption = discreption;
    }

    public String getWorkshop() {
        return Workshop;
    }

    public void setWorkshop(String workshop) {
        Workshop = workshop;
    }

    public String getChanges() {
        return Changes;
    }

    public void setChanges(String changes) {
        Changes = changes;
    }
}
