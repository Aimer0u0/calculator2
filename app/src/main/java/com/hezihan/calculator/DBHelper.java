package com.hezihan.calculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {//数据库操作类

    private static final String database_Name="HISTORY.DB";
    private static final int database_Version=1;
    private static final String TAG="DATABASE OPERATIONS";
    private static final String table_Name="history";
    private static final String column1="calculator_name";
    private static final String column2="expression";
    private static final String create_Table="CREATE TABLE "+table_Name+"("+column1+" TEXT,"+column2+" TEXT);";

    SQLiteDatabase db;
    public DBHelper(Context context) {
        super(context,database_Name,null,database_Version);//创建数据库
        Log.i(TAG,"Database Created / Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建表
        db.execSQL(create_Table);
        Log.i(TAG,"Table Created");
    }

    public void insert(String calcName,String expression) {//插入表达式
        db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(column1,calcName);
        contentValues.put(column2, expression);
        db.insert(table_Name, null, contentValues);
        db.close();
    }

    @SuppressLint("Recycle")
    public ArrayList<String> showHistory(String calcName) {//显示表达式
        db=getReadableDatabase();
        Cursor cursor;
        ArrayList<String> list= new ArrayList<>();
        String []selectionArgs={calcName};
        //查询表达式，返回表达式列表
        cursor=db.rawQuery("select * from "+table_Name+" where "+column1+" = ?",selectionArgs);
        if(cursor.moveToFirst()) {
            do {
                String expression=cursor.getString(1);
                list.add(expression);//将表达式添加到列表
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    //删除表达式
    public void deleteRecords(String calcName){//删除表达式
        db=getWritableDatabase();
        String[] value ={calcName};
        db.delete(table_Name, column1 + "=?", value);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}//更新数据库
}
