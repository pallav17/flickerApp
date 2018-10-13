package com.example.pshah1.flickerapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pshah1.flickerapi.utlis.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String[] titleArray;
    private String[] imageUrlArray;
    private ProgressBar progressBar;
    private sampleAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_progress);

        URL url =  NetworkUtils.buildURL(NetworkUtils.BASE_URL);
        new GithubGetRepo().execute(url);
        recyclerView = findViewById(R.id.recycler_view);


    }



    class GithubGetRepo extends AsyncTask<URL,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(URL... urls) {
            String data = null;
            try {
                data = NetworkUtils.getResponse(urls[0]);
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                JSONObject obj1 = new JSONObject(data);
                JSONObject obj2 = obj1.getJSONObject("photos");
                JSONArray jsonArray = obj2.getJSONArray("photo");
                int length = jsonArray.length();
                titleArray = new String[length];
                imageUrlArray = new String[length];
                for (int i = 0; i< length ; i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    titleArray[i] = object.getString("title");
                    imageUrlArray[i] = object.getString("url_m");

                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return data;
        }

        @Override
            protected void onPostExecute(String data) {
            super.onPostExecute(data);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new sampleAdapter(MainActivity.this, titleArray, imageUrlArray);
            recyclerView.setAdapter(adapter);

        }
    }
}
