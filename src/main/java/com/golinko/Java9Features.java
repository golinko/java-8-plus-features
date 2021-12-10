package com.golinko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Java9Features {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        http();
        processApi();
        tryCatch();
        diamondOperator();
        defaultInterfaceMethods();
        newInterfaces();
    }

    private static void http() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);
    }

    private static void processApi() {
        ProcessHandle self = ProcessHandle.current();

        long PID = self.pid();
        System.out.println(PID);

        ProcessHandle.Info procInfo = self.info();

        System.out.println(procInfo.arguments());
        System.out.println(procInfo.commandLine());
        System.out.println(procInfo.startInstant());
        System.out.println(procInfo.totalCpuDuration());
    }

    private static void tryCatch() {
        Reader inputString = new StringReader("message");
        BufferedReader br1 = new BufferedReader(inputString);
        try (br1) {
            System.out.println(br1.readLine());
        } catch (IOException e) {
            System.out.println("catch");
        }
    }

    private static void diamondOperator() {
        FooClass<Integer> fc = new FooClass<>(1) {
            void test() {
                System.out.println("inside fc");
            }
        };
        fc.test();

        FooClass<? extends Number> fc0 = new FooClass<>(2.9) {
            void test() {
                System.out.println("inside fc0");
            }
        };
        fc0.test();

        FooClass<?> fc1 = new FooClass<>("Test") {
            void test() {
                System.out.println("inside fc1");
            }
        };
        fc1.test();
    }

    private static void defaultInterfaceMethods() {
        FooInterface s = new FooInterface() {};
        s.defaultMethod();
    }

    private static void newInterfaces() {
        System.out.println(Set.of("key1", "key2", "key3"));

        List<Optional<String>> listOfOptionals = List.of(Optional.of("1"), Optional.of("2"), Optional.ofNullable(null));
        List<String> filteredList = listOfOptionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(filteredList);
    }
}

class FooClass<T> {
    FooClass(T i) {
        System.out.println(i);
    }

    void test() {
        System.out.println("inside original");
    }
}

interface FooInterface {
    default void defaultMethod() {
        defaultStaticPrivate();
        defaultPrivate();

        FooInterface instance = new FooInterface() {
            private void defaultPrivate() {
                System.out.println("inner default private"); // useless
            }
        };
        instance.defaultPrivate();
    }

    private static void defaultStaticPrivate() {
        System.out.println("default static private");
    }

    private void defaultPrivate() {
        System.out.println("default private");
    }
}