package Entities;

public class Item {
    private int id;
    private String itemname, username, type;
    private byte[] image;

    public Item() {
    }

    public Item(String itemname, String username, String type, byte[] image) {
        this.itemname = itemname;
        this.username = username;
        this.type = type;
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public byte[] getImage() {
        return image;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
