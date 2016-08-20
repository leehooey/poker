package com.example.lee.ad;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView tv,tvS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText("当前得分:" + 0 + "分" + '\n' + "还剩" + 24 + "张牌");
        tv.setTextSize(17);
        tv.setTextColor(Color.GREEN);


        tvS= (TextView) findViewById(R.id.tvS);
        tvS.setPadding(getWindowManager().getDefaultDisplay().getWidth() / 2, getWindowManager().getDefaultDisplay().getWidth() / 25, 0, 0);
        tvS.setTextColor(Color.RED);
        tvS.setTextSize(30);
    }

    static public void setScore(int numb, int num) {
        tv.setText("当前得分:" + numb + "分" + '\n' + "还剩" + num + "张牌");
    }

}
