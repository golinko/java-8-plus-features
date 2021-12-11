package com.golinko;

public class Java15Features {
    public static void main(String[] args) {
        records();
        sealedClasses();
        instanceOf();
    }

    private static void records() {
        try {
            new Point(4, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sealedClasses() {
        // the switch is the 17 feature, though
        Human person = new PersonRecord("name");
        switch (person) {
            case Employee e -> System.out.printf("employee %s%n", e);
            case Manager m -> System.out.printf("manager %s%n", m);
            case PersonRecord p -> System.out.printf("person record %s%n", p);
            case BabyBoy ignored -> System.out.println("not recognized as required for switch by compiler");
            case Baby ignored -> System.out.println("baby");
            case Boss ignored -> System.out.println("boss");
        };
    }

    private static void instanceOf() {
        Human person = new PersonRecord("name");
        if (person instanceof PersonRecord record && record.name().length() > 1) {
            System.out.println(record);
        }
    }
}

record Point(int x, int y) {
    Point {
        if (x > y)
            throw new IllegalArgumentException(String.format("(%d,%d)", x, y));
    }
}

sealed interface Human permits Person, PersonRecord {}
record PersonRecord(String name) implements Human {}
abstract sealed class Person implements Human permits Boss, Employee, Manager {}
non-sealed class Employee extends Person {} // should be final, sealed or non-sealed
final class Manager extends Person {}
sealed class Boss extends Person permits Baby {}
non-sealed class Baby extends Boss {}
class BabyBoy extends Baby {}