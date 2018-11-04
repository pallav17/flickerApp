package com.example.pshah1.flickerapi.utlis;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public final static String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=8eef5bcc246a9e8d6973f008baec24b3&text=london&safe_search=1&format=json&nojsoncallback=1&extras=url_m";

    private final static String TAG = "NetworkUtils";

    public static URL buildURL(String flickerStringUrl){ //BASE_URL
        Uri uri = Uri.parse(flickerStringUrl); // parse string to URL

        URL url = null;
        try {
            url = new URL(uri.toString());
        }catch (Exception e){
           e.printStackTrace();
        }

        Log.w(TAG, "Build URL -----------------------------------------" + url.toString());

        return url;
    }


    public static String getResponse(URL buildUrl) throws IOException {
        // Make HTTP connection - establish connection
        HttpURLConnection urlConnection = (HttpURLConnection) buildUrl.openConnection();

        try {
         InputStream inputStream =  urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection. disconnect();
    }

    }
}
