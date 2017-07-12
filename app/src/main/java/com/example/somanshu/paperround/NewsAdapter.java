package com.example.somanshu.paperround;

import android.content.Context;
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
    private IntentCall callintent;
    private Context context;
    public NewsAdapter(Context context, ArrayList<News> newsArrayList,IntentCall var) {
        super(context, 0, newsArrayList);
        this.callintent=var;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        final News var=getItem(position);
        TextView tv=(TextView)convertView.findViewById(R.id.newstitle);
        ImageView iv=(ImageView)convertView.findViewById(R.id.newimg);
        tv.setText(var.getTitle());
        Picasso.with(context).load(var.getImgurl()).into(iv);
        Log.e("Image downloaded","Placed");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    callintent.call(var);
            }
        });
        return convertView ;
    }
}
