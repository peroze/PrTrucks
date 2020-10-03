package sample;

import java.sql.*;
import java.util.ArrayList;

public class Sql {


    private Connection conn;

    /**
     * The Constractor For the Class
     */
    public Sql() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(" COnnected");
            e.printStackTrace();
        }

    }


    /**
     * This method deletes an entry from the table
     *
     * @param id    The Id of the car
     * @param Table The Table from whitch the delete will happend
     */
    public void DeleteWith(int id, String Table) {

    }

    /**
     * This Method Returns The List With All the cars
     *
     * @return The Cars
     */
    public ResultSet Query_General_Trucks() {
        ResultSet rs = null;
        try {
            String sql = "SELECT Trucks.id, Trucks.LiscPlate,Trucks.Manufactor,Trucks.Model,Trucks.Plaisio,Trucks.Type,Trucks.Location,Trucks.First_Date,Trucks.Kilometers FROM Trucks";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_General_Service() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  Trucks.LiscPlate,Service.Date,Service.Kilometers,Service.Date,Service.Type FROM Trucks  JOIN Service ON Trucks.id=Service.id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * This method inserts a new car on the db
     *
     * @param a the new car
     */
    public void InsertCar(ModelTruck a, int max_i) {
        ArrayList<String> car = new ArrayList<>();

        car.add(a.getLiscPlate());
        car.add(a.getManufactor());
        car.add(a.getModel());
        car.add(a.getKilometers());
        car.add(a.getPlaisio());
        car.add(a.getLocation());
        car.add(a.getType());
        car.add(a.getDate());


        String sql = "INSERT INTO Trucks(id,LiscPlate,Manufactor,Model,Kilometers,Plaisio,Location,Type,First_Date) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println(max_i);
            pstmt.setInt(1, max_i+1);
            pstmt.setString(2, car.get(0));
            pstmt.setString(3, car.get(1));
            pstmt.setString(4, car.get(2));
            pstmt.setInt(5, Integer.valueOf(car.get(3)));
            pstmt.setString(6, car.get(4));
            pstmt.setString(7, car.get(5));
            pstmt.setString(8, car.get(6));
            pstmt.setString(9, car.get(7));


            pstmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method gets a date and the table and returns all entries in this date
     *
     * @param Date  The date
     * @param Table The Table
     */
    public void Query_By_Date(String Date, String Table) {

    }


}
