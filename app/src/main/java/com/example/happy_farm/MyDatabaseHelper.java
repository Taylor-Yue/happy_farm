package com.example.happy_farm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //查找已有数据库
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE login1(id INTEGER PRIMARY KEY AUTOINCREMENT,user VARCHAR(30),password VACHAR(30))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int version, int newVersion){

    }
}
