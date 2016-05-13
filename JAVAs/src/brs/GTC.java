package brs;

import java.sql.*;

/**
 * Created by ali on 2/25/16.
 */
public class GTC implements Type {
    @Override
    public String Sell(int id_, String name_, int price_, int quantity_, Database db_) {
        ResultSet seller = db_.get_user(id_);
        ResultSet stock = db_.get_symbol(name_);
        if (id_ == 1) { //handle admins POWER!!!
            if (stock == null) {
                db_.add_symbol(name_);
                stock = db_.get_symbol(name_);
            }
            // seller.property.put(name_,quantity_);
            db_.add_property(id_,name_,quantity_);
        }
        //Request req = new Request(seller,stock,"GTC",true,quantity_,price_);
        try{
            if (!seller.next())
                return "Unknown user id";
            else if (!stock.next()) {
                return "Invalid symbol id";
            }
            else if (db_.get_propertyAmount(id_,name_) < quantity_) {
                //  seller.refused.add(req);
                db_.add_request(id_,name_,"GTC",true,quantity_,price_,-1);
                return "Not enough share";
            }
            else {
                //seller.inAct.add(req);
                db_.add_request(id_,name_,"GTC",true,quantity_,price_,0);
                return add_sellReq(name_);
            }
        }catch (Exception ex){return "add property err";}
    }

    @Override
    public String add_sellReq(String symbol)  {
        // req_.symbl.getSeller().add(req_);
        ResultSet rs = Database.getDB().getSeller(symbol); // to be implemented
        // if (!(req_.equals(req_.symbl.getSeller().iterator().next())))
        try{
            if(!rs.next())
                return "Order is queued";
            else {
                StringBuilder sb = new StringBuilder("");
                while (true) {
                    String current = doDeal(symbol);
                    if (current.equals("nothing Dealed!"))
                        break;
                    else {
                        sb.append(current + "\n");
                    }
                }
                String result = sb.toString();
                if (result.equals(""))
                    return "Order is queued";
                else
                    return result;
            }
        }catch(Exception ex){return "Order Sql Error";}

    }

    @Override
    public String Buy(int id_, String name_, int price_, int quantity_, Database db_) {
        ResultSet buyer = db_.get_user(id_);
        ResultSet stock = db_.get_symbol(name_);
        try{
            if (!buyer.next())
                return "Unknown user id";
            else if (!stock.next()) {
                return "Invalid symbol id";
            }
            else if (Integer.parseInt(buyer.getString("fund")) < (price_)*(quantity_)) {
                //  seller.refused.add(req);
                db_.add_request(id_,name_,"GTC",false,quantity_,price_,-1);
                return "Not enough money";
            }
            else {
                //seller.inAct.add(req);
                db_.add_request(id_,name_,"GTC",false,quantity_,price_,0);
                return add_buyReq(name_);
            }
        }catch (Exception ex){return "add property err";}
    }

    @Override
    public String add_buyReq(String symbol) {
        //req_.symbl.getBuyer().add(req_);
        ResultSet rs = Database.getDB().getBuyer(symbol);
        /*if (!(req_.equals(req_.symbl.getBuyer().iterator().next())))*/
        try{
            if(!rs.next())
                return "Order is queued";
            else {
                StringBuilder sb = new StringBuilder("");
                while (true) {

                    String current = doDeal(symbol);
                    if (current.equals("nothing Dealed!"))
                        break;
                    else {
                        sb.append(current + "\n");
                    }

                }
                String result = sb.toString();
                if (result.equals(""))
                    return "Order is queued";
                else
                    return result;
            }
        }catch(Exception ex){return "doDeal fucked up";}

    }

    @Override
    public String doDeal(String symbol) throws SQLException {
        ResultSet rs1 = Database.getDB().getBuyer(symbol);
        ResultSet rs2 = Database.getDB().getSeller(symbol);
        /* if (!req_.symbl.getBuyer().iterator().hasNext() || !req_.symbl.getSeller().iterator().hasNext())
            return "nothing Dealed!";*/
        if(!rs1.next() || !rs2.next()){
            return "nothing Dealed!";
        }
        ResultSet rs3 = Database.getDB().hh.executeQuery("SELECT price,quantity FROM Requests WHERE req_id=" + rs1.getString("req_id"));
        ResultSet rs4 = Database.getDB().hh.executeQuery("SELECT price,quantity FROM Requests WHERE req_id=" + rs2.getString("req_id"));
        /*Request firstBuyReq = req_.symbl.getBuyer().iterator().next();
        Request firstSellReq = req_.symbl.getSeller().iterator().next();*/
        int buy_price = Integer.parseInt(rs3.getString("price"));
        int sell_price = Integer.parseInt(rs4.getString("price"));
        int buy_quantity = Integer.parseInt(rs3.getString("quantity"));
        int sell_quantity = Integer.parseInt(rs4.getString("quantity"));
        int buyer_id = Integer.parseInt(rs1.getString("buyer_id"));
        int seller_id = Integer.parseInt(rs2.getString("seller_id"));
        if ( buy_price >= sell_price )
        {
            //if (firstBuyReq.quantity > firstSellReq.quantity) {
            if( buy_quantity > sell_quantity ){
                //int moneyExchanged = (firstSellReq.quantity) * (firstBuyReq.price);
                int moneyExchanged = (sell_quantity) * (buy_price);
                //firstBuyReq.cstmr.fund -= moneyExchanged;
                if(Database.getDB().withdraw_customer(buyer_id,moneyExchanged) != 0){
                    return "nothing Dealed! (Not enough Money)";
                }
                //firstSellReq.cstmr.fund += moneyExchanged;
                Database.getDB().deposit_customer(seller_id,moneyExchanged);
                //firstBuyReq.quantity -= firstSellReq.quantity;
                Database.getDB().hh.executeUpdate("UPDATE Requests SET quantity=quantity-" + sell_quantity + " WHERE req_id=" + rs1.getString("req_id") );
                //Exchange properties
                Database.getDB().hh.executeUpdate("MERGE INTO Properties AS t USING (VALUES('" + symbol + "'," + sell_quantity + "," + buyer_id + ")) AS vals(symb_name,amount,cstmr_id) \n" +
                        "        ON t.cstmr_id=vals.cstmr_id AND t.symb_name=vals.symb_name\n" +
                        "    WHEN MATCHED THEN UPDATE SET t.amount = t.amount+vals.amount\n" +
                        "    WHEN NOT MATCHED THEN INSERT VALUES vals.symb_name, vals.amount, vals.cstmr_id");
                Database.getDB().hh.executeUpdate("UPDATE Properties SET amount=amount-" + sell_quantity + " WHERE cstmr_id=" + seller_id );

                //req_.symbl.getSeller().remove(firstSellReq);

                // firstSellReq.cstmr.done.add(firstSellReq);
                Database.getDB().hh.executeUpdate("UPDATE Requests SET state=1 WHERE req_id=" + rs2.getString("req_id"));
                //firstSellReq.cstmr.inAct.remove(firstSellReq);
                Database.getDB().add2log(buyer_id + "," + seller_id + "," + symbol + ",GTC," + sell_quantity + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged) + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged));
                return seller_id + " sold " + sell_quantity + " shares of " + symbol + " @" + buy_price + " to " + buyer_id;
            }// else if (firstBuyReq.quantity == firstSellReq.quantity) {
            else if (buy_quantity == sell_quantity) {
                int moneyExchanged = (sell_quantity) * (buy_price);
                //firstBuyReq.cstmr.fund -= moneyExchanged;
                if(Database.getDB().withdraw_customer(buyer_id,moneyExchanged) != 0){
                    return "nothing Dealed! (Not enough Money)";
                }
                //firstSellReq.cstmr.fund += moneyExchanged;
                Database.getDB().deposit_customer(seller_id,moneyExchanged);
                //req_.symbl.getBuyer().remove(firstBuyReq);

                // req_.symbl.getSeller().remove(firstSellReq);

                //firstBuyReq.cstmr.done.add(firstBuyReq);
                Database.getDB().hh.executeUpdate("UPDATE Requests SET state=1 WHERE req_id=" + rs1.getString("req_id"));
                //firstSellReq.cstmr.done.add(firstSellReq);
                Database.getDB().hh.executeUpdate("UPDATE Requests SET state=1 WHERE req_id=" + rs2.getString("req_id"));

                //Exchange properties
                Database.getDB().hh.executeUpdate("MERGE INTO Properties AS t USING (VALUES('" + symbol + "'," + buy_quantity + "," + buyer_id + ")) AS vals(symb_name,amount,cstmr_id) \n" +
                        "        ON t.cstmr_id=vals.cstmr_id AND t.symb_name=vals.symb_name\n" +
                        "    WHEN MATCHED THEN UPDATE SET t.amount = t.amount+vals.amount\n" +
                        "    WHEN NOT MATCHED THEN INSERT VALUES vals.symb_name, vals.amount, vals.cstmr_id");
                Database.getDB().hh.executeUpdate("UPDATE Properties SET amount=amount-" + sell_quantity + " WHERE cstmr_id=" + seller_id );


//                firstBuyReq.cstmr.inAct.remove(firstBuyReq);
//                firstSellReq.cstmr.inAct.remove(firstSellReq);
                Database.getDB().add2log(buyer_id + "," + seller_id + "," + symbol + ",GTC," + sell_quantity + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged) + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged));
                return seller_id + " sold " + sell_quantity + " shares of " + symbol + " @" + buy_price + " to " + buyer_id;
            } else {
                int moneyExchanged = (sell_quantity) * (buy_price);
                //firstBuyReq.cstmr.fund -= moneyExchanged;
                if(Database.getDB().withdraw_customer(buyer_id,moneyExchanged) != 0){
                    return "nothing Dealed! (Not enough Money)";
                }
                // firstSellReq.cstmr.fund += moneyExchanged;
                Database.getDB().deposit_customer(seller_id,moneyExchanged);
                //firstSellReq.quantity -= firstBuyReq.quantity;
                Database.getDB().hh.executeUpdate("UPDATE Requests SET quantity=quantity-" + buy_quantity + " WHERE req_id=" + rs2.getString("req_id") );
                //req_.symbl.getBuyer().remove(firstBuyReq);

                //firstBuyReq.cstmr.done.add(firstBuyReq);
                Database.getDB().hh.executeUpdate("UPDATE Requests SET state=1 WHERE req_id=" + rs1.getString("req_id"));
                // firstBuyReq.cstmr.inAct.remove(firstBuyReq);

                //Exchange properties
                Database.getDB().hh.executeUpdate("MERGE INTO Properties AS t USING (VALUES('" + symbol + "'," + buy_quantity + "," + buyer_id + ")) AS vals(symb_name,amount,cstmr_id) \n" +
                        "        ON t.cstmr_id=vals.cstmr_id AND t.symb_name=vals.symb_name\n" +
                        "    WHEN MATCHED THEN UPDATE SET t.amount = t.amount+vals.amount\n" +
                        "    WHEN NOT MATCHED THEN INSERT VALUES vals.symb_name, vals.amount, vals.cstmr_id");
                Database.getDB().hh.executeUpdate("UPDATE Properties SET amount=amount-" + buy_quantity + " WHERE cstmr_id=" + seller_id );

                Database.getDB().add2log(buyer_id + "," + seller_id + "," + symbol + ",GTC," + sell_quantity + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged) + "," + (Integer.parseInt(rs1.getString("fund")) - moneyExchanged));
                return seller_id + " sold " + sell_quantity + " shares of " + symbol + " @" + buy_price + " to " + buyer_id;
            }
        } else
            return "nothing Dealed!";
    }
}