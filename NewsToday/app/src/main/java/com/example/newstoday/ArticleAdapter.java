package com.example.newstoday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Articles> {

    public ArticleAdapter(@NonNull Context context, ArrayList<Articles> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Articles articles = getItem(position);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.title);
        textView.setText(articles.getArticleTitle());

        TextView textView1 = (TextView) listItemView.findViewById(R.id.date);
        textView1.setText(articles.getArticleDate());

        TextView textView2 = (TextView) listItemView.findViewById(R.id.author);
        textView2.setText(articles.getArticleAuthor());

        TextView textView3 = (TextView) listItemView.findViewById(R.id.category);
        textView3.setText(articles.getArticleCategory());

        ImageView articleThumbnail = (ImageView) listItemView.findViewById(R.id.image);
        articleThumbnail.setImageBitmap(formatImageFromBitmap(articles.getArticleImage()));


        return listItemView;
    }

    private Bitmap formatImageFromBitmap(Bitmap articleThumbnail) {
        // Bitmap for image
        Bitmap returnBitmap;
        // Check thumbnail valid
        if (articleThumbnail == null) {
            // If not valid return default image
            returnBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.noimage);
        } else {
            // If valid return image
            returnBitmap = articleThumbnail;
        }
        // Return bitmap
        return returnBitmap;
    }
}
