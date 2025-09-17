package org.expressJava.task7.genericBox;

public class Box<Q> {
    private Q box;

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.setBox(10);
        System.out.println(integerBox.getBox());

        Box<String> stringBox = new Box<>();
        stringBox.setBox("Generics RELEES");
        System.out.println(stringBox.getBox());
    }

    public Q getBox() {
        return box;
    }

    public void setBox(Q box) {
        this.box = box;
    }
}
