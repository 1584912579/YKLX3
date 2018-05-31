package com.example.asus.yklx3.ui.MediaPlayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.asus.yklx3.R;
import com.example.asus.yklx3.ui.shopping.ShopActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPlayerActivity extends AppCompatActivity {

    @BindView(R.id.gramophone_view)
    GramophoneView mGramophoneView;
    @BindView(R.id.btnPlayUrl)
    Button mBtnPlayUrl;
    @BindView(R.id.btnPause)
    Button mBtnPause;
    @BindView(R.id.btnStop)
    Button mBtnStop;
    @BindView(R.id.tt)
    Button tt;
    @BindView(R.id.skbProgress)
    SeekBar mSkbProgress;
    private String path= "http://sc1.111ttt.cn:8282/2018/1/03m/13/396131229550.m4a?tflag=1519095601&pin=6cd414115fdb9a950d827487b16b5f97#.mp3";
    private MediaPlayer mediaPlayer;
    private boolean flag=false;
    private Handler handler =new Handler(){
        //收到Handler发回的消息被回调
        public void handleMessage(Message msg) {
            //更新UI组件
            if(msg.what==2){
//                int current=mSkbProgress.getMax()
//                        * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
                 int current=mediaPlayer.getCurrentPosition();
                mSkbProgress.setProgress(current);
                handler.sendEmptyMessageDelayed(2,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        ButterKnife.bind(this);
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            //循环播放
            mediaPlayer.setLooping(flag);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    //加载下一首
                    //Toast.makeText(MainActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                }
            });
            mSkbProgress.setMax(mediaPlayer.getDuration());
            mSkbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser){
                        mediaPlayer.seekTo(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBtnPlayUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mGramophoneView.setPlaying(true);
                handler.sendEmptyMessageDelayed(2,1000);


            }
        });
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                mGramophoneView.setPlaying(false);
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mediaPlayer.stop();
                flag=!flag;
//                mGramophoneView.setPlaying(false);
            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaPlayerActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer=null;//回收资源
        }
        super.onDestroy();
    }

}
