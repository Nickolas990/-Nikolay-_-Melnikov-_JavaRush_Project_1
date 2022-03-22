package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.util.*;

public class StatisticEncryptor extends EnCryptor {
    Map<Character, Double> stats;
    Map<Character, Double> cryptedStats;

    public StatisticEncryptor(Path example, Path crypted, Path encrypted) {
        input = crypted;
        output = encrypted;
        this.stats = creatingStats(example);
        this.cryptedStats = creatingStats(crypted);
    }

    public int breaking() {
        char oftenLetter = ' ';
        char oftenCryptoletter = ' ';
        int index = 0;
        int cryptedIndex = 0;
        int max = Integer.MIN_VALUE;
        int cryptedMax = Integer.MIN_VALUE;
        for (Map.Entry<Character, Double> entry : stats.entrySet()) {
            if (entry.getValue() > max) {
                oftenLetter = entry.getKey();
                max =(int) Math.round(entry.getValue());
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (oftenLetter == alphabet[i]) index = i;
        }
        for (Map.Entry<Character, Double> entry : cryptedStats.entrySet()) {
            if (entry.getValue() > cryptedMax) {
                oftenCryptoletter = entry.getKey();
                cryptedMax = (int) Math.round(entry.getValue());
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (oftenCryptoletter == alphabet[i]) cryptedIndex = i;
        }
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

    private Map creatingStats(Path example) {
        var map = new HashMap<Character, Double>();
        int totalLetters = 0;
        for (char c : alphabet) {
            map.put(c, .0);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(example)))) {
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read() > 0) {
                cBuff.put((reader.readLine().toLowerCase(Locale.ROOT)));
                cBuff.flip();
                while (cBuff.hasRemaining()) {
                    Character c = cBuff.get();
                    if (map.containsKey(c)) {
                        totalLetters++;
                        map.put(c, map.get(c) + 1);
                    }
                }
                cBuff.clear();
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
