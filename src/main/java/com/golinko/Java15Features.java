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
        Human person = new PersonRecord("name");
        switch (person) {
            case Employee e -> System.out.printf("employee %s%n", e);
            case Manager m -> System.out.printf("manager %s%n", m);
            case PersonRecord p -> System.out.printf("person record %s%n", p);
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
abstract sealed class Person implements Human permits Employee, Manager {}
final class Employee extends Person {} // should be final or sealed
final class Manager extends Person {}
