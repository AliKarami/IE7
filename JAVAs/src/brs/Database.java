package brs;

import java.sql.ResultSet;
import java.util.*;



public class Database {
    private Vector<Customer> list = new Vector<Customer>();
    private Vector<Symbol> symbs = new Vector<Symbol>();
    private Vector<String> log = new Vector<String>();
    private Vector<Integer[]> DepReqs = new Vector<Integer[]>();
    static GTC gtc = new GTC();
    static MPO mpo = new MPO();
    static IOC ioc = new IOC();

    HSQLHandler hh = new HSQLHandler();

    private static Database theDatabase = new Database();
    public static Database getDB() {
        return theDatabase;
    }

    Database() {
            hh.init_tables();
            //Customer Admin = new Customer(1,"admin","");
            //list.add(Admin);
            hh.executeUpdate("INSERT INTO Customer VALUES (1,'admin','',0)");
    }

//    public Customer get_user(int id_){
//        for (Customer cstmr : list) {
//            if (cstmr.id == id_)
//                return cstmr;
//        }
//        return null;
//    }

    public ResultSet get_user(int id_) {
        return hh.executeQuery("SELECT * FROM Customer WHERE cstmr_id=" + id_);
    }

    public void add2log (String Content) {
//        log.add(Content);
        hh.executeUpdate("INSERT INTO Log (desc) values ('" + Content  + "')");
    }

    public Vector<String> getLog() {
//        return log;
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
//        return DepReqs;
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
//                Integer[] temp = new Integer[2];
//                temp[0]=id;
//                temp[1]=amount;
//                DepReqs.add(temp);
//                return true;
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

    public void appDR(int i) {
//        int id_ = DepReqs.get(i)[0];
//        int amount_ = DepReqs.get(i)[1];
        ResultSet rs = hh.executeQuery("SELECT * FROM DepReqs WHERE DepReq_id=" + i);
        try {
            if (rs.next()) {
                int cstmr_id = Integer.parseInt(rs.getString("cstmr_id"));
                int amount = Integer.parseInt(rs.getString("amount"));
                deposit_customer(cstmr_id,amount);
                hh.executeUpdate("DELETE FROM DepReqs WHERE DepReq_id=" + i);
            }
        } catch (Exception ex) {System.err.println("appDepReq err");}
//        deposit_customer(id_,amount_);
//        DepReqs.remove(i);
    }

    public void decDR(int i) {
//        DepReqs.remove(i);
          if (hh.executeUpdate("DELETE FROM DepReqs WHERE DepReq_id=" + i) != 1)
              System.err.println("decDepReq err");
    }

    public boolean add_customer(int id_,String name_,String family_) {
//        for (Customer cstmr : list) {
//            if (cstmr.id==id_)
//                return false;
//        }
//        Customer newcstmr = new Customer(id_,name_,family_);
//        list.add(newcstmr);
//        return true;
        try {
            if (hh.executeUpdate("INSERT INTO Customer values (" + id_ + "," + name_ + "," + family_ + ",0)") == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("add cstmr err"); return false;}

    }

    public boolean deposit_customer(int id_,int amount){
//        for (Customer cstmr : list) {
//            if (cstmr.id==id_) {
//                cstmr.fund += amount;
//                return true;
//            }
//        }
//        return false;
        try {

            if (hh.executeUpdate("UPDATE Customer SET fund = fund +" + amount + " WHERE cstmr_id=" + id_) == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("err depositCstmr"); return false;}
    }
    public int withdraw_customer(int id_,int amount) {
//        for (Customer cstmr : list) {
//            if (cstmr.id==id_) {
//                if (cstmr.fund >= amount) {
//                    cstmr.fund -= amount;
//                    return 0; // Successful
//                } else {
//                    return -1; //Not enough money
//                }
//            }
//        }
//        return -2; //Unknown user id
        ResultSet rs = get_user(id_);
        try {
            if (rs.next()) {
                int current_fund = Integer.parseInt(rs.getString("fund"));
                if (current_fund >= amount) {
                    if (hh.executeUpdate("UPDATE Customer SET fund = fund -" + amount + " WHERE cstmr_id=" + id_) == 1)
                        return 0; // Successful
                    return -2;
                } else {
                    return -1; //Not enough money
                }
            } else
                return -2; //Unknown user id
        } catch (Exception ex) {System.err.println("err on withdraw cstmr");}

    }

    public ResultSet get_symbol(String name_) {
//        for (Symbol sym : symbs) {
//            if (sym.name.equals(name_))
//                return sym;
//        }
//        return null;
        ResultSet rs = hh.executeQuery("SELECT * FROM Customer WHERE symb_name=" + name_);
        try {
            if (rs.next())
                return rs;
            else
                return null;
        } catch (Exception ex) {System.err.println("err on getsymb")}
    }

    public ResultSet getSymbs () {//return symbs;
        return hh.executeQuery("SELECT * FROM Symbol");
    }

    public boolean add_symbol(String name_) {
//        Symbol newSymbl = new Symbol(name_);
//        symbs.add(newSymbl);
        try {
            if (hh.executeUpdate("INSERT INTO Symbol values ("  + name_ + ")") == 1)
                return true;
            return false;
        } catch (Exception ex) {System.err.println("add symbol err"); return false;}

    }

}
