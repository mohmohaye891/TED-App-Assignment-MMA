package com.padcmyanmar.ted.data.models;

import com.padcmyanmar.ted.data.vos.TalkVO;
import com.padcmyanmar.ted.events.SuccessGetNewestEvent;
import com.padcmyanmar.ted.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted.network.NewestDataAgent;
import com.padcmyanmar.ted.network.OkHttpDataAgentImpl;
import com.padcmyanmar.ted.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewestModel {

    private static NewestModel objInstance;

    private NewestDataAgent mDataAgent;

    private Map<String, TalkVO> mNewestMap;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private NewestModel() {
       // mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent = OkHttpDataAgentImpl.getsObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getInstance();

        mNewestMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static NewestModel getObjectInstance() {
        if (objInstance == null){
            objInstance = new NewestModel();
        }
        return objInstance;
    }

    public void LoadNewestList(){
        mDataAgent.LoadNewestList(1,DUMMY_ACCESS_TOKEN);
    }

    public TalkVO getTalkById(String talkId){
        return mNewestMap.get(talkId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetNewestEvent event){
        for (TalkVO talks : event.getNewestList()){
            mNewestMap.put(talks.getTalkId(), talks);
        }
    }
}
