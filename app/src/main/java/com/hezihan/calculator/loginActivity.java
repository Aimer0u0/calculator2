package com.hezihan.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class loginActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw;
    private String userName,psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);//设置为竖屏
        init();
    }
    public void init(){
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        Button btn_login = findViewById(R.id.btn_login);
        TextView tv_register = findViewById(R.id.tv_register);
        Intent intent = getIntent();
        String username = intent.getStringExtra("userName");
        String password= intent.getStringExtra("password");
        if(!TextUtils.isEmpty(username)&& !TextUtils.isEmpty(password)){
            et_user_name.setText(username);
            et_psw.setText(password);
        }
        btn_login.setOnClickListener(view -> {
            userName = et_user_name.getText().toString().trim();
            psw = et_psw.getText().toString().trim();
            SharedPreferences sharedPreferences = getSharedPreferences("SEND",MODE_PRIVATE);
            Map<String,?>map = sharedPreferences.getAll();
            if (usernameISValid(map,userName) && passwordISVales(map,psw)){
                Toast.makeText(loginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                loginActivity.this.finish();
                Intent intent1 = new Intent(loginActivity.this,MainActivity.class);
                intent1.putExtra("username",userName);
                startActivity(intent1);
            }
        });
        tv_register.setOnClickListener(view -> {
            Intent intent12 = new Intent(loginActivity.this,registerActivity.class);
            startActivity(intent12);
        });
    }

    private boolean passwordISVales(Map<String,?> map, String psw) {
        if(TextUtils.isEmpty(psw)){
            Toast.makeText(loginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else {
            for(Map.Entry<String,?> m : map.entrySet()){
                if (m.getValue().equals(psw)){
                    return  true;
                }
            }
            Toast.makeText(loginActivity.this, "密码有误", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean usernameISValid(Map<String,?> map, String userName) {
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(loginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }else {
            for (Map.Entry<String,?> m :map.entrySet()){
                if(m.getKey().equals(userName)){
                    return true;
                }
            }
            Toast.makeText(loginActivity.this, "您未注册", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
