package com.example.relay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ArticleListAdapter extends ArrayAdapter<NewsListActivity> {
    private static final String TAG = "ArticleListAdapter";
    private Context mContext;

    public ArticleListAdapter(Context context, int resource, ArrayList<Article> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    }
}
