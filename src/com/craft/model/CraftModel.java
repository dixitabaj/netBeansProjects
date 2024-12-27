/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.craft.model;

/**
 *
 * @author dixitabajracharya
 */
public class CraftModel {
    String productId;
    String name;
    float price;
    int stock;
    String category;
    String condition;
    String status;
    String dateCreated;
    float weight;
    int totalSales;  

    
    //Constructor to initialize the CraftModel object
    public CraftModel(String productId, String name, float price, int stock, String category, int totalSales, String status, String dateCreated, float weight){
        this.productId=productId;
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.category=category;
        this.totalSales=totalSales;
        this.status = status;
        this.weight = weight;
        this.dateCreated=dateCreated;
    }
    
//getter methods
    public int getTotalSales() {
        return totalSales;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public float getWeight() {
        return weight;
    }
    

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }
    public String getStatus(){
        return status;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
