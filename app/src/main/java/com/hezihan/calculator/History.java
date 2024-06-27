package com.hezihan.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ListView lv;
    private DBHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private String calcName="";
    private final String []EmptyList={"还没有历史记录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);//设置标题栏
        setSupportActionBar(toolbar);

        lv= findViewById(R.id.listView);
        dbHelper=new DBHelper(this);
        calcName=getIntent().getStringExtra("calcName");//获取计算器名称
        ArrayList<String> list = dbHelper.showHistory(calcName);//获取历史记录
        if(!list.isEmpty())
            adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        else
            adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, EmptyList);
        lv.setAdapter(adapter);
    }

    public void onClick(View v)//清空历史记录
    {
        dbHelper.deleteRecords(calcName);
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, EmptyList);
        lv.setAdapter(adapter);
    }
}