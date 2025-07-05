package org.example.task2;

public class Book {
String title;
String author;

Book(String title, String author){
    this.author = author;
    this.title = title;
}

String getTitle(){
    return this.title;
}

String getAuthor(){
    return this.author;
}

void setTitle(String title){
    this.title = title;
}

void setAuthor(String author){
    this.author = author;
}

void printInfo(){
    System.out.println("Автора: " + this.author + ". Название книги: " + this.title);
}
}


