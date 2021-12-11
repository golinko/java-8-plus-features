package com.golinko;

import jdk.incubator.vector.IntVector;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java16Features {
    public static void main(String[] args) {
        timeFormats();
        streams();
        vectors();
        innerRecords();
    }

    private static void timeFormats() {
        var date = LocalTime.now();
        var formatter = DateTimeFormatter.ofPattern("h B");
        System.out.println(date.format(formatter));
    }

    private static void streams() {
        List<Integer> before = Stream.of("1", "2", "3", "4").map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(before);
        List<Integer> now = Stream.of("1", "2", "3", "4").map(Integer::parseInt).toList();
        System.out.println(now);
    }

    private static void vectors() {
        int[] a = {1, 2, 3, 4};
        int[] b = {5, 6, 7, 8};
        var c = new int[a.length];

        var vectorA = IntVector.fromArray(IntVector.SPECIES_128, a, 0);
        System.out.println(vectorA);
        var vectorB = IntVector.fromArray(IntVector.SPECIES_128, b, 0);
        System.out.println(vectorB);
        var vectorC = vectorA.mul(vectorB);
        System.out.println(vectorC);
        vectorC.intoArray(c, 0);
        System.out.println(Arrays.toString(c));
    }

    private static void innerRecords() {
        var innerClass = new InnerClass();
        System.out.println(innerClass.book);
    }

    private static class InnerClass {
        Book book = new Book("Title", "author", "isbn");
    }
}

record Book(String title, String author, String isbn) {}