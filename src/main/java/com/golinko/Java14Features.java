package com.golinko;

public class Java14Features {
    public static void main(String[] args) {
        switchStandard();
        textBlocks();
        records();
        nullPointerMessage();
    }

    private static void switchStandard() {
        var day = "TUESDAY";
        var isTodayHoliday = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> false;
            case "SATURDAY", "SUNDAY" -> true;
            default -> throw new IllegalArgumentException("What's a " + day);
        };
        System.out.println(isTodayHoliday);
    }

    private static void textBlocks() {
        String justForReadability = """
            line 1 \
            line 2 \
            line 3
            """;
        System.out.println(justForReadability);
    }

    private static void records() {
        var record1 = new Record(1, "Test");
        System.out.println(record1);
        System.out.println(record1.id());
        System.out.println(record1.name());

        var record2 = new Record(1, "Test");
        System.out.println(record1.equals(record2));
    }

    private static void nullPointerMessage() {
        try {
            int[] arr = null;
            arr[0] = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

record Record(Integer id, String name) {}