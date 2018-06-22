package com.padcmyanmar.ted.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PodcastVO {

    @SerializedName("podcast_id")
    private String postcastId;

    @SerializedName("title")
    private String title;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("segments")
    private List<SegmentVO> segment;

    public String getPostcastId() {
        return postcastId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<SegmentVO> getSegment() {
        return segment;
    }
}
