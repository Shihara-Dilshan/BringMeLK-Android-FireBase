package app.noobstack.bringme.bringmelk.model;

public class CompletedOrders {

    String Address;
    String Distance;
    String ItemName;
    String ItemPrice;
    String Quantity;
    String MobileNumber;
    public CompletedOrders(){

    }
    public CompletedOrders(String address, String distance, String itemName, String itemPrice,String mobileNumber,String quantity) {
        Address = address;
        Distance = distance;
        ItemName = itemName;
        ItemPrice = itemPrice;
        Quantity = quantity;
        MobileNumber= mobileNumber;
    }
    public String getAddress() {
        return Address;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public void setAddress(String address) {
        Address = address;
    }
    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
