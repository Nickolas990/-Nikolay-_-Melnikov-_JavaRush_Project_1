package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.util.*;

public class StatisticEncryptor extends EnCryptor {
    private Path crypted;
    Map<Character, Double> stats;
    Map<Character, Double> cryptedStats;

    public StatisticEncryptor(Path example, Path crypted) {
        this.crypted = crypted;
        this.stats = creatingStats(example);
        this.cryptedStats = creatingStats(crypted);
    }

    public void breaking() {
        for (Map.Entry<Character, Double> entry : cryptedStats.entrySet()) {
            int index = 0;
            int cryptedIndex = 0;
            for (int i = 0; i < alphabet.length; i++) {
                if (entry.getKey() == alphabet[i]) index = i;
            }
            for (Map.Entry<Character, Double> entry2 : stats.entrySet()) {
                Double value = entry.getValue();
                if (value.equals(entry2.getValue())) {
                    for (int i = 0; i < alphabet.length; i++) {
                        if (entry.getKey() == alphabet[i]) {
                            cryptedIndex = i;
                            key = cryptedIndex - index;
                        }
                    }
                }
            }
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
            while (reader.read(cBuff) > 0) {
                cBuff.flip();
                while (cBuff.hasRemaining()) {
                    Character c = cBuff.get();
                    totalLetters++;
                    map.put(c, map.get(c) + 1);
                    cBuff.clear();
                }
            }
            if (totalLetters > 0) {
                for (Map.Entry<Character, Double> entry : map.entrySet()) {
                    map.put(entry.getKey(), entry.getValue() / totalLetters * 100);
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            } else System.out.println("Stats table is empty");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return map;
    }

}
