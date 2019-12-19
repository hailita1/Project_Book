package com.codegym.model;

public class Book {
    private int id;
    private String name;
    private float price;
    private String author;
    private String NXB;
    private String img;

    public Book() {
    }

    public Book(int id, String name, float price, String author, String NXB, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.NXB = NXB;
        this.img = img;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
