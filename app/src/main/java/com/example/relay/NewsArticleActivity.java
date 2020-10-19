package com.example.relay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URL;

public class NewsArticleActivity extends AppCompatActivity {
    static final String SEARCH_KEY = "content.search_key";
    static final String ARTICLE = "content.article";

    String articleJson;
    TextView articleTitle;
    TextView articleAuthor;
    TextView articleDate;
    TextView articleDescription;
    ImageView articleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);
        articleJson = getIntent().getStringExtra(NewsListActivity.ARTICLE);
        articleTitle = findViewById(R.id.articleTitle);
        articleAuthor = findViewById(R.id.articleAuthor);
        articleDate = findViewById(R.id.articleDate);
        articleDescription = findViewById(R.id.articleDescription);
        articleImage = findViewById(R.id.articleImage);
        try {
            Article a = new Article(new JSONObject(articleJson));
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            String title = a.getSource();
            actionBar.setTitle(title);


            articleTitle.setText(a.getTitle());
            articleAuthor.setText((a.getAuthor()));
            articleDate.setText(a.getPublishedAt());
            articleDescription.setText(a.getDescription());
            new DownloadImageTask(articleImage)
                    .execute(a.getUrlToImage());
        }catch (Exception e){

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}