package sample;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains all the methods which are needed in order to sexport data from the database
 *
 * @author peroze
 * @version 1.0 Alpha
 */
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


    /**
     * This method returns the list with all Services
     *
     * @return All Services
     */
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
     * This method returns all Repairs
     *
     * @return All stored Repairs
     */
    public ResultSet Query_General_Repair() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Repairs  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


    /**
     * This method return the list with all KTEO
     * @return All KTEOs
     */
    public ResultSet Query_General_KTEO() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM KTEO  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * This method returns the list with all Emmision Cards
     * @return The set with all Emmision cards
     */
    public ResultSet Query_General_EmmisionCard() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM EmmisionCard  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This method returns the list with all Refills
     * @return The set with all refills
     */
    public ResultSet Query_General_Refill() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Refill  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * This question the total cost of a table for a specific car
     * @param Table The table that we need to sum
     * @param Lisc The car
     * @return The total cost
     */
    public ResultSet Query_Car_Price(String Table,String Lisc){
        ResultSet rs;
        try{
            String Query="Select SUM(Price) as Total From ? WHERE id=?";
            PreparedStatement pstm=conn.prepareStatement(Query);
            pstm.setInt(2,Integer.valueOf(GetIdFromLisx(Lisc)));
            pstm.setString(1,Table);
            rs=pstm.executeQuery();
            return rs;
        }
        catch (SQLException e) {
            return null;
        }

    }


    /**
     * This method returns the total coast of all cars
     * @param Table
     * @return
     */
    public ResultSet Querry_All_Price(String Table){
        ResultSet rs;
        try{
            String Query="Select SUM(Price) as Total From ?";
            PreparedStatement pstm=conn.prepareStatement(Query);
            pstm.setString(1,Table);
            rs=pstm.executeQuery();
            return rs;
        }
        catch (SQLException e) {
            return null;
        }
    }


    /**
     * This question the total cost of a table for a specific car between a specific date
     * @param Table The table that we need to sum
     * @param Lisc The car
     * @return The total cost
     */
    public ResultSet Query_Car_Price_Date(String Table,String Lisc,String From, String To){
        ResultSet rs;
        try{
            String Query="Select SUM(Price) as Total From "+Table+" WHERE id=? AND Date BETWEEN ? AND ?";
            PreparedStatement pstm=conn.prepareStatement(Query);
            pstm.setInt(1,Integer.valueOf(GetIdFromLisx(Lisc)));
            pstm.setString(2,From);
            pstm.setString(3,To);
            rs=pstm.executeQuery();
            return rs;
        }
        catch (SQLException e) {
            return null;
        }

    }


    /**
     * This method return the cost of all cars between two Dates
     * @param Table The table of the SQL DB
     * @param From The 1st Date
     * @param To The Second Date
     * @return The total cost
     */
    public ResultSet Query_All_Price_Date(String Table,String From, String To){
        ResultSet rs;
        try{
            String Query="Select SUM(Price) as Total From "+Table+"  WHERE Date BETWEEN ? AND ?";
            PreparedStatement pstm=conn.prepareStatement(Query);
           // pstm.setString(1,Table);
            pstm.setString(1,From);
            pstm.setString(2,To);
            rs=pstm.executeQuery();
            return rs;
        }
        catch (SQLException e) {
            return null;
        }
    }

    /**
     * This method returns the total amount of fuel
     * @return The total Fuel and KM
     */
    public ResultSet Query_All_Refill() {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill";
            PreparedStatement pstm = conn.prepareStatement(Query);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * This method returns the total amount of fuel of a specific car
     * @param lisc The car
     * @return The total Fuel and KM of the car
     */
    public ResultSet Query_Car_Refill(String lisc) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Car_id=?";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1,GetLisxxFromId(lisc));
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * This method returns the total amount of fuel between a specific Date
     * @param From The 1st Date
     * @param To The 2nd Date
     * @return The total Fuel and KM
     */
    public ResultSet Query_All_Refill_Date(String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Date BETWEEN ? AND ?";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1,From);
            pstm.setString(2,To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
    /**
     * This method returns the total amount of fuel between a specific Date of a specific car
     * @param From The 1st Date
     * @param To The 2nd Date
     * @return The total Fuel and KM
     */
    public ResultSet Query_Car_Refill_Date(String lisc,String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Car_id=? AND Date BETWEEN ? AND ?";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1,GetIdFromLisx(lisc));
            pstm.setString(2,From);
            pstm.setString(3,To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * This method returns all liscense plate numbers
     *
     * @return all liscence plate numbers
     */
    public ResultSet Query_All_Lisc() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  LiscPlate FROM  Trucks ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }




    /**
     * This method returns the latest  service for each car (Based on the Date of the next Service
     *
     * @return The Latest Service of each car
     */
    public ResultSet Query_Group_Service() {
        String sql = "Select id, MAX(Next_Date),Kilometers,Date,Type,Changes,Workshop,Next_Kilometers,Price,Service_Id From Service Group BY id";
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * This method returns all entries of a specific car in a specific table
     *
     * @param lisc  The liscence plate of the car
     * @param table The table we want to search
     * @return the dataset with the right data
     */
    public ResultSet Query_Specific_With_Lisc(String lisc, String table) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM  " + table + " WHERE id=? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, GetIdFromLisx(lisc));
            rs = pstmt.executeQuery();
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
            pstmt.setInt(1, max_i + 1);
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
     * This method inserts a new repair on the db
     *
     * @param a the new Repair
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertRepair(ModelRepair a) {
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


    /**
     * This method inserts a new Service on the db
     *
     * @param a the new service
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertService(ModelService a) {
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


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * This method inserts a new KTEO on the db. It also add the included emmisions card in the corresponding Table
     * If EmmisionDate variable is null it means that its not a clean insert but a reinsert of a previous so we just add the new one without Deleteing the previous or adding emission card
     *
     * @param a            the new KTEO
     * @param EmmisionDate The Expiration of the Emmision Card coming with KTEO
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertKTEO(ModelKTEO a, String EmmisionDate) {
        try {
            int i = 1;
            ResultSet rs = null;
            if (EmmisionDate != null) {
                rs = Query_Specific_With_Lisc(a.getLiscPlate(), "KTEO");
                i = DeleteΚΤΕΟ(a.getLiscPlate());  // Only one KTEO is stored from each car so we delete the previous one
                if (i == 0) {
                    throw new SQLException();
                }
            }
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getWarnings());
            repair.add(a.getDate());
            repair.add(a.getCompany());
            repair.add(a.getNext());
            repair.add(a.getPrice());
            String sql = "INSERT INTO KTEO(id,Warnings,Kilometers,Date,Company,DateNext,Price) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setString(2, repair.get(1));
            pstmt.setInt(3, Integer.valueOf(repair.get(0)));
            pstmt.setString(4, repair.get(2));
            pstmt.setString(5, repair.get(3));
            pstmt.setString(6, repair.get(4));
            pstmt.setString(7, repair.get(5));
            pstmt.executeUpdate();
            if (EmmisionDate==null){
                return 1;
            }
            i=InstertEmmisionCard(new ModelEmmisionCard(a.getLiscPlate(),a.getKilometers(),a.getDate(),"TRUE",EmmisionDate));
            if (i == 1) {
                return 1;
            } else {
                DeleteΚΤΕΟ(a.getLiscPlate());// If we get here it means that the new KTEO is added but the Card is mot so we must delete the KTEO which was added and add the previous one
                InsertKTEO(new ModelKTEO(GetLisxxFromId(rs.getString("id")), rs.getString("Price"), rs.getString("Kilometers"), rs.getString("Date"), rs.getString("Warnings"), rs.getString("DateNext"), rs.getString("Company")),null);
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * This method inserts a new Emmision card
     * @param a The new emmision card
     * @return 1 if comleted 0 if not
     */
    public int InstertEmmisionCard(ModelEmmisionCard a) {
        try {

            int i ;
            i = DeleteEmissionCard(a.getLiscPlate());  // Only one Emmision is stored from each car so we delete the previous one
            if (i == 0) {
                throw new SQLException();
            }
            boolean bool;
            if(a.getWithKTEO().equals("TRUE")){
                bool=true;
            }
            else{
                bool=false;
            }
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getDate());
            repair.add(a.getNext());
            repair.add(a.getWithKTEO());
            String sql = "INSERT INTO EmmisionCard(id,Kilometers,Date,NextDate,WithKTEO) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setInt(2, Integer.valueOf(repair.get(0)));
            pstmt.setString(3, repair.get(1));
            pstmt.setString(4, repair.get(2));
            pstmt.setBoolean(5, bool);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }


    /**
     * This method inserts a new Refill card
     * @param a The new Refill
     * @return 1 if comleted 0 if not
     */
    public int InstertRefill(ModelRefill a) {
        try {
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getDate());
            repair.add(a.getAmount());
            String sql = "INSERT INTO Refill(Car_id,Kilometers,Date,Amount) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setInt(2, Integer.valueOf(repair.get(0)));
            pstmt.setString(3, repair.get(1));
            pstmt.setString(4, repair.get(2));
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * This method deletes a car from the database
     *
     * @param id The id of the car to be deleted from the databse
     * @return 1 if the deletion is complete or 0if there is an error
     */
    public int DeleteCar(int id) {
        String stmt = "DELETE FROM Trucks WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection (Not always but sometimes)
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }


    /**
     * This method deletes the KTEO of a specific car
     *
     * @param Lisc The car which will have its KTEO Deleted
     * @return 1 if completed 0 if not
     */
    public int DeleteΚΤΕΟ(String Lisc) {
        int id = Integer.valueOf(GetIdFromLisx(Lisc));
        String stmt = "DELETE FROM KTEO WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }


    /**
     * This method deletes the Emmision Card of a specific car
     *
     * @param Lisc The car which will have its Emmision Card Deleted
     * @return 1 if completed 0 if not
     */
    public int DeleteEmissionCard(String Lisc) {
        int id = Integer.valueOf(GetIdFromLisx(Lisc));
        String stmt = "DELETE FROM EmmisionCard WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * This method deletes the Refill of a specific car
     *
     * @param Id The car which will have its Refill Deleted
     * @return 1 if completed 0 if not
     */
    public int DeleteRefill(String Id) {
        String stmt = "DELETE FROM Refill WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, Integer.valueOf(Id));
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }


    /**
     * This method deletes a specific Service from the list
     *
     * @param ServiceId The id of the service to be deleted
     * @return 1 if completed 0 if not
     */
    public int DeleteService(int ServiceId) {
        String stmt = "DELETE FROM Service WHERE Service_Id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, ServiceId);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }


    /**
     * This method deletes a specific Repair  from the list
     *
     * @param RepairId The id of the Repair to be deleted
     * @return 1 if completed 0 if not
     */
    public int DeleteRepair(int RepairId) {
        String stmt = "DELETE FROM Service WHERE Service_Id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, RepairId);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }


    /**
     * This method return the id of the car given a its Liscence plate number
     *
     * @param lisc The liscence plate number
     * @return The id
     */
    public String GetIdFromLisx(String lisc) {
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


    /**
     * This method return the liscence of the car given  its id
     *
     * @param id The id of the car
     * @return The liscence plate number
     */
    public String GetLisxxFromId(String id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  LiscPlate FROM Trucks  WHERE id=" + id;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            return rs.getString("LiscPlate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * This method is used to transfrom the boolean type variable, which are in English, to Greek.
     *
     * @param bool The boolean variable
     * @return " Ναι " or "Οχι"
     */
    public String BooleantoGreek(boolean bool) {
        if (bool == true) {
            return "Ναί";
        }
        return "Όχι";
    }




    public void Disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
