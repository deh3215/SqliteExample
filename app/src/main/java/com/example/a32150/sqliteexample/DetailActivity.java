package com.example.a32150.sqliteexample;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    int id;
    TextView  txname, txphone, txaddr;
    TextView txid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txid =(TextView)findViewById(R.id.tvid);
        txname= (TextView)findViewById(R.id.tvname);
        txphone= (TextView)findViewById(R.id.tvphone);
        txaddr= (TextView)findViewById(R.id.tvaddr);

        Intent it = getIntent();
        id=it.getIntExtra("id", -1);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("Students", new String[] {"id", "name", "phone", "address"}, "id=?", new String[] {String.valueOf(id)},null,null,null);

        if(c.moveToFirst()) {
            txid.setText("ID: "+c.getInt(0));
            txname.setText("Name: "+c.getString(1));
            txphone.setText("Phone: "+c.getString(2));
            txaddr.setText("Address "+c.getString(3));
        }
    }

    @Override
    protected void onResume() {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("Students", new String[] {"id", "name", "phone", "address"}, "id=?", new String[] {String.valueOf(id)},null,null,null);

        if(c.moveToFirst()) {
            txid.setText("ID: "+c.getInt(0));
            txname.setText("Name: "+c.getString(1));
            txphone.setText("Phone: "+c.getString(2));
            txaddr.setText("Address "+c.getString(3));
        }

        super.onResume();
    }

    public void  clickEdit(View v)  {
        Intent it = new Intent(this, EditActivity.class);
        it.putExtra("id", id);
        Log.d("id", "id = "+id);
        startActivity(it);
    }


//    DELETE FROM "表格名"
//    WHERE "條件";

    public void clickDelete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("請確認刪除");
        builder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
                //db.delete("Students", "id=?", new String[] {String.valueOf(id)});
                db.execSQL("DELETE FROM 'Students' WHERE id='"+id+"'");
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void clickBack(View v)
    {
        finish();
    }

}
