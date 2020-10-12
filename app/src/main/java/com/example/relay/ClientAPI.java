package com.example.relay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientAPI extends AsyncTask<String, Void, String>{

    private String url;
    private static StringBuffer BaseURL = new StringBuffer("http://newsapi.org/v2/everything?q=&from=2020-10-05&sortBy=publishedAt&apiKey=b38c027d148f4a0c88abeba6d67b337e");
    private String res;
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
        String url = BaseURL.insert(35, searchKey).toString();
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



