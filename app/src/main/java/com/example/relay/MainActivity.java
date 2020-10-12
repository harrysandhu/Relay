package com.example.relay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    ClientAPI client = new ClientAPI();
    TextView textView;
    String response;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client.delegate = this;
        client.execute("Spacex");
        textView = findViewById(R.id.textView);
        }


    @Override
    public void processFinish(String output)  {
        response = output;
        Log.i("RESULTFINAL", response);
        try {
            JSONObject res = new JSONObject(response);
            Log.i("RESULTFINAL", (String)res.get("totalResults").toString());
            textView.setText( (String)res.toString());
        }catch (Exception e){
            textView.setText(e.getMessage());
        }

    }
}

//        str
//
//        final String sample = {
//                "author": "fafa",
//                "title" :" fdfsd",
//                "desc": "fdsfdsfsd"
//        }
//
//        article_json = APIClient.filter(URL, news_json_string)
//        article_json = APIClient.filter(URL, news_json_string)
//        intent.putExtra(ARTICLE, article_json);