package com.padcmyanmar.ted.data.vos;

import com.google.gson.annotations.SerializedName;

public class SegmentVO {


    @SerializedName("segment_id")
    private String segmentId;

    @SerializedName("title")
    private String title;

    @SerializedName("imageUrl")
    private String imageUrl;

    public String getSegmentId() {
        return segmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
