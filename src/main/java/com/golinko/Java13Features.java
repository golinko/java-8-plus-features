package com.golinko;

public class Java13Features {
    public static void main(String[] args) {
        yieldSwitch();
        stringBlocks();
    }

    private static void yieldSwitch() {
        var me = 4;
        var operation = "squareMe";
        var result = switch (operation) {
            case "doubleMe" -> {
                System.out.println("doubleMe");
                yield me * 2;
            }
            case "squareMe" -> {
                System.out.println("squareMe");
                yield me * me;
            }
            default -> me;
        };
        System.out.println(result);
    }

    private static void stringBlocks() {
        var text =
            """
            {
                "id" : 123456798,
                "name" : "Test"
            }
            """;
        System.out.println(text);

        var text2 = "{\r\n" + "\"id\" : 123456789,\r\n" + "\"name\" : \"Text%s\"\r\n" + "}";
        System.out.println(text2.stripIndent());
        System.out.println(text2.translateEscapes());
        System.out.println(text2.formatted("ABC"));
    }
}
