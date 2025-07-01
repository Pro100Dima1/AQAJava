package org.example.task3;

public class Main {
    public static void main(String[] args) {

        Company employee1 = new Company(1, "Токарь");
        Company employee2 = new Company(2, "Слесарь");
        Company.companyName = "Avito";
        Company.printCompanyName();
        Company.companyName = "Ozon";
        Company.printCompanyName();
        //employee2.employeeID = 3; - Нельзя, потому что final

        System.out.println(MathConstants.calculateCircleArea(8));
        System.out.println(MathConstants.calculateCircumference(11));

        University student1 = new University(1, "Vova");
        University student2 = new University(2, "Kolya");
        University student3 = new University(3, "Pasha");

        University.changeUniversityName("MSU");
        student1.printStudentInfo();
        student2.printStudentInfo();
        student3.printStudentInfo();

        GameSettings game1 = new GameSettings("War", 5);
        GameSettings game2 = new GameSettings("Sims", 9);
        GameSettings.setMaxPlayers(12);
        game1.addPlayer();
        game2.addPlayer();
        game1.printGameStatus();
        game2.printGameStatus();

        Person person1 = new Person("Vanya", "Ivanov", "123-43-6789");
        Person person2 = new Person("Sveta", "Zukova", "123-41-6789");
        Person person3 = new Person("Kostya", "Orlov", "123-46-6789");

        person3.setFirstName("Zeleboba");

        person3.printPersonInfo();
        person2.printPersonInfo();
        person1.printPersonInfo();
    }
}
