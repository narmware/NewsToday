package com.narmware.newstoday.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.narmware.newstoday.pojo.Category;
import com.narmware.newstoday.pojo.HomeMenu;
import com.narmware.newstoday.pojo.HomeNews;
import com.narmware.newstoday.pojo.News;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess
{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    String rs;

    public  DatabaseAccess(Context context) {
        this.openHelper=new DatabaseOpenHelper(context);
    }


    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */


    public static DatabaseAccess getInstance(Context context)
    {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }

    }

//for NewsDetails table

    public void setData(String news_link, String img_path, String news_title, String news_desc, String news_name, String news_date, String news_content)
    {
        ContentValues values=new ContentValues();
        values.put("news_link",news_link);
        values.put("img_path",img_path);
        values.put("news_title",news_title);
        values.put("news_desc",news_desc);
        values.put("news_name",news_name);
        values.put("news_date",news_date);
        values.put("news_content",news_content);
        Log.d("stringCon",news_content);
        database.insert("NewsDetails",null,values);

    }

    public ArrayList<HomeNews> Details() {
        ArrayList<HomeNews> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM NewsDetails", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {

            String title=(cursor.getString(0));
            String desc=(cursor.getString(1));
            String name=(cursor.getString(2));
            String path=(cursor.getString(3));
            String date=(cursor.getString(4));
            String  link=(cursor.getString(5));
            String content=(cursor.getString(6));
            HomeNews homeNews=new HomeNews(link,path,title,desc,name,date,content);
            list.add(homeNews);
            cursor.moveToNext();
        }
        cursor.close();
        return list ;

    }


//for NewsCategories

    public ArrayList<Category> CatDetails() {
        ArrayList<Category> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM NewsCategoris", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
           int  id=(cursor.getInt(0));
           int  count=(cursor.getInt(1));
           String name=(cursor.getString(2));


            Category category=new Category(id,count,name);
            list.add(category);
            cursor.moveToNext();

        }
        cursor.close();
        return  list;

    }


    public void setNewsCategoris( int id,int count,String name)
    {

        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("count",count);
        values.put("name",name);

        database.insert("NewsCategoris",null,values);
    }

    //for OtherNews table
    public void setOtherNews(String news_link,String img_path, String news_title, String news_desc,String news_name,String news_date,int id,String news_content)
    {
        ContentValues values=new ContentValues();
        values.put("news_link",news_link);
        values.put("img_path",img_path);
        values.put("news_title",news_title);
        values.put("news_desc",news_desc);
        values.put("news_name",news_name);
        values.put("news_date",news_date);
        values.put("id",id);
        values.put("news_content",news_content);
        database.insert("OtherNews",null,values);

    }


    public ArrayList<News> getOtherNews(int id)
    {
        ArrayList<News> newslist = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM  OtherNews WHERE id="+id,null);


        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {

            String title=(cursor.getString(0));
            String desc=(cursor.getString(1));
            String path=(cursor.getString(2));
            String name=(cursor.getString(3));
            String date=(cursor.getString(4));
            String  link=(cursor.getString(5));
            String content=(cursor.getString(7));

             //id=(cursor.getInt(6));
            News news=new News(link,path,title,desc,name,date,id,content);
            newslist.add(news);
            cursor.moveToNext();
        }
        cursor.close();
        return newslist ;
        }


        public void Delete()
    {
        database.execSQL("delete from NewsDetails");
    }
}
