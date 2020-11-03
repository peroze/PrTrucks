package sample;

public class ModelEmmisionCard {
    private String LiscPlate;
    private String Kilometers;
    private String Date;
    private String Next;
    private String WithKTEO;

    public ModelEmmisionCard(String liscPlate,  String kilometers, String date, String withKteo, String next) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Next = next;
        WithKTEO=withKteo;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
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

    public String getNext() {
        return Next;
    }

    public void setNext(String next) {
        Next = next;
    }

    public String getWithKTEO() {
        return WithKTEO;
    }

    public void setWithKTEO(String withKteo) {
        WithKTEO = withKteo;
    }
}
