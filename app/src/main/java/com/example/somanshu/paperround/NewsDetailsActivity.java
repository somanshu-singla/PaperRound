package com.example.somanshu.paperround;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Intent it=getIntent();
        News news=it.getExtras().getParcelable("news");
        Picasso.with(this).load(news.getImgurl()).into((ImageView)findViewById(R.id.newsimage));
        ((TextView)findViewById(R.id.newstitle)).setText(news.getTitle());
        ((TextView)findViewById(R.id.author)).setText("Author : "+news.getAuthor());
        String pdate=news.getPdate();
        String date="N/A";
        String time="N/A";
        if(pdate!=null)
        {
            date=(String)pdate.subSequence(0,10);
            time=(String)pdate.subSequence(11,19);
        }
        ((TextView)findViewById(R.id.date)).setText("Date : "+date);
        ((TextView)findViewById(R.id.time)).setText("Time : "+time);
        ((TextView)findViewById(R.id.newsdesc)).setText(news.getDescription());
        final String link=news.getUrllink();
        ((TextView)findViewById(R.id.web)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse(link));
                startActivity(it);
            }
        });
    }
}
