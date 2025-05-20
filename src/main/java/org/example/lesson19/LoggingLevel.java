package org.example.lesson19;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Supplier;

public enum LoggingLevel {
    INFO {
        @Override
        protected Set<LoggingLevel> getIncludedLevels() {
            return EnumSet.of(INFO);
        }
    },
    DEBUG {
        @Override
        protected Set<LoggingLevel> getIncludedLevels() {
            return EnumSet.of(INFO, DEBUG);
        }
    };

    protected abstract Set<LoggingLevel> getIncludedLevels();

    public boolean includes(LoggingLevel level) {
        return getIncludedLevels().contains(level);
    }
}
