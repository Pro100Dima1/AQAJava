package org.reshebnik.collection.ex2;

//2. Уникальные города
//   При вводе городов исключай повторы. Порядок не важен

import java.util.HashSet;
import java.util.Set;

public class ListOfCities {
    public static void main(String[] args) {
        Set<Cities> listOfCities = new HashSet<>();
        listOfCities.add(new Cities("Moscow"));
        listOfCities.add(new Cities("London"));
        listOfCities.add(new Cities("London"));
        listOfCities.add(new Cities("London"));
        listOfCities.add(new Cities("Praga"));

        for(Cities city : listOfCities){
            System.out.println(city);
        }
    }
}
