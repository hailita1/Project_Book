package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class BookForm {
    private int id;
    private String name;
    private float price;
    private String author;
    private String NXB;
    private MultipartFile img;

    public BookForm() {
    }

    public BookForm(int id, String name, float price, String author, String NXB, MultipartFile img) {
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

    public void setPrice(float prince) {
        this.price = prince;
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

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
