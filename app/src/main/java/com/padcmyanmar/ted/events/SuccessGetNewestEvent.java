package com.padcmyanmar.ted.events;

import com.padcmyanmar.ted.data.vos.TalkVO;

import java.util.List;

public class SuccessGetNewestEvent {
    private List<TalkVO> newestList;

    public SuccessGetNewestEvent(List<TalkVO> newestList) {
        this.newestList = newestList;
    }

    public List<TalkVO> getNewestList() {
        return newestList;
    }
}
