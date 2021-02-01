package Pr.Cars;

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
            System.out.println("Open");
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
            String sql = "SELECT  * FROM Trucks WHERE Deleted=0 ORDER BY id ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * This Method Returns The List With All the cars
     *
     * @return The Cars
     */
    public ResultSet Query_General_Fast() {
        ResultSet rs = null;
        try {
            String sql = "Select LiscPlate,Manufactor,Trucks.Kilometers,Trucks.Location,Service.Next_Date,MAX(Service.Next_Kilometers),KTEO.DateNext,EmmisionCard.NextDate,Trucks.SpeedWriter,Trucks.FireExtiguiser FROM Trucks,Service,KTEO,EmmisionCard WHERE Trucks.id=Service.id AND Trucks.id=EmmisionCard.id ANd Trucks.id=KTEO.id AND Trucks.Deleted=0 GROUP BY Service.id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * This Method Returns The List With All the cars of The External Comanies
     *
     * @return The Cars
     */
    public ResultSet Query_General_External_Trucks() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM External_Trucks ORDER BY id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * This Method Returns The List With All the cars of The External Comanies
     *
     * @return The Cars
     */
    public ResultSet Query_General_Company() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Companies ORDER BY id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_General_External_Phone_List() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM ExternalPhoneList ORDER BY Name";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_General_Internal_Phone_List() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM InternalPhoneList ORDER BY Name";
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
            String sql = "SELECT  * FROM Repairs JOIN Trucks ON Repairs.id=Trucks.id WHERE Deleted=0  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


    /**
     * This method return the list with all KTEO
     *
     * @return All KTEOs
     */
    public ResultSet Query_General_KTEO() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM KTEO JOIN Trucks ON KTEO.id=Trucks.id WHERE Deleted=0 ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * This method returns the list with all Emmision Cards
     *
     * @return The set with all Emmision cards
     */
    public ResultSet Query_General_EmmisionCard() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM EmmisionCard  JOIN Trucks ON EmmisionCard.id=Trucks.id WHERE Deleted=0  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This method returns the list with all Refills
     *
     * @return The set with all refills
     */
    public ResultSet Query_General_Refill() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Refill JOIN Trucks ON  Refill.Car_id=Trucks.id WHERE Trucks.Deleted=0  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet Query_General_Locations() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Location  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet Query_General_Types() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Type  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet Query_General_Posistion() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM CompanyPosistions  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet Query_General_Partners_Type() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM PartersType  ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }




    /**
     * This question the total cost of a table for a specific car
     *
     * @param Table The table that we need to sum
     * @param Lisc  The car
     * @return The total cost
     */
    public ResultSet Query_Car_Price(String Table, String Lisc) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Price) as Total From ? WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setInt(2, Integer.valueOf(GetIdFromLisx(Lisc)));
            pstm.setString(1, Table);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }

    }


    /**
     * This method returns the total coast of all cars
     *
     * @param Table
     * @return
     */
    public ResultSet Querry_All_Price(String Table) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Price) as Total From ? Where Price is not null";
            if (Table == "Refill") {
                Query = "Select SUM(Cost) as Total From ? WHERE Cost is not null";
            }
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1, Table);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * This question the total cost of a table for a specific car between a specific date
     *
     * @param Table The table that we need to sum
     * @param Lisc  The car
     * @return The total cost
     */
    public ResultSet Query_Car_Price_Date(String Table, String Lisc, String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Price) as Total From " + Table + " WHERE id=? AND Date BETWEEN ? AND ? and Price AND Price is not null";
            if (Table.equals("Refill")) {
                Query = "Select SUM(Cost) as Total From " + Table + " WHERE id=? AND Date BETWEEN ? AND ? AND Cost is not null";
            }
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setInt(1, Integer.valueOf(GetIdFromLisx(Lisc)));
            pstm.setString(2, From);
            pstm.setString(3, To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }

    }


    /**
     * This method return the cost of all cars between two Dates
     *
     * @param Table The table of the SQL DB
     * @param From  The 1st Date
     * @param To    The Second Date
     * @return The total cost
     */
    public ResultSet Query_All_Price_Date(String Table, String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Price) as Total From " + Table + "  WHERE Date BETWEEN ? AND ? AND Price is not null";
            if (Table.equals("Refill")) {
                Query = "Select SUM(Cost) as Total From " + Table + "  WHERE Date BETWEEN ? AND ? AND Cost is not null ";
            }

            PreparedStatement pstm = conn.prepareStatement(Query);
            // pstm.setString(1,Table);
            pstm.setString(1, From);
            pstm.setString(2, To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * This method returns the total amount of fuel
     *
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
     *
     * @param lisc The car
     * @return The total Fuel and KM of the car
     */
    public ResultSet Query_Car_Refill(String lisc) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Car_id=? AND Amount is not null";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1, GetLisxxFromId(lisc));
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * This method returns the total amount of fuel between a specific Date
     *
     * @param From The 1st Date
     * @param To   The 2nd Date
     * @return The total Fuel and KM
     */
    public ResultSet Query_All_Refill_Date(String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Date BETWEEN ? AND ? AND Amount is not null";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1, From);
            pstm.setString(2, To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * This method returns the total amount of fuel between a specific Date of a specific car
     *
     * @param From The 1st Date
     * @param To   The 2nd Date
     * @return The total Fuel and KM
     */
    public ResultSet Query_Car_Refill_Date(String lisc, String From, String To) {
        ResultSet rs;
        try {
            String Query = "Select SUM(Amount) as Total From Refill WHERE Car_id=? AND Date BETWEEN ? AND ?";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1, GetIdFromLisx(lisc));
            pstm.setString(2, From);
            pstm.setString(3, To);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public ResultSet Query_Car_Refill_Cons(String lisc, String From, String To) {

        ResultSet rs;
        try {
            String Query = "Select AVG(Consumption) as Cons From Refill WHERE Car_id=? AND Date BETWEEN ? AND ? AND Consumption is not null";
            PreparedStatement pstm = conn.prepareStatement(Query);
            pstm.setString(1, GetIdFromLisx(lisc));
            pstm.setString(2, From);
            pstm.setString(3, To);
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
            String sql = "SELECT  LiscPlate FROM  Trucks WHERE Deleted=0";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This method returns all liscense plate numbers
     *
     * @return all liscence plate numbers
     */
    public ResultSet Query_All_Companies() {
        ResultSet rs = null;
        try {
            String sql = "SELECT  Name FROM  Companies ";
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
        String sql = "Select  Service.id, MAX(Service.Date) AS 'MAX(Date)',Service.Kilometers AS Kilometers,Service.Next_Date AS Next_Date,Service.Type AS Type,Service.Changes AS Changes ,Service.Workshop AS Workshop ,Service.Next_Kilometers AS Next_Kilometers,Service.Price AS Price ,Service.Service_Id AS Service_Id ,Service.Receipt_Number AS Receipt_Number ,Service.WorkPrice AS WorkPrice  From Service JOIN Trucks ON Service.id=Trucks.id WHERE Trucks.Deleted=0 Group BY Service.id";
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

    public ResultSet Query_Specific_External_Phone_List(String Disc) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM ExternalPhoneList WHERE Discreption=? ORDER BY Name";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Disc);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_Specific_Internal_Phone_List(String Disc) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM InternalPhoneList WHERE Posistion=? ORDER BY Name";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Disc);
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * This method returns all entries of a specific car from Refill table
     *
     * @param lisc The liscence plate of the car
     * @return the dataset with the right data
     */
    public ResultSet Query_Specific_Refill(String lisc) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM  Refill WHERE Car_id=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, GetIdFromLisx(lisc));
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


    public ResultSet Query_Specific_NextKteo(String lisc) {

        ResultSet rs = null;
        try {
            String sql = "SELECT  KTEOIn FROM  Trucks WHERE id=? ";
            String id = GetIdFromLisx(lisc);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet Query_Specific_ExternalByLisc(String Comp) {
        ResultSet rs = null;
        try {

            String sql;
            String id;
            if (Comp.equals("Ιδιότης")) {
                id = "NULL";
            } else {
                id = GetIdFromComp(Comp);
            }
            sql = "SELECT  * FROM  External_Trucks WHERE Company_id=" + id;
            Statement pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * This Method Returns the car with the given lisc. Plate
     *
     * @param lisc The liscence plate
     * @return The Car
     */
    public ResultSet Query_Specific_Trucks(String lisc) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  * FROM Trucks WHERE LiscPlate=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lisc);
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet Query_Specific_LastRefill(String lisc) {
        String sql = "SELECT  MAX(Date),Amount,Kilometers FROM Refill WHERE Car_id=" + GetIdFromLisx(lisc);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet Query_Specific_LastKteoKM(String lisc) {
        String sql = "Select MAX(Date),Kilometers FROM KTEO WHERE id=" + GetIdFromLisx(lisc);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet Query_Specific_LastEmmisionKM(String lisc) {
        String sql = "Select MAX(Date),Kilometers FROM EmmisionCard WHERE id=" + GetIdFromLisx(lisc);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet Query_Specific_LastServiceKM(String lisc) {
        String sql = "Select MAX(Date),Kilometers FROM Service WHERE id=" + GetIdFromLisx(lisc);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet Query_Specific_LastRepairKM(String lisc) {
        String sql = "Select MAX(Date),Kilometers FROM Repairs WHERE id=" + GetIdFromLisx(lisc);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet Query_Specific_NextGas(String lisc) {

        ResultSet rs = null;
        try {
            String sql = "SELECT  GasIn FROM  Trucks WHERE id=? ";
            String id = GetIdFromLisx(lisc);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public ResultSet Query_Specific_NextServiceKm(String lisc) {

        ResultSet rs = null;
        try {
            String id = GetIdFromLisx(lisc);
            String sql = "SELECT  ServiceInKm FROM  Trucks WHERE id=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public ResultSet Query_Specific_NextServiceKmCurrentKm(String id) {
        ResultSet rs = null;

        try {

            String sql = "SELECT  MAX(Next_Kilometers),Trucks.Kilometers FROM Service,Trucks WHERE Service.id=Trucks.id AND Trucks.id=" + id;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ResultSet Query_Specific_TableByLocationType(String Table, String Type, String Location) {
        try {
            String sql;
            ResultSet rs;
            PreparedStatement pstm;
            if (Location == null && Type != null) {
                sql = "SELECT  * FROM" + Table + ",Trucks WHERE " + Table + ".id=Trucks.id AND Trucks.Type=? AND Trucks.Deleted=0";
                if (Table.equals("Service")) {
                    sql = "Select Service.id, MAX(Service.Date)AS Date,Service.Kilometers,Service.Next_Date,Service.Type,Service.Changes,Service.Workshop,Service.Next_Kilometers,Service.Price,Service.Service_Id,Receipt_Number,WorkPrice From Service,Trucks WHERE Service.id=Trucks.id AND Trucks.Type=? AND Trucks.Deleted=0 Group BY Service.id";
                }
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, Type);
            } else if (Type == null && Location != null) {

                sql = "SELECT  * FROM" + Table + ",Trucks WHERE " + Table + ".id=Trucks.id AND Location=? AND Trucks.Deleted=0";
                if (Table.equals("Service")) {
                    sql = "Select Service.id, MAX(Service.Date)AS Date,Service.Kilometers,Service.Next_Date,Service.Type,Service.Changes,Service.Workshop,Service.Next_Kilometers,Service.Price,Service.Service_Id,Receipt_Number,WorkPrice From Service,Trucks WHERE Service.id=Trucks.id AND Location=? AND Trucks.Deleted=0 Group BY Service.id";

                }
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, Location);
            } else {
                sql = "SELECT  * FROM " + Table + " ,Trucks WHERE " + Table + ".id=Trucks.id AND Location=? AND Trucks.Type=? AND Trucks.Deleted=0";
                if (Table.equals("Service")) {
                    sql = "Select Service.id, MAX(Service.Date)AS Date,Service.Kilometers,Service.Next_Date,Service.Type,Service.Changes,Service.Workshop,Service.Next_Kilometers,Service.Price,Service.Service_Id,Receipt_Number,WorkPrice From Service,Trucks WHERE Service.id=Trucks.id AND Location=? AND Trucks.Type=? AND Trucks.Deleted=0 Group BY Service.id";
                }
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, Location);
                pstm.setString(2, Type);
            }
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet Query_Specific_MaxKmAllMinusTrucks(String id) {

        String sql = "SELECT MAX(KM) FROM (SELECT MAX(Service.Kilometers) AS KM FROM Service,Trucks WHERE Service.id=Trucks.id AND Trucks.id=? UNION SELECT MAX(KTEO.Kilometers) AS KM FROM KTEO,Trucks WHERE KTEO.id=Trucks.id AND Trucks.id=? UNION SELECT MAX(Repairs.Kilometers) AS KM FROM Repairs,Trucks WHERE Repairs.id=Trucks.id AND Trucks.id=? UNION SELECT MAX(EmmisionCard.Kilometers) AS KM1 FROM EmmisionCard,Trucks WHERE EmmisionCard.id=Trucks.id AND Trucks.id=? UNION SELECT MAX(Refill.Kilometers) AS KM1 FROM Refill,Trucks WHERE Refill.Car_id=Trucks.id AND Trucks.id=?)";
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(id));
            stmt.setInt(2, Integer.valueOf(id));
            stmt.setInt(3, Integer.valueOf(id));
            stmt.setInt(4, Integer.valueOf(id));
            stmt.setInt(5, Integer.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * This method inserts a new car on the db
     *
     * @param a the new car
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertCar(ModelTruck a, int max_i, boolean edit) {
        ArrayList<String> car = new ArrayList<>();

        car.add(a.getLiscPlate());
        car.add(a.getManufactor());
        car.add(a.getModel());
        car.add(a.getKilometers());
        car.add(a.getPlaisio());
        car.add(a.getLocation());
        car.add(a.getType());
        car.add(a.getDate());
        car.add(a.getData());
        car.add(a.getGasIn());
        car.add(a.getKteoIn());
        car.add(a.getServiceInkm());
        car.add(a.getFireExtinguiser());
        car.add(a.getSpeedWriter());



        String sql = "INSERT OR REPLACE INTO Trucks(id,LiscPlate,Manufactor,Model,Kilometers,Plaisio,Location,Type,First_Date,Data,GasIn,KTEOIn,ServiceInKm,FireExtiguiser,SpeedWriter) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (edit == false) {
                pstmt.setInt(1, max_i + 1);
            } else {
                pstmt.setInt(1, Integer.valueOf(a.getId()));
            }
            pstmt.setString(2, car.get(0));
            pstmt.setString(3, car.get(1));
            pstmt.setString(4, car.get(2));
            pstmt.setInt(5, Integer.valueOf(car.get(3)));
            pstmt.setString(6, car.get(4));
            pstmt.setString(7, car.get(5));
            pstmt.setString(8, car.get(6));
            pstmt.setString(9, car.get(7));
            pstmt.setString(10, car.get(8));
            pstmt.setString(11, car.get(10));
            pstmt.setString(12, car.get(9));
            pstmt.setInt(13, Integer.valueOf(car.get(11)));
            pstmt.setString(14,car.get(12));
            pstmt.setString(15,car.get(13));


            pstmt.executeUpdate();
            return 1;


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * This method inserts a new repair on the db
     *
     * @param a the new Repair
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertRepair(ModelRepair a, boolean edit) {
        ArrayList<String> repair = new ArrayList<>();

        repair.add(a.getKilometers());
        repair.add(a.getDiscreption());
        repair.add(a.getWorkshop());
        repair.add(a.getDate());
        repair.add(a.getChanges());
        repair.add(a.getPrice());
        repair.add(a.getReceipt_Number());
        repair.add(a.getWorkPrice());
        String sql;
        if (edit == false) {
            sql = "INSERT INTO Repairs(id,Discreption,Kilometers,Date,Changes,Workshop,Price,Receipt_Number,WorkPrice) VALUES (?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT OR REPLACE INTO Repairs(id,Discreption,Kilometers,Date,Changes,Workshop,Price,Receipt_Number,WorkPrice,Repair_Id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setString(2, repair.get(1));
            pstmt.setInt(3, Integer.valueOf(repair.get(0)));
            pstmt.setString(4, repair.get(3));
            pstmt.setString(5, repair.get(4));
            pstmt.setString(6, repair.get(2));
            pstmt.setInt(7, Integer.valueOf(repair.get(5)));
            pstmt.setString(8,repair.get(6));
            pstmt.setInt(9,Integer.valueOf(repair.get(7)));
            if (edit == true) {
                pstmt.setInt(10, Integer.valueOf(a.getId()));
            }
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
    public int InsertExternalCars(ModelExternalCars a, boolean edit) {
        ArrayList<String> repair = new ArrayList<>();

        repair.add(a.getLiscPlate());
        repair.add(GetIdFromComp(a.getCompany()));
        repair.add(a.getModel());
        repair.add(a.getManufactor());
        repair.add(a.getDriver());
        repair.add(a.getPhone());
        repair.add(a.getWidth());
        repair.add(a.getLenght());
        repair.add(a.getHeight());
        String sql;
        if (edit == false) {

            sql = "INSERT INTO External_Trucks(LiscPlate,Company_id,Model,Manufactor,Driver,Phone,Width,Lenght,Height) VALUES (?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT OR REPLACE INTO External_Trucks(LiscPlate,Company_id,Model,Manufactor,Driver,Phone,Width,Lenght,Height,id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, repair.get(0));
            pstmt.setInt(2, Integer.valueOf(repair.get(1)));
            pstmt.setString(3, repair.get(2));
            pstmt.setString(4, repair.get(3));
            pstmt.setString(5, repair.get(4));
            pstmt.setString(6, repair.get(5));
            pstmt.setInt(7, Integer.valueOf(repair.get(6)));
            pstmt.setInt(8, Integer.valueOf(repair.get(7)));
            pstmt.setInt(9, Integer.valueOf(repair.get(8)));
            if (edit == true) {
                pstmt.setInt(10, Integer.valueOf(a.getId()));
            }
            pstmt.executeUpdate();
            return 1;


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int InsertExternalPhone(ModelExternalPhoneList a, boolean edit) {
        ArrayList<String> list = new ArrayList<>();

        list.add(a.getName());

        list.add(a.getPhone());
        list.add(a.getAddress());
        list.add(a.getDiscreption());
        list.add(a.getNotes());
        list.add(a.getEmail());
        String sql;
        sql = "INSERT OR REPLACE INTO ExternalPhoneList(Name,Phone,Address,Discreption,Notes,Email) VALUES(?,?,?,?,?,?)";
        if (edit == true) {
            sql = "INSERT OR REPLACE INTO ExternalPhoneList(Name,Phone,Address,Discreption,Notes,Email,id) VALUES(?,?,?,?,?,?,?)";
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                pstmt.setString(i+1, list.get(i));
            }
            if (edit == true) {
                pstmt.setInt(7, Integer.valueOf(a.getId()));
            }
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int InsertInternalPhone(ModelInternalPhoneList a, boolean edit) {
        ArrayList<String> list = new ArrayList<>();

        list.add(a.getName());
        list.add(a.getPhone());
        list.add(a.getEmail());
        list.add(a.getPosistion());
        String sql;
        sql = "INSERT OR REPLACE INTO InternalPhoneList(Name,Phone,Email,Posistion) VALUES(?,?,?,?)";
        if (edit == true) {
            sql = "INSERT OR REPLACE INTO PhonePhoneList(id,Name,Phone,Email,Posistion) VALUES(?,?,?,?,?)";
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int l = 0;
            if (edit == true) {
                l = 1;
                pstmt.setInt(1, Integer.valueOf(a.getId()));
            }
            for (int i = l; i < list.size(); i++) {
                pstmt.setString(i + 1, list.get(i));
            }
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }


    /**
     * This method inserts a new Service on the db
     *
     * @param a the new service
     * @return 1 if the insertion is complete or 0 if there is an error
     */
    public int InsertService(ModelService a, boolean edit) {
        ArrayList<String> repair = new ArrayList<>();

        repair.add(a.getKilometers());
        repair.add(a.getType());
        repair.add(a.getWorkshop());
        repair.add(a.getDate());
        repair.add(a.getChanges());
        repair.add(a.getNextDate());
        repair.add(a.getNextKilometers());
        repair.add(a.getPrice());
        repair.add(a.getReceiptNum());
        repair.add(a.getWorkPrice());
        String sql;
        if (edit == false) {
            sql = "INSERT OR REPLACE INTO Service(id,Type,Kilometers,Date,Changes,Workshop,Next_Date,Next_Kilometers,Price,Receipt_Number,WorkPrice) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT OR REPLACE INTO Service(id,Type,Kilometers,Date,Changes,Workshop,Next_Date,Next_Kilometers,Price,Receipt_Number,WorkPrice,Service_Id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        }
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
            pstmt.setString(10,repair.get(8));
            pstmt.setInt(11,Integer.valueOf(repair.get(9)));
            if (edit == true) {
                pstmt.setInt(12, Integer.valueOf(a.getId()));
            }
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
            int i = InstertEmmisionCard(new ModelEmmisionCard(a.getLiscPlate(), a.getKilometers(), a.getDate(), "TRUE", EmmisionDate));
            if (i == 0) {
                return 0;
            }

            ResultSet rs = null;
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getWarnings());
            repair.add(a.getDate());
            repair.add(a.getCompany());
            repair.add(a.getNext());
            repair.add(a.getPrice());
            String sql = "INSERT OR REPLACE INTO KTEO(id,Warnings,Kilometers,Date,Company,DateNext,Price) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setString(2, repair.get(1));
            pstmt.setInt(3, Integer.valueOf(repair.get(0)));
            pstmt.setString(4, repair.get(2));
            pstmt.setString(5, repair.get(3));
            pstmt.setString(6, repair.get(4));
            pstmt.setString(7, repair.get(5));
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * This method inserts a new Emmision card
     *
     * @param a The new emmision card
     * @return 1 if comleted 0 if not
     */
    public int InstertEmmisionCard(ModelEmmisionCard a) {
        try {
            int i;
            boolean bool;
            if (a.getWithKTEO().equals("TRUE")) {
                bool = true;
            } else {
                bool = false;
            }
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getDate());
            repair.add(a.getNext());
            String sql = "INSERT OR REPLACE INTO EmmisionCard(id,Kilometers,Date,NextDate,WithKTEO) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setInt(2, Integer.valueOf(repair.get(0)));
            pstmt.setString(3, repair.get(1));
            pstmt.setString(4, repair.get(2));
            pstmt.setBoolean(5, bool);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * This method inserts a new Refill card
     *
     * @param a The new Refill
     * @return 1 if comleted 0 if not
     */
    public int InstertRefill(ModelRefill a) {
        try {
            String sql;
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getKilometers());
            repair.add(a.getDate());
            repair.add(a.getAmount());
            if (a.getConsumption() != null && a.getCost() != null) {
                repair.add(a.getConsumption());
                repair.add(a.getCost());
                sql = "INSERT INTO Refill(Car_id,Kilometers,Date,Amount,Consumption,Cost) VALUES (?,?,?,?,?,?)";
            } else {
                sql = "INSERT INTO Refill(Car_id,Kilometers,Date,Amount) VALUES (?,?,?,?)";
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(GetIdFromLisx(a.getLiscPlate())));
            pstmt.setInt(2, Integer.valueOf(repair.get(0)));
            pstmt.setString(3, repair.get(1));
            pstmt.setString(4, repair.get(2));
            if (a.getConsumption() != null && a.getCost() != null) {
                pstmt.setString(5, repair.get(3));
                pstmt.setString(6, repair.get(4));
            }
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int InsertSecondary(String Data, String Table) {
        try {
            String sql;
            if (Table.equals("Locations")) {
                sql = "INSERT INTO Location(City) VALUES (?)";
            } else if (Table.equals("Posistion")) {
                sql = "INSERT INTO CompanyPosistions(Posistion) VALUES (?)";
            }else if(Table.equals("Partners")){
                sql = "INSERT INTO PartersType(Type) VALUES (?)";
            }
            else {
                sql = "INSERT INTO Type(Type) VALUES (?)";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Data);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * This method inserts a new Company
     *
     * @param a The new Company
     * @return 1 if comleted 0 if not
     */
    public int InstertCompany(ModelCompany a, boolean edit) {
        try {
            ArrayList<String> repair = new ArrayList<>();
            repair.add(a.getName());
            repair.add(a.getPhone());
            repair.add(a.getPrices());
            String sql;
            if (edit == true) {
                sql = "INSERT OR REPLACE INTO Companies(Name,Phone,Prices,id) VALUES (?,?,?,?)";
            } else {
                sql = "INSERT INTO Companies(Name,Phone,Prices) VALUES (?,?,?)";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, repair.get(0));
            pstmt.setString(2, repair.get(1));
            pstmt.setString(3, repair.get(2));
            if (edit == true) {
                pstmt.setInt(4, Integer.valueOf(a.getId()));
            }
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int DeleteSecondary(String Data, String Table) {
        try {
            String sql;
            if (Table.equals("Locations")) {
                sql = "DELETE FROM Location WHERE City=?";
            } else if (Table.equals("Posistion")) {
                sql = "DELETE FROM CompanyPosistions WHERE Posistion=?";
            } else if(Table.equals("Partners")){
                sql = "DELETE FROM PartersType WHERE Type=?";
            }
            else {
                sql = "DELETE FROM Type WHERE Type=?";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Data);
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
    public int DeleteCar(ModelTruck a) {
        ArrayList<String> car = new ArrayList<>();

        car.add(a.getLiscPlate());
        car.add(a.getManufactor());
        car.add(a.getModel());
        car.add(a.getKilometers());
        car.add(a.getPlaisio());
        car.add(a.getLocation());
        car.add(a.getType());
        car.add(a.getDate());
        car.add(a.getData());
        car.add(a.getGasIn());
        car.add(a.getKteoIn());
        car.add(a.getServiceInkm());
        car.add(a.getFireExtinguiser());
        car.add(a.getSpeedWriter());



        String sql = "INSERT OR REPLACE INTO Trucks(id,LiscPlate,Manufactor,Model,Kilometers,Plaisio,Location,Type,First_Date,Data,GasIn,KTEOIn,ServiceInKm,FireExtiguiser,SpeedWriter,Deleted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(a.getId()));
            pstmt.setString(2, car.get(0));
            pstmt.setString(3, car.get(1));
            pstmt.setString(4, car.get(2));
            pstmt.setInt(5, Integer.valueOf(car.get(3)));
            pstmt.setString(6, car.get(4));
            pstmt.setString(7, car.get(5));
            pstmt.setString(8, car.get(6));
            pstmt.setString(9, car.get(7));
            pstmt.setString(10, car.get(8));
            pstmt.setString(11, car.get(10));
            pstmt.setString(12, car.get(9));
            pstmt.setInt(13, Integer.valueOf(car.get(11)));
            pstmt.setString(14,car.get(12));
            pstmt.setString(15,car.get(13));
            pstmt.setBoolean(16,true);
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
    public int DeleteExternal_Truck(int id) {
        String stmt = "DELETE FROM External_Trucks WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection (Not always but sometimes)
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int DeleteExternalPhone(int id) {
        String stmt = "DELETE FROM ExternalPhoneList WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection (Not always but sometimes)
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int DeleteInternalPhone(int id) {
        String stmt = "DELETE FROM InternalPhoneList WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection (Not always but sometimes)
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
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
    public int DeleteCompanies(int id) {
        String stmt = "DELETE FROM Companies WHERE id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            Class.forName("org.sqlite.JDBC");   //The is an error with Java Sqlite when you do multiple Deletes it crashes if you dont close and reopen connection (Not always but sometimes)
            conn = DriverManager.getConnection("jdbc:sqlite:PrTrucks.db");
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
        String stmt = "DELETE FROM Repairs WHERE Repair_Id=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, RepairId);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
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
     * This method return the Name of the Company  given  its id
     *
     * @param id The id of the Comnpany
     * @return The Name of the Company
     */
    public String GetCompFromId(String id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  Name FROM Companies  WHERE id=" + id;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs.getString("Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method return the id of the Company  given  its Name
     *
     * @param Names The name of the Comnpany
     * @return The id of the Company
     */
    public String GetIdFromComp(String Names) {
        ResultSet rs = null;
        try {
            String sql = "SELECT  id FROM Companies  WHERE Name=" + "\"" + Names + "\"";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            return rs.getString("id");
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


    public void Disconnect() {
        try {

            conn.close();
            System.out.println("Close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
