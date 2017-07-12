package com.example.somanshu.paperround;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static com.example.somanshu.paperround.R.id.sport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView sports=(ImageView) findViewById(R.id.sport);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(MainActivity.this,NewsActivity.class);
                Log.e("MainActivity","Index called - 1");
                news.putExtra("num",1);
                startActivity(news);
            }
        });
        //Business News
        ImageView business=(ImageView)findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(MainActivity.this,NewsActivity.class);
                Log.e("MainActivity","Index called - 2");
                news.putExtra("num",2);
                startActivity(news);
            }
        });
        //Entertainment News
        ImageView ent=(ImageView)findViewById(R.id.ent);
        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(MainActivity.this,NewsActivity.class);
                Log.e("MainActivity","Index called - 3");
                news.putExtra("num",3);
                startActivity(news);
            }
        });
        //Technology News
        ImageView tech=(ImageView)findViewById(R.id.tech);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(MainActivity.this,NewsActivity.class);
                Log.e("MainActivity","Index called - 4");
                news.putExtra("num",4);
                startActivity(news);
            }
        });
        //Nature News
        ImageView nature=(ImageView)findViewById(R.id.nature);
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(MainActivity.this,NewsActivity.class);
                Log.e("MainActivity","Index called - 5");
                news.putExtra("num",5);
                startActivity(news);
            }
        });
    }
}
