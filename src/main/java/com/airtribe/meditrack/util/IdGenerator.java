package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {

    private static final IdGenerator EAGER_INSTANCE = new IdGenerator();
    private static final AtomicLong SEQUENCE;

    static {
        SEQUENCE = new AtomicLong(1000);
    }

    private static final class LazyHolder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

    private IdGenerator() {
    }

    public static IdGenerator eagerInstance() {
        return EAGER_INSTANCE;
    }

    public static IdGenerator lazyInstance() {
        return LazyHolder.INSTANCE;
    }

    public String nextId(String prefix) {
        String safePrefix = (prefix == null || prefix.isBlank()) ? "ID" : prefix.trim().toUpperCase();
        return safePrefix + "-" + SEQUENCE.incrementAndGet();
    }
}
