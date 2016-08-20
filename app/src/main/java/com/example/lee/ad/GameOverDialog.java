package com.example.lee.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lee on 2016/1/23.
 */
public class GameOverDialog extends Activity{
    int scoreNumb;
    String name;
    static int numb;
    SharedPreferences sp;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity= (Activity) msg.obj;
            MediaPlayer mp;
            switch (msg.what){

                case 0:
                    mp=MediaPlayer.create(activity,R.raw.succeed1);
                    mp.start();
                    LinearLayout layout=(LinearLayout)activity.getLayoutInflater().inflate(R.layout.input_name, null);
                    TextView tvScore= (TextView) layout.findViewById(R.id.tvScore);
                    tvScore.setText("当前得分:" + scoreNumb + "分");
                    final EditText etInput= (EditText) layout.findViewById(R.id.etInput);
                    AlertDialog alertDialog=new AlertDialog.Builder(activity)
                            .setView(layout)
                            .setTitle("恭喜你！刷新纪录")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (etInput.getText().toString().equals("")) {
                                        name = "匿名";
                                    } else {
                                        name = etInput.getText().toString();
                                    }
                                    System.out.println(name);
                                    sp = activity.getSharedPreferences("scores", MODE_PRIVATE);
                                    SharedPreferences.Editor e = sp.edit();
                                    e.putString("names", name);
                                    e.commit();

                                    activity.recreate();//重新开始游戏
                                    RelativeView.count = 0;
                                    RelativeView.score = 0;
                                    Score.num = 1;
                                    MainActivity.setScore(0, 24);
                                    Onclicked.flag = new boolean[29];//初始化
                                }
                            }).create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                    break;
                case 1:
                    mp=MediaPlayer.create(activity,R.raw.succeed);
                    mp.start();
                    AlertDialog alertDialog1=new AlertDialog.Builder(activity)
                            .setTitle("恭喜你，通关成功！")
                            .setMessage("得分:" + numb +"分"+ '\n'+ '\n' + "最佳:" + scoreNumb + "分"+ '\n'+"记录保持者:" + name )
                            .setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.recreate();//重新开始游戏
                                    RelativeView.count = 0;
                                    RelativeView.score = 0;
                                    Score.num = 1;
                                    MainActivity.setScore(0, 24);
                                    Onclicked.flag = new boolean[29];//初始化
                                }
                            })
                            .create();
                    alertDialog1.setCanceledOnTouchOutside(false);
                    alertDialog1.show();
                    break;
                case 2:
                    mp=MediaPlayer.create(activity,R.raw.gameover);
                    mp.start();
                    AlertDialog alertDialog2=new AlertDialog.Builder(activity)
                            .setTitle("很遗憾，通关失败！")
                            .setMessage("得分:" + numb +"分"+ '\n'+ '\n' + "最佳:" + scoreNumb + "分"+ '\n'+"记录保持者:" + name )
                            .setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.recreate();//重新开始游戏
                                    RelativeView.count = 0;
                                    RelativeView.score = 0;
                                    Score.num = 1;
                                    MainActivity.setScore(0, 24);
                                    Onclicked.flag = new boolean[29];//初始化
                                }
                            })
                            .create();
                    alertDialog2.setCanceledOnTouchOutside(false);
                    alertDialog2.show();
                    break;
            }

        }
    };

    public void Dialog(final Activity activity,int numb,int flag){
        GameOverDialog.numb=numb;
        sp=activity.getSharedPreferences("scores", MODE_PRIVATE);
        scoreNumb=sp.getInt("ScoreNumb",0);
        if(scoreNumb<numb){

            Message msg=new Message();
            msg.obj=activity;
            msg.what=0;
            handler.sendMessage(msg);

            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("ScoreNumb",numb);
            editor.commit();
            scoreNumb=numb;
        }else{
            name=sp.getString("names","匿名");
            Message msg=new Message();
            msg.obj=activity;
            if(flag==1){
                msg.what=1;
            }else if(flag==2){
                msg.what=2;
            }
            handler.sendMessage(msg);
        }
    }
}
