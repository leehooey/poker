package com.example.lee.ad;

/**
 * Created by Lee on 2016/1/22.
 */
public class Interface {

    static public int[] getInterface(int cardwidth, int cardheight, int w, int h, int i) {
        int a[] = new int[2];
        int i1 = ((w / 4) - cardwidth / 2) - (cardwidth * 3 / 5);
        int i2 = ((w / 2) - cardwidth / 2);
        int i3 = ((w / 2) + (w / 4) - cardwidth / 2) + (cardwidth * 3 / 5);
        int i4 = i1 - (cardwidth / 2 + 5);
        int i5 = i1 + (cardwidth / 2 + 5);
        int i6 = i2 - (cardwidth / 2 + 5);
        int i7 = i2 + (cardwidth / 2 + 5);
        int i8 = i3 - (cardwidth / 2 + 5);
        int i9 = i3 + (cardwidth / 2 + 5);
        int i15 = i2 + (cardwidth / 2 + 5) * 2;
        if (i >= 1 && i <= 3) {
            a[1] = 5;
            if (i == 1) a[0] = i1;
            if (i == 2) a[0] = i2;
            if (i == 3) a[0] = i3;
        } else if (i >= 4 && i <= 9) {
            a[1] = cardheight * 3 / 5;
            if (i == 4) a[0] = i4;
            if (i == 5) a[0] = i5;
            if (i == 6) a[0] = i6;
            if (i == 7) a[0] = i7;
            if (i == 8) a[0] = i8;
            if (i == 9) a[0] = i9;
        } else if (i > 9 && i <= 18) {
            a[1] = cardheight * 6 / 5;
            if (i < 15) {
                a[0] = i15 - ((w - 10 * cardwidth) / 11 + cardwidth) * (15 - i);
            } else if (i == 15) {
                a[0] = i15;
            } else {
                a[0] = i15 + ((w - 10 * cardwidth) / 11 + cardwidth) * (i - 15);
            }
        }
        if (i > 18 && i <= 28) {
            a[1] = cardheight * 9 / 5;
            a[0] = cardwidth * (i - 19) + (w - 10 * cardwidth) / 11 * (i - 18);
        }
        if (i >= 29) {
            a[1] = h - cardheight - 20;
            if (i == 29) a[0] = (cardwidth + 10) * 3;
            if (i == 30) a[0] = i2;
            if (i == 31) {
                a[0] = cardwidth * (28 - 19) + (w - 10 * cardwidth) / 11 * (28 - 18);
                a[1] = h - cardheight - 20+cardwidth;
            }
        }
        return a;
    }
}
