package com.hezihan.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class StandardCal extends AppCompatActivity {

    private EditText e1,e2;
    private int count=0;
    private String expression="";
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_cal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1= findViewById(R.id.editText1);
        e2= findViewById(R.id.editText2);
        dbHelper=new DBHelper(this);

        e2.setText("");
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.num0:
                e2.setText(e2.getText()+"0");
                break;

            case R.id.num1:
                e2.setText(e2.getText()+"1");
                break;

            case R.id.num2:
                e2.setText(e2.getText()+"2");
                break;

            case R.id.num3:
                e2.setText(e2.getText()+"3");
                break;


            case R.id.num4:
                e2.setText(e2.getText()+"4");
                break;

            case R.id.num5:
                e2.setText(e2.getText()+"5");
                break;

            case R.id.num6:
                e2.setText(e2.getText()+"6");
                break;

            case R.id.num7:
                e2.setText(e2.getText()+"7");
                break;

            case R.id.num8:
                e2.setText(e2.getText()+"8");
                break;

            case R.id.num9:
                e2.setText(e2.getText()+"9");
                break;

            case R.id.dot:
                if(count==0 && e2.length()!=0)
                {
                    e2.setText(e2.getText()+".");
                    count++;
                }
                break;

            case R.id.clear:
                e1.setText("");
                e2.setText("");
                count=0;
                expression="";
                break;

            case R.id.backSpace:
                String text = e2.getText().toString();
                if(text.length()>0)
                {
                    if(text.endsWith("."))
                    {
                        count=0;
                    }
                    String newText= text.substring(0, text.length()-1);
                    if(text.endsWith(")"))
                    {
                        char []a= text.toCharArray();
                        int pos=a.length-2;
                        int counter=1;
                        for(int i=a.length-2;i>=0;i--)
                        {
                            if(a[i]==')')
                            {
                                counter++;
                            }
                            else if(a[i]=='(')
                            {
                                counter--;
                            }
                            else if(a[i]=='.')
                            {
                                count=0;
                            }
                            if(counter==0)
                            {
                                pos=i;
                                break;
                            }
                        }
                        newText= text.substring(0,pos);
                    }
                    if(newText.equals("-")||newText.endsWith("sqrt"))
                    {
                        newText="";
                    }
                    else if(newText.endsWith("^"))
                        newText=newText.substring(0,newText.length()-1);

                    e2.setText(newText);
                }
                break;

            case R.id.plus:
                operationClicked("+");
                break;

            case R.id.minus:
                operationClicked("-");
                break;

            case R.id.divide:
                operationClicked("/");
                break;

            case R.id.multiply:
                operationClicked("*");
                break;

            case R.id.sqrt:
                if(e2.length()!=0)
                {
                    text =e2.getText().toString();
                    e2.setText("sqrt(" + text + ")");
                }
                break;

            case R.id.square:
                if(e2.length()!=0)
                {
                    text =e2.getText().toString();
                    e2.setText("("+ text +")^2");
                }
                break;

            case R.id.posneg:
                if(e2.length()!=0)
                {
                    String s=e2.getText().toString();
                    char[] arr =s.toCharArray();
                    if(arr[0]=='-')
                        e2.setText(s.substring(1));
                    else
                        e2.setText("-"+s);
                }
                break;

            case R.id.equal:
                if(e2.length()!=0)
                {
                    text =e2.getText().toString();
                    expression=e1.getText().toString()+ text;
                }
                e1.setText("");
                if(expression.length()==0)
                    expression="0.0";
                new DoubleEvaluator();
                try
                {
                    Double result = new ExtendedDoubleEvaluator().evaluate(expression);
                    if(!expression.equals("0.0"))
                        dbHelper.insert("STANDARD",expression+" = "+ result);
                    e2.setText(String.valueOf(result));
                }
                catch (Exception e)
                {
                    e2.setText("输入错误");
                    e1.setText("");
                    expression="";
                    e.printStackTrace();
                }
                break;

            case R.id.openBracket:
                e1.setText(e1.getText()+"(");
                break;

            case R.id.closeBracket:
                if(e2.length()!=0) {
                    e1.setText(e1.getText() + e2.getText().toString() + ")");
                    e2.setText("");
                }
                else
                    e1.setText(e1.getText() + ")");
                break;

            case R.id.history:
                Intent i=new Intent(this,History.class);
                i.putExtra("calcName","STANDARD");
                startActivity(i);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void operationClicked(String op)
    {
        if(e2.length()!=0)
        {
            String text=e2.getText().toString();
            e1.setText(e1.getText() + text+op);
            e2.setText("");
            count=0;
        }
        else
        {
            String text=e1.getText().toString();
            if(text.length()>0)
            {
                String newText=text+op;
                e1.setText(newText);
            }
        }
    }
}