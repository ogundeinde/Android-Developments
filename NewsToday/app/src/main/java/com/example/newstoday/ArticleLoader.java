package com.example.newstoday;

import android.content.Context;
import android.content.AsyncTaskLoader;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Articles>> {

    private String mUrl;


    public ArticleLoader(Context context, String url){
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<Articles> loadInBackground(){
        if(mUrl==null){
            return null;
        }

        QueryUtils queryUtils=new QueryUtils();
        List<Articles> articles=queryUtils.fetchArticlesListData(mUrl);
        return articles;
    }
}
