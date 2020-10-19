package com.example.relay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NewsListActivity extends AppCompatActivity implements AsyncResponse {
    static final String SEARCH_KEY = "content.search_key";
    static final String ARTICLE = "content.article";
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
            final HashMap<String, Article> articleMap = new HashMap<>();
            ArrayList<String> articleTitles = new ArrayList<>();
            for (int i = 0; i < articles.length(); i++){
                Article a = new Article(articles.getJSONObject(i));
                articleList.add(a);
                articleTitles.add(a.getTitle());
                articleMap.put(a.getTitle(), a);
            }

            for (int i = 0; i < articles.length(); i++){
                Log.i("a", articleList.get(i).toString());
            }
            Log.i("adapterlist", articleTitles.toString());

            ListView listView = (ListView) findViewById(R.id.article_list_view);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_view_item, articleTitles);
//            ArticleListAdapter adapter = new ArticleListAdapter(this, R.layout.list_view_item, articleTitles);
            listView.setAdapter(adapter);
            final Intent intent = new Intent(this, NewsArticleActivity.class);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String title = ((TextView)view).getText().toString();
                    Log.i("MAIN_ACTIVITY", articleMap.get(title).getJsonString());
                    intent.putExtra(ARTICLE, articleMap.get(title).getJsonString());
                    startActivity(intent);
                }
            });
        }catch (Exception e){

        }

    }
}