package com.padcmyanmar.ted.data.vos;

import java.util.List;

public class TalkVO {

    private String talkId;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private String durationInSec;
    private String description;
    private List<TagVO> tagVO;

    public String getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDurationInSec() {
        return durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTagVO() {
        return tagVO;
    }
}
