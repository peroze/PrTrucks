package sample;

public class StringsForTables {


    private String string;
    private String string2;


    public StringsForTables(String string) {
        this.string = string;
    }

    public StringsForTables(String string, String string2) {
        this.string = string;
        this.string2 = string2;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }
}
