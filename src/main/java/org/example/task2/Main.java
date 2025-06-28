package org.example.task2;

public class Main {
    public static void main(String[] args) {
        Car bmw = new Car("BMW", 1997);
        Car nissan = new Car("Nissan", 2003);

        bmw.print();
        nissan.print();

        bmw.setBrand("Mersedec");
        nissan.setYear(1999);
        bmw.print();
        nissan.print();

        Rectangle rectangle = new Rectangle(10, 5);
        System.out.println("Площадь нового прямоугольника = " + rectangle.calculateArea(5, 5));

        Book bookHistoryOfMath = new Book("История математики", "Гюйгенс Френель");
        bookHistoryOfMath.setAuthor("Григорий Фихтенгольц");
        bookHistoryOfMath.printInfo();

        BankAccount bankAccount = new BankAccount("Филя Филимонов", 200);
        bankAccount.printBalance();

        Point point = new Point(2, 5);
        point.print();
        point.setX(8);
        point.print();

        StudentGroup studentGroup = new StudentGroup("Разработки", 5);
        studentGroup.setStudentCount(12);
        studentGroup.printInfo();


        Circle circle = new Circle(13);
        circle.setRadius(3);
        circle.calculateArea();
        circle.calculateCircumference();

        Teacher mathTeacher = new Teacher("Валера", "Математику");
        mathTeacher.setSubject("Историю");
        mathTeacher.printInfo();

        Product product = new Product("тарелка", 100);
        product.applyDiscount(20);
        product.printInfo();
        product.setPrice(1000);
        product.applyDiscount(20);
        product.printInfo();

        Laptop laptop = new Laptop("Acer", 2500);
        laptop.printInfo();
        laptop.setPrice(3800);
        laptop.printInfo();
    }
}
