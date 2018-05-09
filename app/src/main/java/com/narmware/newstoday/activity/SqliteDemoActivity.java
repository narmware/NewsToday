package com.narmware.newstoday.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.narmware.newstoday.R;
import com.narmware.newstoday.db.DatabaseAccess;

public class SqliteDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);



        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        databaseAccess.open();
       // databaseAccess.setData();
        //String info= databaseAccess.Details();
       // databaseAccess.Delete();
       // String Afterdelete = databaseAccess.Details();
        databaseAccess.close();
        //  Log.d("info",info);

       // System.out.println("NewsData : " +info);
      //  System.out.println("DeleteData : "+ Afterdelete);



    }
}
