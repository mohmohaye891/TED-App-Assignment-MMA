package com.padcmyanmar.ted.data.models;

import com.padcmyanmar.ted.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted.network.NewestDataAgent;

public class NewestModel {

    private static NewestModel objInstance;

    private NewestDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private NewestModel() {
        mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
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
}
