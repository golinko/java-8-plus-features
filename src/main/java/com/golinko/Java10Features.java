package com.golinko;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java10Features {
    public static void main(String[] args) {
        helloKotlin();
        unmodifiableCollections();
        optionals();
    }

    private static void helloKotlin() {
        var message = "Hello, Java 10";
        System.out.println(message);

        var idToNameMap = Map.of(1, "1", 2, "2");
        System.out.println(idToNameMap);
    }

    private static void unmodifiableCollections() {
        var origin = new ArrayList<String>();
        origin.add("1");
        origin.add("2");
        var copy = List.copyOf(origin);
        System.out.println(origin);
        System.out.println(copy);
        System.out.println(origin.equals(copy));
        origin.add("3");
        System.out.println(origin.equals(copy));
        // copy.add("4"); - sorry, copy is immutable

        List<String> modList = copy.stream()
                .filter(s -> s.equals("2"))
                .collect(Collectors.toUnmodifiableList());
        // modList.add("4"); - sorry, modList is immutable
        System.out.println(modList);
    }

    private static void optionals() {
        Integer firstEven = Stream.of(1, 2, 3, 4)
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow();
        System.out.println(firstEven);

        try {
            Stream.of(1, 3, 5)
                    .filter(i -> i % 2 == 0)
                    .findFirst()
                    .orElseThrow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
