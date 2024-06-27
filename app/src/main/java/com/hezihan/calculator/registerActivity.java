package com.hezihan.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw,et_psw_again;
    private RadioGroup rg_gender;

    private String userName,psw,pswAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        init();
    }
    public void init(){
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_pswAgain);
        rg_gender = findViewById(R.id.GenerRadio);
        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                getString();
                int sex;
                int sexChosenId = rg_gender.getCheckedRadioButtonId();
                switch (sexChosenId){
                    case R.id.girl:
                        sex = 0;
                        break;
                    case R.id.boy:
                        sex = 1;
                        break;
                    default:
                        sex = -1;
                        break;
                }
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(registerActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(registerActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(registerActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (sex<0) {
                    Toast.makeText(registerActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(registerActivity.this, "输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, loginActivity.class);
                    intent.putExtra("userName",userName);
                    intent.putExtra("psw",psw);
                    startActivity(intent);
                    SharedPreferences sharedPreferences = getSharedPreferences("SEND", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(userName,psw);
                    edit.apply();
                    registerActivity.this.finish();
                }
            }

            private void getString() {
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                pswAgain = et_psw_again.getText().toString().trim();
            }
        });
    }
}
