package com.example.somanshu.paperround;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SportActivity extends AppCompatActivity implements IntentCall {

    String str="";
    ArrayList<News>news;
    NewsAdapter adap;
    String[] apilinks=new String[6];
    String[] actionbar=new String[6];
    String select ;
    @Override
    //Executed first when this activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        apilinks[1]="https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8";
        apilinks[2]="https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8";
        apilinks[3]="https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8";
        apilinks[4]="https://newsapi.org/v1/articles?source=engadget&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8";
        apilinks[5]="https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8\n" +
                "\n";
        actionbar[1]="Sports News";
        actionbar[2]="Business News";
        actionbar[3]="Entertainment News";
        actionbar[4]="Technology News";
        actionbar[5]="Nature News";
        int num;
        News foo=getIntent().getExtras().getParcelable("num");
        num=foo.getId();
        Log.e("NUM",""+foo.getId());
        setTitle(actionbar[num]);
        select=apilinks[num];
        //initializes the array
        news=new ArrayList<News>();
        //Starts another thread which makes HTTPRerquest and fetch news data
        QueryUtils obj=new QueryUtils(this);
        obj.execute(select);
    }
    //creates and returns news adapter
    public NewsAdapter getadapter()
    {
        return new NewsAdapter(this,news,this);
    }
    //Interface call (made from the adapter class )
    public void call(News news)
    {
        Intent it=new Intent(SportActivity.this,NewsDetailsActivity.class);
        it.putExtra("var",news);
        startActivity(it);
    }
    //Interface call (made from the adapter class )
    public void setImage(String str, ImageView iv)
    {
        Picasso
                .with(this)
                .load(str).resize(400,400)
                .into(iv);
    }
    private class QueryUtils extends AsyncTask<String,Void,String> {
        ProgressDialog progDialog;
        private Context mContext;

        public QueryUtils(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDialog = new ProgressDialog(this.mContext);
            progDialog.setMessage("Fetching News");
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.show();
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
                    return "";
                }
                reader=new BufferedReader(new InputStreamReader(stream));
                String foo;
                json=new StringBuffer();
                while((foo=reader.readLine())!=null)
                {
                    json.append(foo);
                }
                str=json.toString();
            }
            catch (Exception e)
            {
                str="";
                return "";
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
                        return "";
                    }
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            if(str==null||str=="")
            {
                Toast.makeText(mContext,"Internet Error",Toast.LENGTH_SHORT);
                progDialog.dismiss();
            }
            else
            {
                try
                {
                    JSONObject root=new JSONObject(str);
                    JSONArray arr=root.getJSONArray("articles");
                    for(int i=0;i<arr.length();i++)
                    {
                        news.add(new News(arr.getJSONObject(i)));
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(mContext,"Internet Error",Toast.LENGTH_SHORT);
                    progDialog.dismiss();
                }
                ListView rootView=(ListView)findViewById(R.id.sportview);
                adap=getadapter();
                rootView.setAdapter(adap);
                progDialog.dismiss();
            }
        }
    }
}
