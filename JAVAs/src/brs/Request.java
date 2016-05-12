package brs;

public class Request{
    public Customer cstmr;
    public Symbol symbl;
    public String type;
    public boolean forSale;
    public int quantity;
    public int price;

    Request(Customer cstmr_,Symbol symbl_,String type_,boolean forSale_,int quantity_,int price_) {
        cstmr = cstmr_;
        symbl = symbl_;
        type = type_;
        forSale = forSale_;
        quantity = quantity_;
        price = price_;
    }

}
