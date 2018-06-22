package com.padcmyanmar.ted.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.ted.events.ApiErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetNewestEvent;
import com.padcmyanmar.ted.network.responses.GetNewestResponse;
import com.padcmyanmar.ted.utils.NewestConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements NewestDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;
    private OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl(){
        mHttpClient = new OkHttpClient.Builder()//1
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

   public static RetrofitDataAgentImpl getInstance(){
       if (sObjInstance == null) {
           sObjInstance = new RetrofitDataAgentImpl();
       }
        return sObjInstance;
   }

    @Override
    public void LoadNewestList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody formBody = new FormBody.Builder()
                        .add(NewestConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(NewestConstants.PARAM_PAGE, String.valueOf(page))
                        .build();//2.

                Request request = new Request.Builder() //3
                        .url(NewestConstants.API_BASE + NewestConstants.GET_TED)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute(); //4.
                    if (response.isSuccessful()){
                        String responseString = response.body().string();

                        return responseString;
                    }
                } catch (Exception e) {
                    Log.e("LoadNewList", e.getMessage());

                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);

                Gson gson = new Gson();
                GetNewestResponse newestResponse = gson.fromJson(responseString, GetNewestResponse.class);
                Log.d("onPostExecute", "News List Size :" + newestResponse.getMmTalk().size());

                if (newestResponse.isResponseOK()){
                    SuccessGetNewestEvent event = new SuccessGetNewestEvent(newestResponse.getMmTalk());
                    EventBus.getDefault().post(event);
                }else {
                    ApiErrorEvent event = new ApiErrorEvent(newestResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }
        }.execute();
    }
}
