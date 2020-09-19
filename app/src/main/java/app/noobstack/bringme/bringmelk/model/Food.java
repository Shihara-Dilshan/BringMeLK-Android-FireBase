package app.noobstack.bringme.bringmelk.model;

public class Food {
    private String image;
    private String title;
    private String description;
    private String price;
    private String discount;
    private String id;

    public Food() {
    }

    public Food(String image, String title, String description, String price, String discount, String id) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}