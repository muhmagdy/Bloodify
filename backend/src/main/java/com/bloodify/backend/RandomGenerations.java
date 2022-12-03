//package com.bloodify.backend;
//
//import java.time.LocalDate;
//
//public class RandomGenerations {
//    String capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    String smalls = "abcdefghijklmnopqrstuvwxyz";
//    String numbers = "0123456789";
//
//    private int generateYear(int start, int end) {
//        return start + (int)(Math.ceil(Math.random()*(end-start) - 0.5));
//    }
//    private int generateMonth() {
//        return (int) Math.ceil(Math.random()*12);
//    }
//    private int generateDay() {
//        return (int) Math.ceil(Math.random()*30);
//    }
//    public LocalDate generateDate(int yearStart, int yearEnd) {
//        int year = generateYear(yearStart, yearEnd);
//        int month = generateMonth();
//        int day = generateDay();
//        return LocalDate.of(year, month, day);
//    }
//
//    public String generateNationalID() {
//        StringBuilder sb = new StringBuilder(14);
//        for(int i=0; i<14; i++) {
//            int index = (int)(numbers.length()*Math.random());
//            sb.append(numbers.charAt(index));
//        }
//        return sb.toString();
//    }
//
//    public String generateName(int minLength, int maxLength) {
//        int n = (int)(minLength+Math.random()*(maxLength-minLength)+0.5);
//        StringBuilder sb = new StringBuilder(n);
//
//        int capitalIndex = (int)(Math.random()*26);
//        sb.append(capitals.charAt(capitalIndex));
//
//        for(int i=1; i<n; i++) {
//            int index = (int)(smalls.length()*Math.random());
//            sb.append(smalls.charAt(index));
//        }
//        return sb.toString();
//    }
//
//    private String generateWord(int n) {
//        StringBuilder sb = new StringBuilder(n);
//        String allChars = capitals + smalls + numbers;
//        for(int i=0; i<n; i++) {
//            int index = (int)(allChars.length()*Math.random());
//            sb.append(allChars.charAt(index));
//        }
//        return sb.toString();
//    }
//
//    public String generatePassword(int maxLength) {
//        int length = (int)(Math.random()*(maxLength-8) + 8.5);
//        return generateWord(length);
//    }
//
//    public String generateEmail(int minLength, int maxLength) {
//        int n = (int)(Math.random()*(maxLength-minLength) + minLength-4);
//        String name1 = generateWord(2*n/3);
//        String name2 = generateWord(n/3);
//        return name1 + "@" + name2 + ".com";
//    }
//
//    public String generateBloodType() {
//        String[] types = {"A", "B", "AB", "O"};
//        int n = (int)(Math.random()*4);
//        return types[n];
//    }
//
//    public char generateBloodSign() {
//        char[] types = {'+', '-'};
//        int n = (int)(Math.random()*2);
//        return types[n];
//    }
//
//    public boolean generateDiseases() {
//        boolean[] types = {true, false};
//        int n = (int)(Math.random()*2);
//        return types[n];
//    }
//
//
//
//    public static void main(String[] args) {
//        RandomGenerations random = new RandomGenerations();
//        for(int i=0; i<50; i++) {
//            System.out.println(random.generatePassword(10));
//        }
//    }
//}