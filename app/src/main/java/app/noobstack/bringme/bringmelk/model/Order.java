package app.noobstack.bringme.bringmelk.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    String buyer_Name;
    String buyer_Mobile;
    String buyer_Address;
    String requested_Time;
    String payment_status;
    String user_Id;
    String driver_Id;
    String prepared_Time;
    String delivered_time;
    String Total_Price;
    String order_Id;
    String image;
    String item_count;
    String item_name;
    String deliverCharge;
    String itemId;
    String itemDesc;
    String itemDiscount;
    String priceOriginal;

    public Order() {
    }

    public Order(String buyer_Name, String buyer_Mobile, String buyer_Address, String requested_Time, String payment_status, String user_Id, String driver_Id, String prepared_Time, String delivered_time, String total_Price, String order_Id, String image, String item_count, String item_name, String deliverCharge, String itemId, String itemDesc, String itemDiscount, String priceOriginal) {
        this.buyer_Name = buyer_Name;
        this.buyer_Mobile = buyer_Mobile;
        this.buyer_Address = buyer_Address;
        this.requested_Time = requested_Time;
        this.payment_status = payment_status;
        this.user_Id = user_Id;
        this.driver_Id = driver_Id;
        this.prepared_Time = prepared_Time;
        this.delivered_time = delivered_time;
        Total_Price = total_Price;
        this.order_Id = order_Id;
        this.image = image;
        this.item_count = item_count;
        this.item_name = item_name;
        this.deliverCharge = deliverCharge;
        this.itemId = itemId;
        this.itemDesc = itemDesc;
        this.itemDiscount = itemDiscount;
        this.priceOriginal = priceOriginal;
    }

    public String getBuyer_Name() {
        return buyer_Name;
    }

    public void setBuyer_Name(String buyer_Name) {
        this.buyer_Name = buyer_Name;
    }

    public String getBuyer_Mobile() {
        return buyer_Mobile;
    }

    public void setBuyer_Mobile(String buyer_Mobile) {
        this.buyer_Mobile = buyer_Mobile;
    }

    public String getBuyer_Address() {
        return buyer_Address;
    }

    public void setBuyer_Address(String buyer_Address) {
        this.buyer_Address = buyer_Address;
    }

    public String getRequested_Time() {
        return requested_Time;
    }

    public void setRequested_Time(String requested_Time) {
        this.requested_Time = requested_Time;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getDriver_Id() {
        return driver_Id;
    }

    public void setDriver_Id(String driver_Id) {
        this.driver_Id = driver_Id;
    }

    public String getPrepared_Time() {
        return prepared_Time;
    }

    public void setPrepared_Time(String prepared_Time) {
        this.prepared_Time = prepared_Time;
    }

    public String getDelivered_time() {
        return delivered_time;
    }

    public void setDelivered_time(String delivered_time) {
        this.delivered_time = delivered_time;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String total_Price) {
        Total_Price = total_Price;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDeliverCharge() {
        return deliverCharge;
    }

    public void setDeliverCharge(String deliverCharge) {
        this.deliverCharge = deliverCharge;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(String priceOriginal) {
        this.priceOriginal = priceOriginal;
    }
}
