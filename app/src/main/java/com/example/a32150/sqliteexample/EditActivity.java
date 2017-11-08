package com.example.a32150.sqliteexample;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed1 = (EditText)findViewById(R.id.editname);
        ed2 = (EditText)findViewById(R.id.editphone);
        ed3 = (EditText)findViewById(R.id.editaddr);

//        String username = ed1.getText().toString();
//        String phone = ed2.getText().toString();
//        String address = ed3.getText().toString();

        Intent it = getIntent();
        id = it.getIntExtra("id", -1);
        Log.d("id", "id = "+id);

        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("Students", new String[] {"id", "name", "phone", "address"}, "id=?", new String[] {String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()) {
            ed1.setText(c.getString(1));
            ed2.setText(c.getString(2));
            ed3.setText(c.getString(3));
        }
    }

//    UPDATE Store_Information
//    SET Sales = 500
//    WHERE Store_Name = 'Los Angeles'
//    AND Txn_Date = 'Jan-08-1999';

    public void clickUpdate(View v)   {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("name", ed1.getText().toString());
        cv.put("phone", ed2.getText().toString());
        cv.put("address", ed3.getText().toString());
        db.update("Students", cv, "id=?", new String[] {String.valueOf(id)});
        db.close();
        finish();
    }
    public void clickBack(View v)
    {
        finish();
    }
}
