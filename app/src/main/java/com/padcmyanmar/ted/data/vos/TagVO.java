package com.padcmyanmar.ted.data.vos;

import com.google.gson.annotations.SerializedName;

public class TagVO {

    @SerializedName("tag_id")
    private String tagId;

    @SerializedName("tag")
    private String tag;

    @SerializedName("description")
    private String description;

    public String getTagId() {
        return tagId;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }
}
