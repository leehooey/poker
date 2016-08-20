package com.example.lee.ad;

import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.concurrent.Delayed;

/**
 * Created by Lee on 2016/1/22.
 */
public class Score {
    static int num=1;
    static public void setScore(int i, int numb) {
        Handler handle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                MainActivity.tvS.setText("");
            }
        };
        if (i <= 3) {
            ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
            sa.setDuration(500);
            MainActivity.tvS.startAnimation(sa);
            if(num==1){
                RelativeView.score = RelativeView.score + numb * 100 + 500;
                MainActivity.tvS.setText("+500");
                handle.sendEmptyMessageDelayed(0,2000);

            }else if(num==2){
                RelativeView.score = RelativeView.score + numb * 100 + 1000;
                MainActivity.tvS.setText("+1000");
                handle.sendEmptyMessageDelayed(0,2000);
            }else if(num==3){
                RelativeView.score = RelativeView.score + numb * 100 + 5000;
                MainActivity.tvS.setText("+5000");
                handle.sendEmptyMessageDelayed(0,2000);
            }
            MainActivity.setScore(RelativeView.score, 23 - RelativeView.count + 1);
            num++;
        } else {
            RelativeView.score = RelativeView.score + numb * 100;
            MainActivity.setScore(RelativeView.score, 23 - RelativeView.count + 1);
        }
    }
}
