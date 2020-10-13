package com.example.relay;

import android.os.AsyncTask;
import android.util.Log;

import org.joda.time.DateTime;
import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientAPI extends AsyncTask<String, Void, String>{

    private static final String API_KEY = "b38c027d148f4a0c88abeba6d67b337e";
    OkHttpClient client = new OkHttpClient();
    public AsyncResponse delegate = null;


    protected String doInBackground(String... keys){
        return get(keys[0]);
    }

    protected void onProgressUpdate() {

    }

    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }



    public String get(String searchKey){

        DateTime datetime = new DateTime(new Date());
        datetime = datetime.minusDays(7);
        String dateStr = datetime.getYear() + "-" +datetime.monthOfYear().getAsString() + "-" + datetime.dayOfMonth().getAsString();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("newsapi.org")
                .addPathSegments("v2/everything")
                .addQueryParameter("q", searchKey)
                .addQueryParameter("from", dateStr)
                .addQueryParameter("sortBy", "publishedAt")
                .addQueryParameter("apiKey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }catch(IOException e){
            return e.getMessage();
        }
    }

}



