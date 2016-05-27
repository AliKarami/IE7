package brs;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public class Database {
    static GTC gtc = new GTC();
//    static MPO mpo = new MPO();
//    static IOC ioc = new IOC();
    static HSQLHandler hh = new HSQLHandler();

    public int LoggedInID = -1;
    public String LoggedInRole = "NU";

    private static Database theDatabase = new Database();
    public static Database getDB() {
        return theDatabase;
    }

    Database() {
        hh.init_tables();
        hh.executeUpdate("INSERT INTO Customer VALUES (1,'admin','',0,'AD')");
    }

    public void logInUser(int id) {
        try {
            LoggedInID = id;
            ResultSet rs = get_user(id);
            if (rs.next())
                LoggedInRole = rs.getString("Role");
        } catch (SQLException ex) {System.err.println("setting logged user error");}
    }

    public void logOutUser() {
        LoggedInID = -1;
        LoggedInRole = "NU";
    }

    public ResultSet get_user(int id_) {
        return hh.executeQuery("SELECT * FROM Customer WHERE cstmr_id=" + id_);
    }

    public void add2log (String Content) {
        hh.executeUpdate("INSERT INTO Log (desc) values ('" + Content  + "')");
    }

    public Vector<String> getLog() {
        ResultSet rs = hh.executeQuery("SELECT * FROM Log");
        Vector<String> res = new Vector<String>();
        try {
            while (rs.next()) {
                String desc = rs.getString("desc");
                res.add(desc);
            }
            return res;
        } catch (Exception ex) {System.err.println("Log Bug :D");}
        return null;
    }

    public Vector<Integer[]> getDepReqs() {
        ResultSet rs = hh.executeQuery("SELECT * FROM DepReqs");
        Vector<Integer[]> res = new Vector<Integer[]>();
        try {
            while (rs.next()) {
                int cstmr_id = Integer.parseInt(rs.getString("cstmr_id"));
                int amount = Integer.parseInt(rs.getString("amount"));
                Integer[] tmp = new Integer[2];
                tmp[0]=cstmr_id;
                tmp[1]=amount;
                res.add(tmp);
            }
            return res;
        } catch (Exception ex) {System.err.println("Log Bug :D");}
        return null;
    }

    public boolean add2DepReqs (int id, int amount) {
        try {
            if (get_user(id).next()) {
                if (hh.executeUpdate("INSERT INTO DepReqs (cstmr_id,amount) values (" + id + "," + amount + ")") == 1) {
                    return true;
                } else
                    return false;
            }
            return false;
        } catch (Exception ex) {
            System.err.println("add2Reqs Error!");
            return false;
        }
    }

    public void appDR(int cstmrid_,int amount_) {
        ResultSet rs = hh.executeQuery("SELECT * FROM DepReqs WHERE cstmr_id=" + cstmrid_ + " AND amount=" + amount_);
        try {
            if (rs.next()) {
                deposit_customer(cstmrid_,amount_);
                hh.executeUpdate("DELETE FROM DEPREQS WHERE CSTMR_ID=" +cstmrid_+ " AND AMOUNT=" +amount_+ " AND rownum() <= 1");
            }
            else System.err.println("DepReq is fake!");
        } catch (Exception ex) {System.err.println("appDepReq err");}
    }

    public void decDR(int cstmrid_,int amount_) {
        if (hh.executeUpdate("DELETE FROM DEPREQS WHERE CSTMR_ID=" +cstmrid_+ " AND AMOUNT=" +amount_+ " AND rownum() <= 1") != 1)
            System.err.println("decDepReq err");
    }

    public boolean add_customer(int id_,String name_,String family_) {
        try {
            if (hh.executeUpdate("INSERT INTO Customer values (" + id_ + ",'" + name_ + "','" + family_ + "',0,'NO')") == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("add cstmr err"); return false;}

    }

    public boolean deposit_customer(int id_,int amount){
        try {

            if (hh.executeUpdate("UPDATE Customer SET fund = fund+" + amount + " WHERE cstmr_id=" + id_) == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("err depositCstmr"); return false;}
    }
    public int withdraw_customer(int id_,int amount) {
        ResultSet rs = get_user(id_);
        try {
            if (rs.next()) {
                int current_fund = Integer.parseInt(rs.getString("fund"));
                if (current_fund >= amount) {
                    if (hh.executeUpdate("UPDATE Customer SET fund = fund-" + amount + " WHERE cstmr_id=" + id_) == 1)
                        return 0; // Successful
                    return -2;
                } else {
                    return -1; //Not enough money
                }
            } else
                return -2; //Unknown user id
        } catch (Exception ex) {System.err.println("err on withdraw cstmr"); return -2;}

    }

    public ResultSet get_symbol(String name_) {
        ResultSet rs = hh.executeQuery("SELECT * FROM Symbol WHERE symb_name='" + name_ + "' AND status=TRUE");
        return rs;
    }

    public Vector<String> getSymbs () {
        ResultSet rs = hh.executeQuery("SELECT * FROM Symbol WHERE status=TRUE");
        Vector<String> SymbNames = new Vector<String>();
        try {
            while (rs.next()) {
                SymbNames.add(rs.getString("symb_name"));
            }
        } catch (SQLException ex) {System.err.println("error on getting symbols");}
        return SymbNames;
    }

    public boolean add_symbol(String name_) {
        try {
            if (hh.executeUpdate("INSERT INTO Symbol values ('"  + name_ + "',FALSE)") == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("add symbol err"); return false;}

    }

    public boolean app_symbol(String name_) {
        try {
            if (hh.executeUpdate("UPDATE Symbol SET status = TRUE WHERE symb_name=" + name_) == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("app symbol err"); return false;}

    }

    public boolean del_symbol(String name_) {
        try {
            if (hh.executeUpdate("DELETE FROM Symbol WHERE symb_name=" + name_ + "AND status = FALSE") == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("delete symbol err"); return false;}

    }

    public void add_property(int id,String name,int quantity){
        try {
            hh.executeUpdate("INSERT INTO Properties (cstmr_id,symb_name,amount) values (" + id + ",'" + name + "'," + quantity + ")");
        } catch (Exception ex) {System.err.println("add property err");}
    }

    public void add_request(int id,String name,String type,boolean forSale,int quantity,int price,int state){

    }

    public int get_propertyAmount(int id,String name){
        ResultSet rs = hh.executeQuery("SELECT * FROM Properties WHERE cstmr_id=" + id + " and symb_name='" + name + "'");
        try {
            if(rs.next())
                return Integer.parseInt(rs.getString("amount"));
            return -1;
        } catch (Exception ex) {System.err.println("add property err");return -1;}
    }

    public ResultSet getSeller(String name_) {
        try {
            return Database.getDB().hh.executeQuery("Select * FROM Sellers WHERE symb_name='" + name_ + "' AND state=0 ORDER BY fund ASC");
        } catch (Exception ex) {System.out.println("err on getSeller " + name_); return null;}
    }

    public ResultSet getBuyer(String name_) {
        try {
            return Database.getDB().hh.executeQuery("Select * FROM Buyers WHERE symb_name='" + name_ + "' AND state=0 ORDER BY fund DESC");
        } catch (Exception ex) {System.out.println("err on getBuyer " + name_); return null;}
    }
}