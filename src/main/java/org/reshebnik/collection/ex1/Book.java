package org.reshebnik.collection.ex1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Book {

    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 3 book name :");
        String firstBook = scanner.nextLine();
        String secondBook = scanner.nextLine();
        String thirdBook = scanner.nextLine();
        scanner.close();

        List<String> books = new LinkedList<>();
        books.add(firstBook);
        books.add(secondBook);
        books.add(thirdBook);

        for(String book : books){
            System.out.println(book);
        }
        System.out.println("All books are added : " + books);
    }
}
