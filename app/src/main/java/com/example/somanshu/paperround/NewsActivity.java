package com.example.somanshu.paperround;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class NewsActivity extends AppCompatActivity  {
    ArrayList<News>news;
    NewsAdapter adap;
    @Override
    //Executed first when this activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        int num=getIntent().getIntExtra("num",0);
        Log.e("NUM",""+num);
        setTitle(API.getTitle(num));
        Log.e("URL ",API.getnewsurl(num));
        String url =API.getnewsurl(num);
        news=new ArrayList<News>();
        QueryUtils queryUtils = new QueryUtils(this);
        queryUtils.execute(url);

    }
    private class QueryUtils extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        private Context mContext;

        public QueryUtils(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(this.mContext);
            progressDialog.setMessage("Fetching News");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... urls) {
            String link=urls[0];
            HttpURLConnection connection=null;
            InputStream stream=null;
            BufferedReader reader=null;
            URL url;
            StringBuffer stringBuffer;
            try
            {
                url=new URL(link);
                connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                stream=connection.getInputStream();
                if(stream==null)
                {
                    return null;
                }
                reader=new BufferedReader(new InputStreamReader(stream));
                String line;
                stringBuffer=new StringBuffer();
                while((line=reader.readLine())!=null)
                {
                    stringBuffer.append(line);
                }
                return stringBuffer.toString();
            }
            catch (Exception e)
            {
                return null;
            }
            finally
            {
                if(connection!=null)
                {
                    connection.disconnect();
                }
                if(stream!=null)
                {
                    try {
                        stream.close();
                    }
                    catch (IOException e)
                    {
                        return null;
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String jsonstring) {
            if(jsonstring==null||jsonstring=="")
            {
                Toast.makeText(mContext,"No Internet Connection",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            else
            {
                try
                {
                    JSONObject root=new JSONObject(jsonstring);
                    JSONArray arr=root.getJSONArray("articles");
                    for(int i=0;i<arr.length();i++)
                    {
                        news.add(new News(arr.getJSONObject(i)));
                    }
                }
                catch (Exception e)
                {
                    progressDialog.dismiss();
                }
                ListView rootView=(ListView)findViewById(R.id.sportview);
                adap=new NewsAdapter(mContext,news);
                rootView.setAdapter(adap);
                progressDialog.dismiss();
            }
        }
    }
}
