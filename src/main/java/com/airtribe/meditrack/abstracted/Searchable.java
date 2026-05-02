package com.airtribe.meditrack.abstracted;

public interface Searchable {

    default boolean containsIgnoreCase(String source, String query) {
        return source != null && query != null
                && source.toLowerCase().contains(query.toLowerCase());
    }

    default boolean matchesExact(String source, String query) {
        return source != null && query != null
                && source.equalsIgnoreCase(query);
    }
}
