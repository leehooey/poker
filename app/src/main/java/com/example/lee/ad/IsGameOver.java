package com.example.lee.ad;

/**
 * Created by Lee on 2016/1/23.
 */
public class IsGameOver {
    static public boolean gameOver(){
        for(int i=1;i<Onclicked.flag.length;i++){
            if(Onclicked.flag[i]==false){
                return false;
            }else if(i==Onclicked.flag.length-1){
                return true;
            }
        }
        return false;
    }
}
