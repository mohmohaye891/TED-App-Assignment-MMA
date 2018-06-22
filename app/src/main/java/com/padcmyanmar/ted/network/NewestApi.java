package com.padcmyanmar.ted.network;

import com.padcmyanmar.ted.network.responses.GetNewestResponse;
import com.padcmyanmar.ted.utils.NewestConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewestApi {

    @FormUrlEncoded
    @POST(NewestConstants.GET_TED)
    Call<GetNewestResponse> loadNewestList(
            @Field(NewestConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(NewestConstants.PARAM_PAGE) int page);
}
