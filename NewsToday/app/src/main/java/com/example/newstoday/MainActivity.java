package com.example.newstoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener, LoaderManager.LoaderCallbacks<List<Articles>> {
    private TextView mEmptyStateTextView;
    public static ArticleAdapter adapter;
    private static final String QUERY = "https://content.guardianapis.com/search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView articleListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);


        adapter = new ArticleAdapter(this, new ArrayList<Articles>());

        articleListView.setAdapter(adapter);
        articleListView.setOnItemClickListener(this);

        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
            }
        else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Articles articles = adapter.getItem(i);
        Uri articleUri = Uri.parse(articles.getArticleUrl());

        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

        startActivity(websiteIntent);
    }

    @Override
    public Loader<List<Articles>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(QUERY);

        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", "debates");
        uriBuilder.appendQueryParameter("api-key", "test");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("show-fields", "thumbnail");

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Articles>> loader, List<Articles> data) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_articles);

        adapter.clear();

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Articles>> loader) {
        adapter.clear();
    }

}