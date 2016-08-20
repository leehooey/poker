package com.example.lee.ad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;


/**
 * Created by Lee on 2016/1/20.
 */
public class RelativeView extends RelativeLayout{
    static int numb[] = new int[52];
    int id;
    int clickNumber = 1;
    int[] numbs = new int[24];//存放剩余24张牌
    static int count = 0;
    static int score = 0;
    static int cardWidth, cardHeight, w, h;

    public RelativeView(Context context) {
        super(context);
        initeRelaTive();
    }

    public RelativeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initeRelaTive();
    }

    public RelativeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initeRelaTive();
    }

    private void initeRelaTive() {
        new Thread(new NumbToCard()).start();
    }

    public void reStart() {
        RelativeView relativeView = (RelativeView) findViewById(R.id.relative);
        relativeView.removeViews(0, 31);//移除所有view
        count = 0;
        score = 0;
        Score.num = 1;
        MainActivity.setScore(0, 24);
        Onclicked.flag = new boolean[29];//初始化
        new Thread(new NumbToCard()).start();
        addCards(cardWidth, cardHeight, w, h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//获取屏幕宽高
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.max(w, h) - 100) / 10;
        this.cardWidth = cardWidth;
        this.cardHeight = cardWidth * 3 / 2;
        this.w = w;
        this.h = h;
        addCards(this.cardWidth, this.cardHeight, w, h);
    }

    private void addCards(final int cardWidth, final int cardHeight, final int w, final int h) {
        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.fapai);
        mp.start();
        final Card[] cards = new Card[19];//存储卡片对象
        for (int i = 1; i <= 31; i++) {
            final Card c = new Card(getContext());
            c.setBackGround();
            c.setNumb(i);
            RelativeLayout.LayoutParams lp;
            if(i==31){
                lp = new RelativeLayout.LayoutParams(cardWidth, cardWidth/2);
            }else{
               lp = new RelativeLayout.LayoutParams(cardWidth, cardHeight);
            }

            int[] a = Interface.getInterface(cardWidth, cardHeight, w, h, i);
            lp.leftMargin = a[0];
            lp.topMargin = a[1];

            addView(c, lp);

            int[] d = Interface.getInterface(cardWidth, cardHeight, w, h, 30);
            int[] b = Interface.getInterface(cardWidth, cardHeight, w, h, c.getNumb());//获取当前牌的位置
            TranslateAnimation ta = new TranslateAnimation(d[0] - b[0], 0, d[1] - b[1], 0);//相对位置 x位置从0到200，y位置从0到200
            ta.setDuration(1000);
            c.startAnimation(ta);

            if (i <= 28) {
                c.setCardNumb(numb[i - 1]);
                if (i <= 18) {
                    cards[i] = c;
                } else {//最后一行显示
                    int num = c.getCardNumb();
                    c.setBackgroundResource(NumbToCard.cards[num]);
                }
            }
            if (i == 29) {
                c.setText("发牌");
                c.setTextSize(15);
                c.setTextColor(Color.WHITE);
                for (int j = 0; j < 24; j++) {
                    numbs[j] = numb[j + 28];
                }
            }
            if (i == 30) c.setId(id);
            if (i == 31) {
                c.setTextColor(Color.WHITE);
                c.setTextSize(10);
                c.setWidth(100);
                c.setText("+");
                c.setBackgroundResource(R.drawable.newgame);
            }
            c.setOnClickListener(new OnClickListener() {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0) {
                            MainActivity.setScore(score, 24 - count);
                        }
                    }
                };

                @Override
                public void onClick(View v) {
                    if (c.getNumb() <= 28 && count > 0 && Onclicked.CanOnclick(c.getNumb(), c.getCardNumb(), numbs[count - 1]) == true) {
                        MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.click);
                        mp.start();

                        //混合动画效果
                        int[] a = Interface.getInterface(cardWidth, cardHeight, w, h, 30);
                        int[] b = Interface.getInterface(cardWidth, cardHeight, w, h, c.getNumb());//获取当前牌的位置

                        AnimationSet as = new AnimationSet(true);//使用同一个部件
                        as.setDuration(1000);
                        RotateAnimation ra = new RotateAnimation(0, 720, cardWidth / 2, cardHeight / 2);
                        as.addAnimation(ra);
                        TranslateAnimation ta = new TranslateAnimation(0, a[0] - b[0], 0, a[1] - b[1]);
                        as.addAnimation(ta);
                        c.startAnimation(as);

                        c.setVisibility(INVISIBLE);//隐藏卡片

                        //自动翻转没有被覆盖卡片
                        new Thread(new AutoReversal(c, cards)).start();

                        numbs[count - 1] = c.getCardNumb();
                        //延迟改变button壁纸
                        final Button bt = (Button) findViewById(id);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bt.setBackgroundResource(NumbToCard.cards[numbs[count - 1]]);
                            }
                        }, 800);
                        //计分规则
                        Score.setScore(c.getNumb(), clickNumber);
                        clickNumber = clickNumber + 2;
                        //判断是否结束
                        if (IsGameOver.gameOver() == true) {
                            GameOverDialog g = new GameOverDialog();
                            g.Dialog((Activity) getContext(), score, 1);
                        }
                    }
                    if (c.getNumb() == 29) {
                        MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.onclick);
                        mp.start();
                        clickNumber = 1;
                        handler.sendEmptyMessage(0);
                        Button bt = (Button) findViewById(id);

                        if (count < 24) {
                            TranslateAnimation ta = new TranslateAnimation((cardWidth + 10) * 3 - ((w / 2) - cardWidth / 2), 0, 0, 0);
                            ta.setDuration(500);
                            bt.startAnimation(ta);
                            bt.setBackgroundResource(NumbToCard.cards[numbs[count]]);
                            count++;
                        } else {
                            MainActivity.setScore(score, 0);
                            bt.setText("已发完");
                            bt.setTextSize(15);
                            bt.setTextColor(Color.GREEN);
                            GameOverDialog g = new GameOverDialog();
                            g.Dialog((Activity) getContext(), score, 2);
                        }
                    }
                    if (c.getNumb() == 31) reStart();
                }
            });
        }
    }
}
