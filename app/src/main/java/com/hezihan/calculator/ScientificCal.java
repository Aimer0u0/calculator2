package com.hezihan.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScientificCal extends AppCompatActivity {

    private EditText e1,e2;
    private int count=0;
    private String expression="";
    private DBHelper dbHelper;
    private Button mode,toggle,square,xpowy,log,sin,cos,tan,sqrt,fact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_cal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        mode = findViewById(R.id.mode);
        toggle = findViewById(R.id.toggle);
        square = findViewById(R.id.square);
        xpowy = findViewById(R.id.xpowy);
        log = findViewById(R.id.log);
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        sqrt= findViewById(R.id.sqrt);
        fact = findViewById(R.id.factorial);

        dbHelper=new DBHelper(this);

        e2.setText("");
        mode.setTag(1);
        toggle.setTag(1);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v)
    {
        int toggleMode = (int) toggle.getTag();
        int angleMode = ((int) mode.getTag());
        switch (v.getId()) {//切换按钮

            case R.id.toggle:
                if(toggleMode ==1)
                {
                    toggle.setTag(2);
                    square.setText(R.string.cube);
                    xpowy.setText(R.string.tenpow);
                    log.setText(R.string.naturalLog);
                    sin.setText(R.string.sininv);
                    cos.setText(R.string.cosinv);
                    tan.setText(R.string.taninv);
                    sqrt.setText(R.string.cuberoot);
                    fact.setText(R.string.Mod);
                }
                else if(toggleMode ==2)
                {
                    toggle.setTag(3);
                    square.setText(R.string.square);
                    xpowy.setText(R.string.epown);
                    log.setText(R.string.log);
                    sin.setText(R.string.hyperbolicSine);
                    cos.setText(R.string.hyperbolicCosine);
                    tan.setText(R.string.hyperbolicTan);
                    sqrt.setText(R.string.inverse);
                    fact.setText(R.string.factorial);
                }
                else if(toggleMode ==3)
                {
                    toggle.setTag(1);
                    sin.setText(R.string.sin);
                    cos.setText(R.string.cos);
                    tan.setText(R.string.tan);
                    sqrt.setText(R.string.sqrt);
                    xpowy.setText(R.string.xpown);
                }
                break;

            case R.id.mode://角度模式
                if(angleMode ==1)
                {
                    mode.setTag(2);
                    mode.setText(R.string.mode2);
                }
                else
                {
                    mode.setTag(1);
                    mode.setText(R.string.mode1);
                }
                break;

            case R.id.num0:
                e2.setText(e2.getText() + "0");
                break;

            case R.id.num1:
                e2.setText(e2.getText() + "1");
                break;

            case R.id.num2:
                e2.setText(e2.getText() + "2");
                break;

            case R.id.num3:
                e2.setText(e2.getText() + "3");
                break;


            case R.id.num4:
                e2.setText(e2.getText() + "4");
                break;

            case R.id.num5:
                e2.setText(e2.getText() + "5");
                break;

            case R.id.num6:
                e2.setText(e2.getText() + "6");
                break;

            case R.id.num7:
                e2.setText(e2.getText() + "7");
                break;

            case R.id.num8:
                e2.setText(e2.getText() + "8");
                break;

            case R.id.num9:
                e2.setText(e2.getText() + "9");
                break;

            case R.id.pi://圆周率
                e2.setText(e2.getText() + "pi");
                break;

            case R.id.dot://小数点
                if (count == 0 && e2.length() != 0) {
                    e2.setText(e2.getText() + ".");
                    count++;
                }
                break;

            case R.id.clear:
                e1.setText("");
                e2.setText("");
                count = 0;
                expression = "";
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
                    if(newText.equals("-")||newText.endsWith("sqrt")||newText.endsWith("log")||newText.endsWith("ln")
                            ||newText.endsWith("sin")||newText.endsWith("asin")||newText.endsWith("asind")||newText.endsWith("sinh")
                            ||newText.endsWith("cos")||newText.endsWith("acos")||newText.endsWith("acosd")||newText.endsWith("cosh")
                            ||newText.endsWith("tan")||newText.endsWith("atan")||newText.endsWith("atand")||newText.endsWith("tanh")
                            ||newText.endsWith("cbrt"))
                    {
                        newText="";
                    }
                    else if(newText.endsWith("^")||newText.endsWith("/"))
                        newText=newText.substring(0,newText.length()-1);
                    else if(newText.endsWith("pi")||newText.endsWith("e^"))
                        newText=newText.substring(0,newText.length()-2);
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

            case R.id.sqrt://平方根
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    toggleMode =(int)toggle.getTag();
                    if(toggleMode ==1)
                        e2.setText("sqrt(" + text + ")");
                    else if(toggleMode ==2)
                        e2.setText("cbrt(" + text + ")");
                    else
                        e2.setText("1/(" + text + ")");
                }
                break;

            case R.id.square://平方
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(toggleMode ==2)
                        e2.setText("(" + text + ")^3");
                    else
                        e2.setText("(" + text + ")^2");
                }
                break;

            case R.id.xpowy://x的y次方
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(toggleMode ==1)
                        e2.setText("(" + text + ")^");
                    else if(toggleMode ==2)
                        e2.setText("10^(" + text + ")");
                    else
                        e2.setText("e^(" + text + ")");
                }
                break;

            case R.id.log://对数
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(toggleMode ==2)
                        e2.setText("ln(" + text + ")");
                    else
                        e2.setText("log(" + text + ")");
                }
                break;

            case R.id.factorial://阶乘
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(toggleMode ==2)
                    {
                        e1.setText("(" + text + ")%");
                        e2.setText("");
                    }
                    else
                    {
                        StringBuilder res= new StringBuilder();
                        try
                        {
                            CalculateFactorial cf=new CalculateFactorial();
                            int []arr=cf.factorial((int)Double.parseDouble(String.valueOf(new ExtendedDoubleEvaluator().evaluate(text))));
                            int res_size=cf.getRes();
                            if(res_size>20)
                            {
                                for (int i=res_size-1; i>=res_size-20; i--)
                                {
                                    if(i==res_size-2)
                                        res.append(".");
                                    res.append(arr[i]);
                                }
                                res.append("E").append(res_size - 1);
                            }
                            else
                            {
                                for (int i=res_size-1; i>=0; i--)
                                {
                                    res.append(arr[i]);
                                }
                            }
                            e2.setText(res.toString());
                        }
                        catch (Exception e)
                        {
                            if(e.toString().contains("ArrayIndexOutOfBoundsException"))
                            {
                                e2.setText("结果太大了!");
                            }
                            else
                                e2.setText("错误!!");
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case R.id.sin:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(angleMode ==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if(toggleMode ==1)
                            e2.setText("sin(" + angle + ")");
                        else if(toggleMode ==2)
                            e2.setText("asind(" + text + ")");
                        else
                            e2.setText("sinh(" + text + ")");
                    }
                    else
                    {
                        if(toggleMode ==1)
                            e2.setText("sin(" + text + ")");
                        else if(toggleMode ==2)
                            e2.setText("asin(" + text + ")");
                        else
                            e2.setText("sinh(" + text + ")");
                    }
                }
                break;

            case R.id.cos:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(angleMode ==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if(toggleMode ==1)
                            e2.setText("cos(" + angle + ")");
                        else if(toggleMode ==2)
                            e2.setText("acosd(" + text + ")");
                        else
                            e2.setText("cosh(" + text + ")");
                    }
                    else
                    {
                        if(toggleMode ==1)
                            e2.setText("cos(" + text + ")");
                        else if(toggleMode ==2)
                            e2.setText("acos(" + text + ")");
                        else
                            e2.setText("cosh(" + text + ")");
                    }
                }
                break;

            case R.id.tan:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if(angleMode ==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if(toggleMode ==1)
                            e2.setText("tan(" + angle + ")");
                        else if(toggleMode ==2)
                            e2.setText("atand(" + text + ")");
                        else
                            e2.setText("tanh(" + text + ")");
                    }
                    else
                    {
                        if(toggleMode ==1)
                            e2.setText("tan(" + text + ")");
                        else if(toggleMode ==2)
                            e2.setText("atan(" + text + ")");
                        else
                            e2.setText("tanh(" + text + ")");
                    }
                }
                break;

            case R.id.posneg://正负号
                if (e2.length() != 0) {
                    String s = e2.getText().toString();
                    char[] arr = s.toCharArray();
                    if (arr[0] == '-')
                        e2.setText(s.substring(1));
                    else
                        e2.setText("-" + s);
                }
                break;

            case R.id.equal:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    expression = e1.getText().toString() + text;
                }
                e1.setText("");
                if (expression.length() == 0)
                    expression = "0.0";
                try {
                    Double result = new ExtendedDoubleEvaluator().evaluate(expression);
                    if (String.valueOf(result).equals("6.123233995736766E-17"))
                    {
                        result =0.0;
                        e2.setText(String.valueOf(result));
                    }
                    else if(String.valueOf(result).equals("1.633123935319537E16"))
                        e2.setText("infinity");
                    else
                        e2.setText(String.valueOf(result));
                    if (!expression.equals("0.0"))
                        dbHelper.insert("SCIENTIFIC", expression + " = " + result);
                } catch (Exception e) {
                    e2.setText("输入错误");
                    e1.setText("");
                    expression = "";
                    e.printStackTrace();
                }
                break;

            case R.id.openBracket://左括号
                e1.setText(e1.getText() + "(");
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
                Intent i = new Intent(this, History.class);
                i.putExtra("calcName", "SCIENTIFIC");
                startActivity(i);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void operationClicked(String op) {
        if (e2.length() != 0) {
            String text = e2.getText().toString();
            e1.setText(e1.getText() + text + op);
            e2.setText("");
            count = 0;
        } else {
            String text = e1.getText().toString();
            if (text.length() > 0) {
                String newText = text + op;
                e1.setText(newText);
            }
        }
    }
}