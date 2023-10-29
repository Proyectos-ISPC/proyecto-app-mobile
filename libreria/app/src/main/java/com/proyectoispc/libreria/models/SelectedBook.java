package com.proyectoispc.libreria.models;

public class SelectedBook {

    private Book book;
    private int cuantity;

    public SelectedBook() {
    }

    public SelectedBook(Book book, int cuantity) {
        this.book = book;
        this.cuantity = cuantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }
}
