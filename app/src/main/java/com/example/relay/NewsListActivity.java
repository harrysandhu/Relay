package com.example.relay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
        try {
            JSONObject res = new JSONObject(output);
            JSONArray articles = res.getJSONArray("articles");
            ArrayList<Article> articleList = new ArrayList<>();
            for (int i = 0; i < articles.length(); i++){
                articleList.add(new Article(articles.getJSONObject(i)));
            }

            for (int i = 0; i < articles.length(); i++){
                Log.i("a", articleList.get(i).toString());
            }

            ListView mListView = (ListView) findViewById(R.id.listView);
            textView.setText(articleList.toString());

            ArticleListAdapter adapter = new ArticleListAdapter(this, R.layout.activity_news_list, articleList);
            mListView.setAdapter(adapter);

        }catch (Exception e){

        }

    }
}