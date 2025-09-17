package org.expressJava.task3;

public class LibraryTest {
    Library library = new Library();
    String a = library.author;
    int b = library.year;
    // Нет доступа к bookTitle, потому что находимся в другом классе, а у него модификатор доступа private
}
