package com.example.relay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsListActivity extends AppCompatActivity implements AsyncResponse {
    private static final String SEARCH_KEY = "content.search_key";
    String searchKey;
    ClientAPI client = new ClientAPI();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        client.delegate = this;
        searchKey = getIntent().getStringExtra(MainActivity.SEARCH_KEY);
        client.execute(searchKey);
        textView = findViewById(R.id.textView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        String title = "Showing results for \""+ searchKey +"\"";
        actionBar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void processFinish(String output) {
        textView.setText(output);
    }
}