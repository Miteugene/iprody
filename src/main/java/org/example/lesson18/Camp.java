package org.example.lesson18;

import java.util.*;
import java.util.stream.Collectors;

public class Camp {
    private ArrayList<Boyscout> boyscouts = new ArrayList<>();

    public void add(Boyscout boyscout) {
        boyscouts.add(boyscout);
    }

    public Map<TeamEnum, List<Boyscout>> split() {
        return boyscouts.stream()
                .sorted(Comparator.comparing(Boyscout::getAge))
                .collect(Collectors.groupingBy(Boyscout::getTeam));
    }
}
