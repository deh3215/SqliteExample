package com.example.a32150.sqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_FILE;
    TextView tv;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        mylist = new ArrayList<>();
        DB_FILE = getFilesDir() + File.separator + "mydata.sqlite";
        copyDBFile();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        //Cursor c = db.rawQuery("Select * from Students", null);
        Cursor c = db.query("Students", new String[] {"id", "name", "phone", "address"}, null,null,null,null,null);
        if (c.moveToFirst())
        {
            do {
                mylist.add(c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2)+" - " + c.getString(3));
            } while (c.moveToNext());
        }
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(adapter);
    }
//http://android-deve.blogspot.tw/2012/12/sqlite.html    SQLite教學網頁


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add)   {




        }
        return super.onOptionsItemSelected(item);
    }

    public void copyDBFile()
    {
        try {
            File f = new File(DB_FILE);
            if (! f.exists())
            {
                InputStream is = getResources().openRawResource(R.raw.mydata);
                OutputStream os = new FileOutputStream(DB_FILE);
                int read;
                while ((read = is.read()) != -1)
                {
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

}
