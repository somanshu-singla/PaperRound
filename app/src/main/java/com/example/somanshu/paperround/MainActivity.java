package com.example.somanshu.paperround;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import static com.example.somanshu.paperround.R.id.sport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int num=0;
        //SportActivity Implicit Intent
        LinearLayout sport=(LinearLayout)findViewById(R.id.sport);
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport=new Intent(MainActivity.this,SportActivity.class);
                News news=new News();
                news.setId(1);
                Log.e("FOOL",""+news.getId());
                sport.putExtra("num",news);
                startActivity(sport);
            }
        });
        //BusinessActivity Explicit Intent
        LinearLayout business=(LinearLayout)findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport=new Intent(MainActivity.this,SportActivity.class);
                News news=new News();
                news.setId(2);
                sport.putExtra("num",news);
                startActivity(sport);
            }
        });
        //EntertainmentActivity Intent
        LinearLayout ent=(LinearLayout)findViewById(R.id.ent);
        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport=new Intent(MainActivity.this,SportActivity.class);
                News news=new News();
                news.setId(3);
                sport.putExtra("num",news);
                startActivity(sport);
            }
        });
        //TechnologyActivity Intent
        LinearLayout tech=(LinearLayout)findViewById(R.id.tech);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport=new Intent(MainActivity.this,SportActivity.class);
                News news=new News();
                news.setId(4);
                sport.putExtra("num",news);
                startActivity(sport);
            }
        });
        //NatureActivity Intent
        LinearLayout nature=(LinearLayout)findViewById(R.id.nature);
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport=new Intent(MainActivity.this,SportActivity.class);
                News news=new News();
                news.setId(5);
                sport.putExtra("num",news);
                startActivity(sport);
            }
        });
    }
}
