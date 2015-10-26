package Helpers;

/**
 * Created by majid on 25/10/15.
 * Differnet levels of similarity used by Sikuli to recognize a pattern.
 */
public enum SimilarityLevels {
    TEN_PERCENT((float) 0.1),
    TWENTY_PERCENT((float) 0.2),
    THIRTY_PERCENT((float) 0.3),
    FORTY_PERCENT((float) 0.4),
    FIFTY_PERCENT((float) 0.5),
    SIXTY_PERCENT((float) 0.6),
    SEVENTY_PERCENT((float) 0.7),
    EIGHTY_PERCENT((float) 0.8),
    NINETY_PERCENT((float) 0.9),
    HUNDRED_PERCENT((float) 1);

    private final float percentage;

    private SimilarityLevels (float percentage) {
        this.percentage = percentage;
    }

    public float getSimilarityLevel() {
        return this.percentage;
    }
}
