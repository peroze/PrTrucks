package Pr.Cars;

import java.io.*;
import java.util.regex.Pattern;

public class FileManagment {
    File file;
    String type;

    public FileManagment() {
        file = new File("Settings.txt");
        type="Settings";
    }

    public void Write(String Data){
        try {
            FileWriter Writer=new FileWriter(file,false);
            Writer.write(Data);
            Writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(String Name,String Type){
        file=new File(Name);
        Type=Type;
    }

    public Double ReadFuelPrice(){
        try{
            FileReader Reader=new FileReader(file);
            char[] Data=new char[100];
            Reader.read(Data);
            Reader.close();
            String returns=null;
            returns=String.valueOf(Data);
            return Double.valueOf(returns);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -5.0;
    }

    public String[] Read(){
        try {
            FileReader Reader=new FileReader(file);
            char[] Data=new char[100];
            Reader.read(Data);
            Reader.close();
            String strs=String.valueOf(Data);
            String[]returns=strs.split(Pattern.quote("~"));
            return returns;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
