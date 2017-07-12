package com.example.somanshu.paperround;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by somanshu on 7/7/17.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        super(context, 0, newsArrayList);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        final News news=getItem(position);
        TextView title=(TextView)convertView.findViewById(R.id.newstitle);
        ImageView iv=(ImageView)convertView.findViewById(R.id.newimg);
        title.setText(news.getTitle());
        Picasso.with(context).load(news.getImgurl()).into(iv);
        Log.e("Adapter Class","Image downloaded");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(context,NewsDetailsActivity.class);
                it.putExtra("news",news);
                context.startActivity(it);
            }
        });
        return convertView ;
    }
}
