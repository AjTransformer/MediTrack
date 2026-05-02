package com.airtribe.meditrack.constants;

public final class Constants {

    public static final String APP_NAME;
    public static final double DEFAULT_TAX_RATE;
    public static final int MIN_AGE;
    public static final int MAX_AGE;

    static {
        APP_NAME = "MediTrack";
        DEFAULT_TAX_RATE = 0.18d;
        MIN_AGE = 0;
        MAX_AGE = 120;
    }

    private Constants() {
    }
}
