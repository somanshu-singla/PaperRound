package com.example.somanshu.paperround;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by somanshu on 7/7/17.
 */

public class News implements Parcelable {
    private String title ;
    private String description ;
    private String author ;
    private String urllink ;
    private String imgurl ;
    private  String pdate;
    private int id;
    public String getTitle()
    {
        return title ;
    }
    public String getDescription()
    {
        return description ;
    }
    public String getAuthor()
    {
        return author ;
    }
    public  String getUrllink()
    {
        return urllink ;
    }
    public String getImgurl()
    {
        return imgurl ;
    }
    public String getPdate()
    {
        return pdate ;
    }
    public int getId(){return id;}
    public void setId(int num)
    {
        id=num;
        Log.e("News Id",""+num);
    }
    public News()
    {

    }
    public News(JSONObject var)
    {
        try {
            title=var.getString("title");
            author=var.getString("author");
            description=var.getString("description");
            urllink=var.getString("url");
            imgurl=var.getString("urlToImage");
            pdate=var.getString("publishedAt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(author);
        parcel.writeString(urllink);
        parcel.writeString(imgurl);
        parcel.writeString(pdate);
        parcel.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    private News(Parcel in)
    {
        title=in.readString();
        description=in.readString();
        author=in.readString();
        urllink=in.readString();
        imgurl=in.readString();
        pdate=in.readString();
        id=in.readInt();
    }
    public static final Parcelable.Creator<News> CREATOR
            = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
