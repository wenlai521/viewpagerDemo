package com.caijin.spannablestring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    private Button button1,button2,button3,button4,button5,button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }
    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
    }
    private void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }
    
    /** 
     * @Title: onClick 
     * @Description: (非 JavaDoc)
     * @param v 
     * @see android.view.View.OnClickListener#onClick(android.view.View) 
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (v.getId()) {
        case R.id.button1:
            intent.setClass(MainActivity.this, FirstActivity.class);
            break;
        case R.id.button2:
            intent.setClass(MainActivity.this, SecondActivity.class);
            break;
        case R.id.button3:
            intent.setClass(MainActivity.this, ThreeActivity.class);
            break;
        case R.id.button4:
            intent.setClass(MainActivity.this, FourActivity.class);
            break;
        case R.id.button5:
            intent.setClass(MainActivity.this, ThreeActivity.class);
            break;
        case R.id.button6:
            intent.setClass(MainActivity.this, ThreeActivity.class);
            break;

        default:
            break;
        }
        startActivity(intent);
    }
}
