package hu.marktsoft.epipoc.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Constants {
    public static final String GREAT_COMFORT = "GREAT_COMFORT\n";
    public static final String AVERAGE_COMFORT = "AVERAGE_COMFORT\n";
    public static final String WEAK_COMFORT = "WEAK_COMFORT\n";

    public static final String GREAT_ECOLOGY = "GREAT_ECOLOGY\n";
    public static final String AVERAGE_ECOLOGY = "AVERAGE_ECOLOGY\n";
    public static final String WEAK_ECOLOGY = "WEAK_ECOLOGY\n";

    public static final String GREAT_HEALTH = "GREAT_HEALTH\n";
    public static final String AVERAGE_HEALTH = "AVERAGE_HEALTH\n";
    public static final String WEAK_HEALTH = "WEAK_HEALTH\n";

    public static final int GREAT_LIMIT = 70;
    public static final int AVERAGE_LIMIT = 30;
}
