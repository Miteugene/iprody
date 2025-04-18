package org.example.lesson13.task1;

import java.util.ArrayList;
import java.util.HashMap;

public class Task1 {
    public int countOccurrence(ArrayList<String> haystack, String needle) {
        int count = 0;
        for (String word : haystack) {
            if (word.equals(needle)) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Integer> toList(int[] array) {
        return new ArrayList<Integer>() {{
            for (int i = 0; i < array.length; i++) {
                this.add(array[i]);
            }
        }};
    }

    public ArrayList<Integer> findUnique(ArrayList<Integer> arrayList) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer value : arrayList) {
            hashMap.put(value, hashMap.getOrDefault(value, 0) + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer value : hashMap.keySet()) {
            if (hashMap.get(value) == 1) {
                result.add(value);
            }
        }

        return result;
    }

    public HashMap<String, Integer> calcOccurrence(ArrayList<String> wordList) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word: wordList) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        return hashMap;
    }

    public ArrayList<Occurrence> findOccurrence(ArrayList<String> wordList) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word: wordList) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        ArrayList<Occurrence> resultList = new ArrayList<>();

        for (String word : hashMap.keySet()) {
            resultList.add(
                    new Occurrence(word, hashMap.get(word))
            );
        }

        return resultList;
    }
}
