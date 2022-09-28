package collectiblestore.model;

import collectiblestore.model.enums.ProductType;

import java.util.Date;

public class Product {
    private String name;
    private int stockQuantity;
    private ProductType type;
    private Date registerDate;

    public Product(String name, int stockQuantity, String type){
        this.name = name;
        this.stockQuantity = stockQuantity;

        switch (type){
            case "MUG":
                this.type = ProductType.MUG;
                break;
            case "STA":
                this.type = ProductType.STA;
                break;
            default:
                this.type = ProductType.ERR;
                System.out.println("ERROR INVALID TYPE!!");
                break;
        }
    }

    public String getName(){return  this.name;}
    public int getStockQuantity(){return  this.stockQuantity;}
    public String getType(){return this.type.name();}
    public Date getRegisterDate(){return this.registerDate;}

    public void setName(String name){this.name = name;}
    public void setStockQuantity(int stockQuantity){this.stockQuantity = stockQuantity;}
    public void setType(String type){
        switch (type){
            case "MUG":
                this.type = ProductType.MUG;
                break;
            case "STA":
                this.type = ProductType.STA;
                break;
            default:
                this.type = ProductType.ERR;
                System.out.println("ERROR INVALID TYPE!!");
                break;
        }
    }
    public void setRegisterDate(Date registerDate){this.registerDate = registerDate;}

    public String toString(){
        return "product.name: " + name + ", product.stockQuantity: " + stockQuantity + ", product.type: " + type.name() + ", product.registerDate: " + registerDate.toString() + ".";
    }
}
