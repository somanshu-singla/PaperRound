package com.example.somanshu.paperround;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Intent it=getIntent();
        News news=it.getExtras().getParcelable("var");
        Picasso.with(this).load(news.getImgurl()).into((ImageView)findViewById(R.id.newsimage));
        ((TextView)findViewById(R.id.newstitle)).setText(news.getTitle());
        ((TextView)findViewById(R.id.author)).setText(news.getAuthor());
        ((TextView)findViewById(R.id.date)).setText((news.getPdate()));
        ((TextView)findViewById(R.id.newsdesc)).setText(news.getDescription());
        final String link=news.getUrllink();
        //http://www.vogella.com/tutorials/AndroidIntent/article.html
        ((TextView)findViewById(R.id.web)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(link));
                startActivity(i);
            }
        });
    }
}
