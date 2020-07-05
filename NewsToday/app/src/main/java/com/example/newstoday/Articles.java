package com.example.newstoday;

import android.graphics.Bitmap;

public class Articles {
    private String articleTitle;
    private String articleAuthor;
    private String articleDate;
    private String articleUrl;
    private String articleCategory;
    private Bitmap articleImage;


    public Articles(String articleTitle, String articleAuthor, String articleDate, String articleUrl, String articleCategory, Bitmap articleImage){
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleDate = articleDate;
        this.articleUrl = articleUrl;
        this.articleCategory = articleCategory;
        this.articleImage = articleImage;

    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public Bitmap getArticleImage() {
        return articleImage;
    }
}
