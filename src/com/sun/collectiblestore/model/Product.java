package com.sun.collectiblestore.model;

import com.sun.collectiblestore.model.enums.ProductType;

import java.util.Date;

public class Product {
    private int id;
    private double price;
    private String name;
    private int stockQuantity;
    private ProductType type;
    private Date registerDate;

    public Product(int id, String name, int stockQuantity, double price, String type){
        this.id = id;
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity >= 0 ? stockQuantity : 0;

        System.out.println(type);
        System.out.println(name);

        switch (type){
            case "CON":
                this.type = ProductType.CON;
                break;
            case "COL":
                this.type = ProductType.COL;
                break;
            default:
                this.type = ProductType.ERR;
                System.out.println("ERROR INVALID TYPE!!");
                break;
        }
    }

    public int getId(){
        return this.id;
    }
    public String getName(){return  this.name;}
    public int getStockQuantity(){return  this.stockQuantity;}
    public double getPrice(){return  this.price;}
    public String getType(){return this.type.name();}
    public Date getRegisterDate(){return this.registerDate;}

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPrice(Double price){this.price = price;}
    public void setStockQuantity(int stockQuantity){this.stockQuantity = stockQuantity;}
    public void setType(String type){
        switch (type){
            case "CON":
                this.type = ProductType.CON;
                break;
            case "COL":
                this.type = ProductType.COL;
                break;
            default:
                this.type = ProductType.ERR;
                System.out.println("ERROR INVALID TYPE!!");
                break;
        }
    }
    public void setRegisterDate(Date registerDate){this.registerDate = registerDate;}

    public String toString(){
        return "\n ================= \n product.name: " + name + "\n product.stockQuantity: " + stockQuantity + "\n product.type: " + type.name() + "\n product.registerDate: " + registerDate.toString() + "\n product.price: " + price + "\n product.id: " + id + "\n ================= \n";
    }

    public String storeString(){return  "\n| product.name: " + name + " | product.price: " + price + " | product.id: " + id +"| product quantity: "+ stockQuantity + " |\n";}
}
