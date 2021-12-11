package com.golinko;

import java.util.Arrays;
import java.util.random.RandomGeneratorFactory;

public class Java17Features {
    public static void main(String[] args) {
        randoms();
        switchFeatures();
    }

    private static void randoms() {
        var ints = RandomGeneratorFactory.of("L64X256MixRandom")
                .create()
                .ints(25, 0,100)
                .toArray();
        System.out.println(Arrays.toString(ints));
    }

    private static void switchFeatures() {
        Human person = new BabyBoy();
        switch (person) {
            case Employee e -> System.out.printf("employee %s%n", e);
            case Manager ignored -> System.out.println("manager");
            case PersonRecord p -> System.out.printf("person name = %s%n", p.name());
            case BabyBoy ignored -> System.out.println("not recognized as required for switch by compiler");
            case Baby ignored -> System.out.println("baby");
            case Boss ignored -> System.out.println("boss");
        };
    }
}

