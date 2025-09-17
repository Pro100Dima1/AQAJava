package org.expressJava.task2;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("BMW", 1967);
        car.print();
        car.setYear(1997);
        car.print();

        Rectangle rectangle = new Rectangle(10, 10);
        rectangle.setWidth(30);
        System.out.println("Новая площадь прямоугольника = " + rectangle.calculateArea());

        Book historyBook = new Book("История 21 века", "Илья Кузьмич");
        historyBook.setAuthor("Фома Никитич");
        historyBook.printInfo();

        BankAccount bankAccount = new BankAccount("Федя", 2000);
        bankAccount.printBalance();

        Point point = new Point(2, 5);
        point.setX(18);
        point.print();

        StudentGroup rf151 = new StudentGroup("РФ-15-1", 20);
        rf151.setStudentCount(10);
        rf151.printInfo();

        Circle circle = new Circle(25);
        circle.setRadius(10);
        System.out.println("Площадь круга = " + circle.calculateArea() + ". Длина окружности = " + circle.calculateCircumference());

        Teacher teacherMath = new Teacher("Галина Ивановна", "Математику");
        teacherMath.setSubject("философию");
        teacherMath.printInfo();

        Product apple = new Product("Яблоко", 100);
        apple.applyDiscount(20);
        apple.printInfo();

        Laptop dell = new Laptop("ACER", 3000);
        dell.setPrice(2500);
        dell.printInfo();
    }
}
