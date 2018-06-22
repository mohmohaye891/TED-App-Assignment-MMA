package com.padcmyanmar.ted.data.vos;

import com.google.gson.annotations.SerializedName;

public class SearchResultVO {

    @SerializedName("search_result_id")
    private String serachresultId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("result_type")
    private String resultType;

    @SerializedName("result_id")
    private String resultId;

    public String getSerachresultId() {
        return serachresultId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResultType() {
        return resultType;
    }

    public String getResultId() {
        return resultId;
    }
}
