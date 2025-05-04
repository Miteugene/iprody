package org.example.lesson18;

import java.util.Comparator;
import java.util.function.Predicate;

public enum OrderEnum {
    ASC, DESC;

    public <T extends Comparable<? super T>> Comparator<T> getComparator() {
        return switch (this) {
            case ASC -> Comparator.naturalOrder();
            case DESC -> Comparator.reverseOrder();
        };
    }
}
