package brs;

import java.sql.SQLException;

public interface Type {

    public String Sell(int id_, String name_, int price_, int quantity_, Database db_);

    public String add_sellReq(String Symbol);

    public String Buy(int id_, String name_, int price_, int quantity_, Database db_);

    public String add_buyReq(String symbol);

    public String doDeal(String symbol) throws SQLException;
}