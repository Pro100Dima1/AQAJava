package org.example.task7.genericPair;

import java.util.function.Predicate;

public class Pair<Q, A> {
    private Q firstElement;
    private A secondElement;

    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>();
        pair.setFirstElement(10);
        pair.setSecondElement("URA");
        System.out.println(pair.getFirstElement() + " " + pair.getSecondElement());
    }

    public Q getFirstElement() {
        return firstElement;
    }

    public A getSecondElement() {
        return secondElement;
    }

    public void setFirstElement(Q firstElement) {
        this.firstElement = firstElement;
    }

    public void setSecondElement(A secondElement) {
        this.secondElement = secondElement;
    }
}
