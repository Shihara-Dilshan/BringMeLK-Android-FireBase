package app.noobstack.bringme.bringmelk.model;

import java.sql.Driver;

public class CompletedOrders {

    String Address;
    String DeliveryChargers;
    String ItemName;
    String ItemPrice;
    String Quantity;
    String MobileNumber;
    String BuyerName;
    String DriverID;
    public CompletedOrders(){

    }
    public CompletedOrders(String address, String deliverychargers, String itemName, String itemPrice, String mobileNumber, String quantity, String buyername, String driverid) {
        Address = address;
        DeliveryChargers = deliverychargers;
        ItemName = itemName;
        ItemPrice = itemPrice;
        Quantity = quantity;
        MobileNumber= mobileNumber;
        BuyerName=buyername;
        DriverID=driverid;
    }
    public String getDriverID() {
        return DriverID;
    }

    public void setDriverID(String driverID) {
        DriverID = driverID;
    }
    public String getBuyerName() {
        return BuyerName;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
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

    public String getDeliveryChargers() {
        return DeliveryChargers;
    }

    public void setDeliveryChargers(String deliveryChargers) {
        DeliveryChargers = deliveryChargers;
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
