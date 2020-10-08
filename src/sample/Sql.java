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
            String sql = "SELECT  * FROM Service  ";
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
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertCar(ModelTruck a, int max_i) {
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
            return 1;


        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     * This method deletes a car from the database
     * @param id The id of the car to be deleted from the databse
     * @return 1 if the deletion is complete or 0if there is an error
     */
    public int DeleteCar(int id){
        String stmt="DELETE FROM Trucks WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int DeleteService(int ServiceId){
        String stmt="DELETE FROM Service WHERE Service_Id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1,ServiceId);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int InsertRepair(ModelRepair a){
        ArrayList<String> repair = new ArrayList<>();

        repair.add(a.getKilometers());
        repair.add(a.getDiscreption());
        repair.add(a.getWorkshop());
        repair.add(a.getDate());
        repair.add(a.getChanges());
        repair.add(a.getPrice());


        String sql = "INSERT INTO Repairs(id,Discreption,Kilometers,Date,Changes,Workshop,Price) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setString(2, repair.get(1));
            pstmt.setInt(3, Integer.valueOf(repair.get(0)));
            pstmt.setString(4, repair.get(3));
            pstmt.setString(5, repair.get(4));
            pstmt.setString(6, repair.get(2));
            pstmt.setInt(7, Integer.valueOf(repair.get(5)));


            pstmt.executeUpdate();
            return 1;


        } catch (SQLException e) {
            return 0;
        }
    }


    public int InsertService(ModelService a){
        ArrayList<String> repair = new ArrayList<>();

        repair.add(a.getKilometers());
        repair.add(a.getType());
        repair.add(a.getWorkshop());
        repair.add(a.getDate());
        repair.add(a.getChanges());
        repair.add(a.getNextDate());
        repair.add(a.getNextKilometers());
        repair.add(a.getPrice());

        String sql = "INSERT INTO Service(id,Type,Kilometers,Date,Changes,Workshop,Next_Date,Next_Kilometers,Price) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));

            pstmt.setString(2, repair.get(1));
            pstmt.setInt(3, Integer.valueOf(repair.get(0)));
            pstmt.setString(4, repair.get(3));
            pstmt.setString(5, repair.get(4));
            pstmt.setString(6, repair.get(2));
            pstmt.setString(7, repair.get(5));
            pstmt.setString(8, repair.get(6));
            pstmt.setInt(9, Integer.valueOf(repair.get(7)));
            pstmt.executeUpdate();
            return 1;


        } /* catch (SQLException e) {
            return 0;
        }*/ catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }



    public String GetIdFromLisx(String lisc){
        ResultSet rs = null;
        try {
            String sql = "SELECT  id FROM Trucks  WHERE LiscPlate=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lisc);
            rs = pstmt.executeQuery();
            return rs.getString("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String GetLisxxFromId(String id){
        ResultSet rs = null;
        try {
            String sql = "SELECT  LiscPlate FROM Trucks  WHERE id="+id;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            return rs.getString("LiscPlate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
