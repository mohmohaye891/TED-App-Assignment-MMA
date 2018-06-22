package com.padcmyanmar.ted.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.ted.data.vos.TalkVO;

import java.util.List;

public class GetNewestResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("ted_talks")
    private List<TalkVO> mmTalk;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TalkVO> getMmTalk() {
        return mmTalk;
    }
    public boolean isResponseOK(){
        return (code == 200 && mmTalk != null);
    }
}
