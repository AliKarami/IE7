package brs;

import java.util.*;



public class Database {
    private Vector<Customer> list = new Vector<Customer>();
    private Vector<Symbol> symbs = new Vector<Symbol>();
    private Vector<String> log = new Vector<String>();
    private Vector<Integer[]> DepReqs = new Vector<Integer[]>();
    static GTC gtc = new GTC();
    static MPO mpo = new MPO();
    static IOC ioc = new IOC();


    private static Database theDatabase = new Database();
    public static Database getDB() {
        return theDatabase;
    }

    Database() {
        Customer Admin = new Customer(1,"admin","");
        list.add(Admin);
    }

    public Customer get_user(int id_){
        for (Customer cstmr : list) {
            if (cstmr.id == id_)
                return cstmr;
        }
        return null;
    }

    public void add2log (String Content) {
        log.add(Content);
    }

    public Vector<String> getLog() { return log; }

    public Vector<Integer[]> getDepReqs() {
        return DepReqs;
    }

    public boolean add2DepReqs (int id, int amount) {
        if (get_user(id)!=null) {
            Integer[] temp = new Integer[2];
            temp[0]=id;
            temp[1]=amount;
            DepReqs.add(temp);
            return true;
        }
        return false;
    }

    public void appDR(int i) {
        int id_ = DepReqs.get(i)[0];
        int amount_ = DepReqs.get(i)[1];
        deposit_customer(id_,amount_);
        DepReqs.remove(i);
    }

    public void decDR(int i) {
        DepReqs.remove(i);
    }

    public boolean add_customer(int id_,String name_,String family_) {
        for (Customer cstmr : list) {
            if (cstmr.id==id_)
                return false;
        }
        Customer newcstmr = new Customer(id_,name_,family_);
        list.add(newcstmr);
        return true;
    }

    public boolean deposit_customer(int id_,int amount){
        for (Customer cstmr : list) {
            if (cstmr.id==id_) {
                cstmr.fund += amount;
                return true;
            }
        }
        return false;
    }
    public int withdraw_customer(int id_,int amount) {
        for (Customer cstmr : list) {
            if (cstmr.id==id_) {
                if (cstmr.fund >= amount) {
                    cstmr.fund -= amount;
                    return 0; // Successful
                } else {
                    return -1; //Not enough money
                }
            }
        }
        return -2; //Unknown user id
    }

    public Symbol get_symbol(String name_) {
        for (Symbol sym : symbs) {
            if (sym.name.equals(name_))
                return sym;
        }
        return null;
    }

    public Vector<Symbol> getSymbs () {return symbs;}

    public void add_symbol(String name_) {
        Symbol newSymbl = new Symbol(name_);
        symbs.add(newSymbl);
    }

}
