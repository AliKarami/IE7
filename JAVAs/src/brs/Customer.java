package brs;

import java.util.LinkedHashMap;
import java.util.Vector;

public class Customer {
	public int id;
	public String name;
	public String family;
	public int fund;

    LinkedHashMap<String,Integer> property = new LinkedHashMap<String,Integer>();
	Vector<Request> done;
	Vector<Request> refused;
	Vector<Request> inAct;

	Customer(int id_, String name_, String family_) {
		id = id_;
		name = name_;
		family = family_;
        int fund = 0;
		done = new Vector<Request>();
        refused = new Vector<Request>();
        inAct = new Vector<Request>();
	}
}
