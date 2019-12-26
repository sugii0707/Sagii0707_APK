package com.example.sagii0707;

public class Product {
    private int id;
    private String english;
    private String mongolian;
    private String image;

    public Product(int id, String english, String mongolian, String image) {
        this.id = id;
        this.english = english;
        this.mongolian = mongolian;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getEnglish() {
        return english;
    }

    public String getMongolian() {
        return mongolian;
    }

    public String getImage() {
        return image;
    }

}
