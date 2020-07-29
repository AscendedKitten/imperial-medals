package at.imperial.domain;

public enum MedalType {
    VONAMOR_CROSS(""),
    MEDAL_OF_CONQUEST(""),
    MEDAL_OF_SCALES(""),
    MEDAL_OF_CAMARADERIE(""),
    MEDAL_OF_SAINT_LILIUM(""),
    MEDAL_OF_OBURO(""),
    MEDAL_OF_IMPERIAL_SERVICE(""),
    MEDAL_OF_CHARITY(""),
    MEDAL_OF_RECREATION("");

    private String imgUrl;

    MedalType(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}