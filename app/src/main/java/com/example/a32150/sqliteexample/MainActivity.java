package com.example.a32150.sqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String DB_FILE;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textview);
        DB_FILE = getFilesDir() + File.separator + "mydata.sqlite";

        try {
            File f = new File(DB_FILE);
            if (! f.exists()) {
                InputStream is = getResources().openRawResource(R.raw.mydata);
                OutputStream os = new FileOutputStream(DB_FILE);
                int read;
                while ((read = is.read()) != -1) {
                    os.write(read);
                }
                os.close();
                is.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//http://android-deve.blogspot.tw/2012/12/sqlite.html    SQLite教學網頁
    public void OnClick(View v) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.rawQuery("Select * from Students", null);

//        c.moveToFirst();
//        Log.d("DATA", c.getString(1) + "," + c.getString(2));
        String str="";
        while (c.moveToNext()) {
            String name = c.getString(1);
            String phone = c.getString(2);
            Log.d("DATA", "name: "+ name + ", phone: " + phone);
            str +="name: "+ name + ", phone: " + phone+"\n";
        }
        tv.setText(str);

    }

}
