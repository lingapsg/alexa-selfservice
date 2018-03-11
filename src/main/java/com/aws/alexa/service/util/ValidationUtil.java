package com.aws.alexa.service.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ValidationUtil {

    private static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static List<String> monthsList = Arrays.asList(months);
    private static Map<String, String> keyLap = new HashMap<>();

    static {
        keyLap.put("1", "One"); keyLap.put("2", "Two"); keyLap.put("3", "Three"); keyLap.put("4", "Four"); keyLap.put("5", "Five");
        keyLap.put("6", "Six"); keyLap.put("7", "Seven"); keyLap.put("8", "Eight"); keyLap.put("9", "Nine");
    }

    public static boolean isValidMonth(String month) {
        return monthsList.stream()
                .anyMatch(s -> s.contains(month));
    }

    public static String getMonthByIndex(int i) {
        return months[i];
    }

    public static String getMonth(String monthKey) {
        return monthsList.stream()
                .filter(s -> s.contains(monthKey))
                .findAny().orElse(null);
    }

    public static String getMonthByOthers(String month) {
        if (month.equalsIgnoreCase("current")) {
            return getMonthByIndex(Calendar.getInstance().get(Calendar.MONTH));
        } else if (month.equalsIgnoreCase("previoue")){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            return getMonthByIndex(cal.get(Calendar.MONTH));
        } else {
            return getMonth(month);
        }
    }

    public static boolean isValidMsisdn(String msisdn) {
        return StringUtils.isNumeric(msisdn) && msisdn.startsWith("7");
    }

    public static boolean isValidData(String dataSlotValue) {
        return dataSlotValue.replaceAll("[*a-zA-Z]", "").trim().matches("[*0-9]");
    }

    public static String detectUnit(String dataValue) {
        if (dataValue.toLowerCase().matches("gb$|gigabytes$")) {
            return "GB";
        } else if (dataValue.toLowerCase().matches("mb$|megabytes$")) {
            return "MB";
        } else {
            return "MB";
        }
    }

    public static String convertToText(String value) {
        return Arrays.asList(value.split(""))
                .stream()
                .map(s -> keyLap.get(s))
                .collect(Collectors.joining(" "));
    }
}
