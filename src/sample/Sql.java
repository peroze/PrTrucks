package sample;

import java.sql.*;

public class Sql {


    private Connection conn;


    public Sql(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(" COnnected" );
            e.printStackTrace();
        }

    }

    public void Insert_Int(int data,String Table){

    }

    public void Insert_String (String data,String Table){

    }

    public void Delete (int id, String Table){

    }

    public ResultSet Query_General_Trucks(){
        ResultSet rs= null;
        try {
            String sql="SELECT Trucks.id, Trucks.LiscPlate,Trucks.Manufactor,Trucks.Model, Service.Date FROM Trucks LEFT JOIN Service ON Trucks.id=Service.id";
            Statement stmt= conn.createStatement();
            rs=stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_General_Service(){
        ResultSet rs= null;
        try {
            String sql="SELECT  Trucks.LiscPlate,Service.Date,Service.Kilometers,Service.Date,Service.Type FROM Trucks  JOIN Service ON Trucks.id=Service.id";
            Statement stmt= conn.createStatement();
            rs=stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }



    public void Query_By_Date(String Date,String Table){

    }

    public void Query_By_Id(int Id, String Table){

    }

}
