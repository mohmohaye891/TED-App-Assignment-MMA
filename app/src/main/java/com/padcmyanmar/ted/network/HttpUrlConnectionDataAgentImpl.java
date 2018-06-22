package com.padcmyanmar.ted.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.ted.data.models.NewestModel;
import com.padcmyanmar.ted.events.ApiErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetNewestEvent;
import com.padcmyanmar.ted.network.responses.GetNewestResponse;
import com.padcmyanmar.ted.utils.NewestConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpUrlConnectionDataAgentImpl implements NewestDataAgent {

    private static HttpUrlConnectionDataAgentImpl objInstance;

    public HttpUrlConnectionDataAgentImpl() {
    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance(){
        if (objInstance == null){
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void LoadNewestList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    // create the HttpURLConnection
                    url = new URL(NewestConstants.API_BASE + NewestConstants.GET_TED);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // just want to do an HTTP POST here
                    connection.setRequestMethod("POST"); //3.

                    // uncomment this if you want to write output to this url
                    //connection.setDoOutput(true);

                    // give it 15 seconds to respond
                    connection.setReadTimeout(15 * 1000); //4. ms

                    connection.setDoInput(true); //5.
                    connection.setDoOutput(true);

                    //put the request parameter into NameValuePair list.
                    List<NameValuePair> params = new ArrayList<>(); // 6
                    params.add(new BasicNameValuePair(NewestConstants.PARAM_ACCESS_TOKEN,
                            accessToken));
                    params.add(new BasicNameValuePair(NewestConstants.PARAM_PAGE,
                            String.valueOf(page)));

                    //write the parameters from NameValuePair list into connection obj.
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));

                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    connection.connect(); //7.

                    // read the output from the server
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //8.
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null){
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString(); //9.

                    return responseString;


                }catch (Exception e) {
                    Log.e("",e.getMessage());
                } finally {
                    // close the reader; this can throw an exception too, so
                    // wrap it in another try/catch block.
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
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

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
