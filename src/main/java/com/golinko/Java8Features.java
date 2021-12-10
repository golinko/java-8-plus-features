package com.golinko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java8Features {
    public static void main(String[] args) {
        interfaceMethods();
        methodReference();
        optionals();
        tryCatch();
    }

    private static void interfaceMethods() {
        InterfaceMethods i = new InterfaceMethods() {};
        i.defaultMethod();
        InterfaceMethods.staticMethod();
    }

    private static void methodReference() {
        // Method references
        List<User> users = new ArrayList<>();
        users.add(new User("A"));
        users.add(new User("B"));

        System.out.println(users.stream().anyMatch(User::staticMethod));
        System.out.println(users.stream().anyMatch(users.get(0)::nonStaticMethod));
        System.out.println(users.stream().map(User::new).toList());
    }

    private static void optionals() {
        System.out.println(Optional.empty());

        String value = "value";
        System.out.println(Optional.of(value));


        String nullable = null;
        System.out.println(Optional.ofNullable(nullable));

        try {
            Optional<String> valueOpt = Optional.ofNullable(nullable);
            String result = valueOpt.orElseThrow(Exception::new).toUpperCase();
        } catch (Exception e) {
            System.out.println("Exception thrown");
        }

        Optional<User> user = Optional.ofNullable(User.getUser());

        System.out.println(user.orElse(new User("Not null")));

        System.out.println(user
                        .map(User::new)
                        .orElse(new User("not specified")));
    }

    private static void tryCatch() {
        Reader inputString = new StringReader("message");
        try (BufferedReader br1 = new BufferedReader(inputString)) {
            System.out.println(br1.readLine());
        } catch (IOException e) {
            System.out.println("catch");
        }
    }
}

interface InterfaceMethods {
    static void staticMethod() {
        System.out.println("static method");
    }
    default void defaultMethod() {
        System.out.println("default method");
    }
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

    public User(User user) {
        this.name = user.name;
    }

    public boolean nonStaticMethod(User user) {
        return user.name.equals("B");
    }

    public static boolean staticMethod(User user) {
        return user.name.equals("B");
    }

    public static User getUser() {
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
