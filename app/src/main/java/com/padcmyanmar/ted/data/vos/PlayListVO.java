package com.padcmyanmar.ted.data.vos;

import java.util.List;

public class PlayListVO {

    private String playlistId;
    private String title;
    private String imageUrl;
    private String totalTalks;
    private String description;
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
