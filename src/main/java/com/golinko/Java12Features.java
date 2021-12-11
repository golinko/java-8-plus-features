package com.golinko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.DayOfWeek.MONDAY;

public class Java12Features {
    public static void main(String[] args) {
        strings();
        files();
        teeingCollector();
        multiSwitch();
        instanceOf();
    }

    private static void strings() {
        var text = "line1\nline2";
        System.out.println(text);

        text = text.indent(4);
        System.out.println(text);

        text = text.indent(-2);
        System.out.println(text);

        var text2 = "text";
        var transformed = text2.transform(value ->
                new StringBuilder(value).reverse().toString()
        );
        System.out.println(transformed);
    }

    private static void files() {
        try {
            Path dir = Files.createTempDirectory("demo");

            Path file1 = Files.createTempFile(dir, "demo", ".txt");
            Files.writeString(file1, "123");

            Path file2 = Files.createTempFile(dir, "demo", ".txt");
            Files.writeString(file2, "124");

            Path file3 = Files.createTempFile(dir, "demo", ".txt");
            Files.writeString(file3, "124");

            System.out.println(Files.mismatch(file1, file2));
            System.out.println(Files.mismatch(file2, file3));

            Files.deleteIfExists(file1);
            Files.deleteIfExists(file2);
            Files.deleteIfExists(file3);
            Files.deleteIfExists(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void teeingCollector() {
        var mean = Stream.of(1, 2, 3, 4, 5)
                .collect(
                        Collectors.teeing(
                                Collectors.summingDouble(i -> i),
                                Collectors.counting(),
                                (sum, count) -> sum / count
                        )
                );

        System.out.println(mean);

        var multi = Stream.of(1, 2, 3, 4, 5)
                .collect(
                        Collectors.teeing(
                                Collectors.averagingDouble(i -> i),
                                Collectors.summingDouble(i -> i*2),
                                (avg, sum) -> avg * sum
                        )
                );
        System.out.println(multi);
    }

    private static void multiSwitch() {
        var dayOfWeek = MONDAY;
        String result = "None";
        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                System.out.println("do something here");
                System.out.println("and do something here");
                result = "Working day";
            }
            case SATURDAY, SUNDAY -> result = "Day Off";
        };
        System.out.println(result);
    }

    private static void instanceOf() {
        Object obj = "Hello World!";
        if (obj instanceof String s) {
            System.out.println(s.length());
        }
    }
}