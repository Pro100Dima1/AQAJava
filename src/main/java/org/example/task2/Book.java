package org.example.task2;

public class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    void printInfo(){
        System.out.println("Автор: " + author + ". Название книги: " + title);
    }


}


