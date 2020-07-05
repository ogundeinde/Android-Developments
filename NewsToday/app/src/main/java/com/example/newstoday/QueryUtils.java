package com.example.newstoday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryUtils {
    public static final String LOG_TAG = QueryUtils.class.getName();

    public QueryUtils() {
    }

    public List<Articles> fetchArticlesListData(String url){
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url1 = createUrl(url);
        String jsonResponse = makeHttpRequest(url1);
        List<Articles> articles = extractArticlesFromJson(jsonResponse);
        return articles;
    }

    private URL createUrl(String url){
        URL url1 = null;
        try{
            url1 =  new URL(url);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url1;
    }

    private String makeHttpRequest(URL url){
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readJsonResult(inputStream);
            }
            else {
                Log.e(LOG_TAG, "Error");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    private String readJsonResult(InputStream inputStream){
        StringBuilder result = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedInputStream.readLine();
                while (line != null){
                    result.append(line);
                    line = bufferedInputStream.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    private List<Articles> extractArticlesFromJson(String jsonResponse){
        String author = null;
        List<Articles> articlesArrayList = new ArrayList<>();
        try {

            JSONObject articlesData = new JSONObject(jsonResponse);
            JSONObject response = articlesData.optJSONObject("response");
            JSONArray results = response.optJSONArray("results");
            for(int i = 0; i < results.length(); i++){
                JSONObject resultsObject = results.optJSONObject(i);

                String title = resultsObject.optString("webTitle");

                JSONArray tags = resultsObject.optJSONArray("tags");
                JSONObject tagsObject = tags.optJSONObject(0);
                if(tagsObject != null){
                    author = tagsObject.optString("webTitle");
                }

                String publicationDateResult = resultsObject.optString("webPublicationDate");
                String publicationDate = publicationDateResult.split("T")[0];
//                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat format2 = new SimpleDateFormat("MMM DD, yyyy");
//                Date dateType = format1.parse(publicationDate);
                String date = publicationDate;

                String url = resultsObject.optString("webUrl");

                String category = resultsObject.optString("sectionName");

                JSONObject fields = resultsObject.optJSONObject("fields");
                String thumbnail = fields.optString("thumbnail");
                Bitmap image = null;
                URL imageUrl = new URL(thumbnail);
                image = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());

                Articles articles = new Articles(title, author, date, url, category, image);
                articlesArrayList.add(articles);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the articles JSON results", e);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesArrayList;
    }
}
