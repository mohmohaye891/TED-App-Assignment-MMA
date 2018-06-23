package com.padcmyanmar.ted.network;

import com.padcmyanmar.ted.events.ApiErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetNewestEvent;
import com.padcmyanmar.ted.network.responses.GetNewestResponse;
import com.padcmyanmar.ted.utils.NewestConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewestDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;

    private NewestApi mTheApi;

    RetrofitDataAgentImpl(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewestConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(NewestApi.class);

    }

    public static RetrofitDataAgentImpl getInstance(){
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }
    @Override
    public void LoadNewestList(int page, String accessToken) {
        Call<GetNewestResponse> loadNewestCall = mTheApi.loadNewestList(accessToken, page);
        loadNewestCall.enqueue(new Callback<GetNewestResponse>() {
            @Override
            public void onResponse(Call<GetNewestResponse> call, Response<GetNewestResponse> response) {
                GetNewestResponse newestResponse = response.body();
                if (newestResponse != null && newestResponse.isResponseOK()){
                    SuccessGetNewestEvent event = new SuccessGetNewestEvent(newestResponse.getMmTalk());
                    EventBus.getDefault().post(event);
                }else {
                    if (newestResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    }else {
                        ApiErrorEvent event = new ApiErrorEvent(newestResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewestResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });

    }
}
