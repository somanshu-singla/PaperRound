package com.example.somanshu.paperround;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class NewsActivity extends AppCompatActivity implements IntentCall {
    ArrayList<News>news;
    NewsAdapter adap;
    String[] apilinks=new String[6];
    String[] actionbar=new String[6];
    String select ;
    @Override
    //Executed first when this activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        apilinks[1]="https://newsapi.org/v1/articles?source=talksport&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
        apilinks[2]="https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
        apilinks[3]="https://newsapi.org/v1/articles?source=daily-mail&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
        apilinks[4]="https://newsapi.org/v1/articles?source=gruenderszene&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
        apilinks[5]="https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
        actionbar[1]="Sports News";
        actionbar[2]="Business News";
        actionbar[3]="Entertainment News";
        actionbar[4]="Technology News";
        actionbar[5]="Nature News";
        int num=getIntent().getIntExtra("num",0);
        Log.e("NUM",""+num);
        setTitle(actionbar[num]);
        select=apilinks[num];
        //initializes the array
        news=new ArrayList<News>();
        //Starts another thread which makes HTTPRerquest and fetch news data
        QueryUtils queryUtils=new QueryUtils(this);
        queryUtils.execute(select);
    }
    //creates and returns news adapter
    public NewsAdapter getadapter()
    {
        return new NewsAdapter(this,news,this);
    }
    public void call(News news)
    {
        Intent it=new Intent(NewsActivity.this,NewsDetailsActivity.class);
        it.putExtra("var",news);
        startActivity(it);
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
            StringBuffer json;
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
                json=new StringBuffer();
                while((line=reader.readLine())!=null)
                {
                    json.append(line);
                }
                return json.toString();
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
                Toast.makeText(mContext,"Internet Error",Toast.LENGTH_SHORT);
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
                    Toast.makeText(mContext,"Internet Error",Toast.LENGTH_SHORT);
                    progressDialog.dismiss();
                }
                ListView rootView=(ListView)findViewById(R.id.sportview);
                adap=getadapter();
                rootView.setAdapter(adap);
                progressDialog.dismiss();
            }
        }
    }
}
