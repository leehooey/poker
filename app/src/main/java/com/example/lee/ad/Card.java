package com.example.lee.ad;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Lee on 2016/1/20.
 */
public class Card extends Button {
    public Card(Context context) {
        super(context);
        setNumb(0);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int numb = 0;
    int cardNumb;


    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public void setCardNumb(int cardNumb) {
        this.cardNumb = cardNumb;
    }

    public int getCardNumb() {
        return cardNumb;
    }

    public void setBackGround() {
        setBackgroundResource(R.drawable.bei);
    }


}
