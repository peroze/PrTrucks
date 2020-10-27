package sample;

public class ModelKTEO {
    private String LiscPlate;
    private String Price;
    private String Kilometers;
    private String Date;
    private String Warnings;
    private String Next;
    private String Company;

    public ModelKTEO(String liscPlate, String price, String kilometers, String date, String warnings, String next, String company) {
        LiscPlate = liscPlate;
        Price = price;
        Kilometers = kilometers;
        Date = date;
        Warnings = warnings;
        Next = next;
        Company = company;
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

    public String getWarnings() {
        return Warnings;
    }

    public void setWarnings(String warnings) {
        Warnings = warnings;
    }

    public String getNext() {
        return Next;
    }

    public void setNext(String next) {
        Next = next;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
}
