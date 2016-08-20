package com.example.lee.ad;

/**
 * Created by Lee on 2016/1/20.
 */
public class Onclicked {
    static boolean[] flag = new boolean[29];

    static public boolean CanOnclick(int numb, int n, int nb) {
        if (numb <= 3 && flag[numb * 2 + 2] == true && flag[numb * 2 + 3] == true) {
            return isMatching(numb, n, nb);
        }
        if (numb == 4 || numb == 5) {
            if (flag[numb + 6] == true && flag[numb + 7] == true) {
                return isMatching(numb, n, nb);
            }
        }
        if (numb == 6 || numb == 7) {
            if (flag[numb + 7] == true && flag[numb + 8] == true) {
                return isMatching(numb, n, nb);
            }
        }
        if (numb == 8 || numb == 9) {
            if (flag[numb + 8] == true && flag[numb + 9] == true) {
                return isMatching(numb, n, nb);
            }
        }
        if (numb >= 10 && numb <= 18 && flag[numb + 9] == true && flag[numb + 10] == true) {
            return isMatching(numb, n, nb);
        }
        if (numb >= 19 && numb <= 28) {
            return isMatching(numb, n, nb);
        }
        return false;
    }

    static boolean isMatching(int n, int numb, int nb) {
        if (
                nb == numb + 1 && nb != 14 && nb != 27 && nb != 40 || nb == numb - 1 && nb != 13 && nb != 26 && nb != 39 ||
                        nb == numb + 12 && nb != 13 && nb != 26 && nb != 39 && nb != 52 || nb == numb - 12 && nb != 1 && nb != 14 && nb != 27 && nb != 40 ||
                        nb == numb + 14 && nb != 27 && nb != 40 || nb == numb - 14 && nb != 13 && nb != 26 ||
                        nb == numb + 25 && nb != 26 && nb != 39 && nb != 52 || nb == numb - 25 && nb != 1 && nb != 14 && nb != 27 ||
                        nb == numb + 27 && nb != 40 || nb == numb - 27 && nb != 13 ||
                        nb == numb + 38 && nb != 39 && nb != 52 || nb == numb - 38 && nb != 1 && nb != 14 ||
                        nb == numb + 40 || nb == numb - 40) {
            flag[n] = true;

            return true;
        }
        return false;
    }
}
