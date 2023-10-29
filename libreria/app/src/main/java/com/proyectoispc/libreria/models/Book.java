package com.proyectoispc.libreria.models;

public class Book {

    private int id;
    private String name;
    private String author;
    private String description;
    private String cover;
    private int coverID;
    private double price;
    private String tag;

    public Book() {
    }

    public Book(int id, String name, String author, String description, String cover, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.price = price;
    }

    public Book(int id, String name, String author, String description, int coverID, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.coverID = coverID;
        this.price = price;
    }

    public Book(int id, String name, String author, String description, String cover, double price, String tag) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.price = price;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCoverID() {
        return coverID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }
}
