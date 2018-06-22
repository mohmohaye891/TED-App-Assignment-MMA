package com.padcmyanmar.ted.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayListVO {

    @SerializedName("playlist_id")
    private String playlistId;

    @SerializedName("title")
    private String title;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("totalTalks")
    private String totalTalks;

    @SerializedName("description")
    private String description;

    @SerializedName("talksInPlaylist")
    private List<TalkVO> talksInPlaylist;

    public String getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TalkVO> getTalksInPlaylist() {
        return talksInPlaylist;
    }
}
