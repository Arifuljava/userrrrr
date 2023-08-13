package com.messas.kikikikikikik;

public class SliderData {
    // string for our image url.
    private String imgUrl,uuid;

    // empty constructor which is
    // required when using Firebase.
    public SliderData() {
    }

    // Constructor

    public SliderData(String imgUrl, String uuid) {
        this.imgUrl = imgUrl;
        this.uuid = uuid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
