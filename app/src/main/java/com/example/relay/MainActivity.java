package com.example.relay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse, EditText.OnEditorActionListener {

    static final String SEARCH_KEY = "content.search_key";
    ClientAPI client = new ClientAPI();
    EditText searchTF;
    String response;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        client.delegate = this;
        searchTF = findViewById(R.id.searchTF);
        searchTF.setOnEditorActionListener(this);

        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
       String searchKey = (String) searchTF.getText().toString();
       Intent newsListIntent = new Intent(this, NewsListActivity.class);
       newsListIntent.putExtra(SEARCH_KEY, searchKey);
       startActivity(newsListIntent);
       return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void processFinish(String output)  {
        response = output;
        Log.i("RESULTFINAL", response);
        try {
            JSONObject res = new JSONObject(response);
            JSONArray articles = res.getJSONArray("articles");
            ArrayList<Article> articleList = new ArrayList<>();
            for (int i = 0; i < articles.length(); i++){
                articleList.add(new Article(articles.getJSONObject(i)));
            }

            for (int i = 0; i < articles.length(); i++){
                Log.i("a", articleList.get(i).toString());
            }


        }catch (Exception e){

        }

    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}

