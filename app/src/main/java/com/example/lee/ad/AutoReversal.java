package com.example.lee.ad;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Lee on 2016/3/4.
 */
public class AutoReversal implements Runnable {
    Card c;
    Card[] cs;

    public AutoReversal(Card c, Card[] cs) {
        this.c = c;
        this.cs = cs;
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int[] num = (int[]) msg.obj;
            for (int i = 0; i < 2; i++) {
                if (num[i] == 0) {
                } else {
                    cs[num[i]].setBackgroundResource(NumbToCard.cards[cs[num[i]].getCardNumb()]);
                }
            }
        }
    };

    @Override
    public void run() {
        int num[] = getCardNumb();
        Message message = new Message();
        message.obj = num;
        handler.sendMessage(message);

    }

    private int[] getCardNumb() {
        int[] i = new int[2];
        int cardNumb = c.getNumb();

        if (cardNumb >= 19 && cardNumb <= 28) {
            if (cardNumb == 19 && Onclicked.flag[20] == true) {
                i[0] = 10;
            } else if (cardNumb == 28 && Onclicked.flag[27] == true) {
                i[0] = 18;
            } else if (cardNumb > 19 && cardNumb < 28) {
                if (Onclicked.flag[cardNumb - 1] == false && Onclicked.flag[cardNumb + 1] == false) {
                } else if (Onclicked.flag[cardNumb - 1] == true && Onclicked.flag[cardNumb + 1] == false) {
                    i[0] = cardNumb - 10;
                } else if (Onclicked.flag[cardNumb + 1] == true && Onclicked.flag[cardNumb - 1] == false) {
                    i[0] = cardNumb - 9;
                } else {
                    i[0] = cardNumb - 10;
                    i[1] = cardNumb - 9;
                }
            }
        } else if (cardNumb == 4 && Onclicked.flag[5] == true) {
            i[0] = 1;
        } else if (cardNumb == 5 && Onclicked.flag[4] == true) {
            i[0] = 1;
        } else if (cardNumb == 6 && Onclicked.flag[7] == true) {
            i[0] = 2;
        } else if (cardNumb == 7 && Onclicked.flag[6] == true) {
            i[0] = 2;
        } else if (cardNumb == 8 && Onclicked.flag[9] == true) {
            i[0] = 3;
        } else if (cardNumb == 9 && Onclicked.flag[8] == true) {
            i[0] = 3;
        } else if (cardNumb == 10 && Onclicked.flag[11] == true) {
            i[0] = 4;
        } else if (cardNumb == 11) {
            if (Onclicked.flag[10] == false && Onclicked.flag[12] == false) {
            } else if (Onclicked.flag[10] == true && Onclicked.flag[12] == false) {
                i[0] = 4;
            } else if (Onclicked.flag[10] == false && Onclicked.flag[12] == true) {
                i[0] = 5;
            } else {
                i[0] = 4;
                i[1] = 5;
            }
        } else if (cardNumb == 12 && Onclicked.flag[11] == true) {
            i[0] = 5;
        } else if (cardNumb == 13 && Onclicked.flag[14] == true) {
            i[0] = 6;
        } else if (cardNumb == 14) {
            if (Onclicked.flag[13] == false && Onclicked.flag[15] == false) ;
            else if (Onclicked.flag[13] == true && Onclicked.flag[15] == false) {
                i[0] = 6;
            } else if (Onclicked.flag[13] == false && Onclicked.flag[15] == true) {
                i[0] = 7;
            } else {
                i[0] = 6;
                i[1] = 7;
            }
        } else if (cardNumb == 15 && Onclicked.flag[14] == true) {
            i[0] = 7;
        } else if (cardNumb == 16 && Onclicked.flag[17] == true) {
            i[0] = 8;
        } else if (cardNumb == 17) {
            if (Onclicked.flag[16] == false && Onclicked.flag[18] == false) {
            } else if (Onclicked.flag[16] == true && Onclicked.flag[18] == false) {
                i[0] = 8;
            } else if (Onclicked.flag[16] == false && Onclicked.flag[18] == true) {
                i[0] = 9;
            } else {
                i[0] = 8;
                i[1] = 9;
            }
        } else if (cardNumb == 18 && Onclicked.flag[17] == true) {
            i[0] = 9;
        }
        return i;
    }
}
