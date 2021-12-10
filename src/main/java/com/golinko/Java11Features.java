package com.golinko;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java11Features {
    public static void main(String[] args) {
        strings();
        files();
        collections();
        varInLambda();
    }

    public static void strings() {
        var multilineString = "first line \n \n third line \n fourth line \n";
        var lines = multilineString.lines()
                .filter(String::isBlank)
                .map(String::strip)
                .collect(Collectors.toList());
        System.out.println(lines.size());

        System.out.println("La ".repeat(10));

        System.out.println("strip: _" + "\n\t  hello   \u2005".strip() + "_");
        System.out.println("trim: _" + "\n\t  hello   \u2005".trim() + "_");
        System.out.println(Character.isWhitespace('\u2005'));
        System.out.println("\n\t\u2005  ".isBlank());
    }

    public static void files() {
        try {
            Path dir = Files.createTempDirectory("demo");
            Path file = Files.createTempFile(dir, "demo", ".txt");
            Files.writeString(file, "Sample text");
            System.out.println(Files.readString(file));
            Files.deleteIfExists(file);
            Files.deleteIfExists(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void collections() {
        var list = Arrays.asList("1", "2", "3", "", "\n");
        var array = list.toArray(String[]::new);
        System.out.println(list);
        System.out.println(array);

        var withoutBlanks = list.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        System.out.println(withoutBlanks);
    }

    public static void varInLambda() {
        var list = Arrays.asList("a", "b", "c");
        var result = list.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
