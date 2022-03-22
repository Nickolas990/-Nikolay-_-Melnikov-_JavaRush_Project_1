package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.util.*;

public class StatisticEncryptor extends EnCryptor {
    public static final int CAPACITY = 2048;
    Map<Character, Double> stats;
    Map<Character, Double> cryptedStats;

    public StatisticEncryptor(Path example, Path crypted, Path encrypted) {
        input = crypted;
        output = encrypted;
        this.stats = creatingMapWithStats(example);
        this.cryptedStats = creatingMapWithStats(crypted);
    }


    public int getIndexOfMostOftenLetter(Map<Character, Double> target) {
        int index = 0;
        char oftenLetter = ' ';
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Character, Double> entry : target.entrySet()) {
            if (entry.getValue() > max) {
                oftenLetter = entry.getKey();
                max =(int) Math.round(entry.getValue());
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (oftenLetter == alphabet[i]) {
                index = i;
            }
        }

        return index;
    }

    public int breaking() {
        int index = getIndexOfMostOftenLetter(stats);
        int cryptedIndex = getIndexOfMostOftenLetter(cryptedStats);
        return cryptedIndex - index;
    }

    public void printStats() {
        for (Map.Entry<Character, Double> entry : cryptedStats.entrySet()) {
            System.out.println(entry.getKey() + " : " + Math.round(entry.getValue()));
        }
        System.out.println();

        for (Map.Entry<Character, Double> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + " : " + Math.round(entry.getValue()));
        }
    }

    private Map creatingMapWithStats(Path example) {
        var map = new HashMap<Character, Double>();
        int totalLetters = 0;
        for (char c : alphabet) {
            map.put(c, .0);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(example)))) {
            CharBuffer charBuffer = CharBuffer.allocate(CAPACITY);
            while (reader.read() > 0) {
                charBuffer.put((reader.readLine().toLowerCase()));
                charBuffer.flip();
                while (charBuffer.hasRemaining()) {
                    Character c = charBuffer.get();
                    if (map.containsKey(c)) {
                        totalLetters++;
                        map.put(c, map.get(c) + 1);
                    }
                }
                charBuffer.clear();
            }
            if (totalLetters > 0) {
                for (Map.Entry<Character, Double> entry : map.entrySet()) {
                    map.put(entry.getKey(), entry.getValue() / totalLetters * 100);
                }
            } else System.out.println("Stats table is empty");

        } catch (FileNotFoundException e) {
            System.out.println("File not found in this directory");
        } catch (IOException e) {
            System.out.println("Incorrect data entered");

        }
        return map;
    }
}
