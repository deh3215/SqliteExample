package com.example.a32150.sqliteexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void clickAdd(View v)    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        EditText ed1 = (EditText)findViewById(R.id.txname);
        EditText ed2 = (EditText)findViewById(R.id.txphone);
        EditText ed3 = (EditText)findViewById(R.id.txaddress);
        String username = ed1.getText().toString();
        String phone = ed2.getText().toString();
        String address = ed3.getText().toString();
        //String str = "Insert Into phone (username, tel) values ('" + username + "', '" + tel + "')";
        String str = "Insert Into Students (name, phone, address) values ('" + username + "', '" + phone + "', '" + address + "')";
        //String str = "DELETE FROM Students WHERE id = '1'";
        db.execSQL(str);

        finish();
    }
}


