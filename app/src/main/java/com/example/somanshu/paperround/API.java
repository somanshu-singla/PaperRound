package com.example.somanshu.paperround;

/**
 * Created by somanshu on 12/7/17.
 */

public class API {
    static String[] category=new String[6];
    static String[] actionbar=new String[6];
    static String url="https://newsapi.org/v1/articles?source=";
    static String apikey="&apiKey=fcfc12e0cf8a4bef8bdf786b549c73e8&sortBy=latest";
    static String sortkey="&sortBy=top&sortBy=latest";
    public static String getnewsurl(int index)
    {
        category[1]="talksport";
        category[2]="bloomberg";
        category[3]="daily-mail";
        category[4]="techradar";
        category[5]="national-geographic";
        return url+category[index]+sortkey+apikey;
    }
    public static String getTitle(int index)
    {
        actionbar[1]="Sports News";
        actionbar[2]="Business News";
        actionbar[3]="Entertainment News";
        actionbar[4]="Technology News";
        actionbar[5]="Nature News";
        return actionbar[index];
    }
}
