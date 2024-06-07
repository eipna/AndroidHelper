package com.serbi.sampledetailedrecyclerview;

public class DataClass {

    private String dataTitle;
    private String dataLanguage;
    private int dataImage;
    private int dataDescription;

    public DataClass(String dataTitle, int dataDescription, String dataLanguage, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataLanguage = dataLanguage;
        this.dataImage = dataImage;
        this.dataDescription = dataDescription;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataLanguage() {
        return dataLanguage;
    }

    public int getDataImage() {
        return dataImage;
    }

    public int getDataDescription() {
        return dataDescription;
    }
}