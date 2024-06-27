package com.hezihan.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String title = intent.getStringExtra("username")+"的计算器";
        TextView tv_title = findViewById(R.id.textView);
        tv_title.setText(title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClick(View view)
    {
        Intent i;
        if(view.getId()==R.id.button1)
        {
            i=new Intent(this,StandardCal.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.button)
        {
            i=new Intent(this,ScientificCal.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.button2)
        {
            i=new Intent(this, UnitConverter.class);
            startActivity(i);
        }
    }
}