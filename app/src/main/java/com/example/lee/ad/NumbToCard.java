package com.example.lee.ad;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Lee on 2016/1/19.
 */
public class NumbToCard implements Runnable{
    public NumbToCard() {

    }
    static int numb[] = new int[52];
    static public int[] cards=new int[]{R.drawable.bei,
            R.drawable.honga,R.drawable.hong2,R.drawable.hong3,R.drawable.hong4,R.drawable.hong5,R.drawable.hong6,R.drawable.hong7,R.drawable.hong8,R.drawable.hong9,R.drawable.hong10,R.drawable.hongj,R.drawable.hongq,R.drawable.hongk,
            R.drawable.meia,R.drawable.mei2,R.drawable.mei3,R.drawable.mei4,R.drawable.mei5,R.drawable.mei6,R.drawable.mei7,R.drawable.mei8,R.drawable.mei9,R.drawable.mei10,R.drawable.meij,R.drawable.meiq,R.drawable.meik,
            R.drawable.heia,R.drawable.hei2,R.drawable.hei3,R.drawable.hei4,R.drawable.hei5,R.drawable.hei6,R.drawable.hei7,R.drawable.hei8,R.drawable.hei9,R.drawable.hei10,R.drawable.heij,R.drawable.heiq,R.drawable.heik,
            R.drawable.fanga,R.drawable.fang2,R.drawable.fang3,R.drawable.fang4,R.drawable.fang5,R.drawable.fang6,R.drawable.fang7,R.drawable.fang8,R.drawable.fang9,R.drawable.fang10,R.drawable.fangj,R.drawable.fangq,R.drawable.fangk};
    @Override
    public void run() {
        numb= new int[52];
        Random random = new Random();
        for (int i = 0; i < 52; i++) {
            int num = random.nextInt(52) + 1;
            for (int j = 0; j < 52; j++) {
                if (numb[j] == num) {
                    i--;
                    break;
                }
                if (j == 51) {
                    numb[i] = num;
                }
            }
        }
        for(int i=0;i<52;i++){
            RelativeView.numb[i]=numb[i];
        }
    }
}
