package com.example.babyquiz.firstStep;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.babyquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    ImageView select_iv1;
    ImageView select_iv2;
    Handler handler = new Handler();
    Runnable runnable;
    VideoView videoView;
    String path;
    RelativeLayout container;
    Button replatAgain;
    int[]ballVideo={R.raw.startballvedio,R.raw.success_touch_ball,R.raw.faild_touch_ball};
    int[]appaleVideo={R.raw.selectappal1,R.raw.selectappal2,R.raw.selectappal3};
    int[]banaappaVideo={R.raw.bana_app1,R.raw.bana_app2,R.raw.bana_app3};
    int [] images={R.drawable.ball,R.drawable.appal2,R.drawable.banana};
    int[][] allVideos= new int[][]{ballVideo,appaleVideo,banaappaVideo};
    int activityPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.ballImage);
        select_iv1 = findViewById(R.id.selectImage1);
        select_iv2 = findViewById(R.id.selectImage2);
        container = findViewById(R.id.container);
        videoView = (VideoView) findViewById(R.id.VideoView);
        replatAgain = findViewById(R.id.reply);
        activityPosition=getIntent().getIntExtra("actvty", 1);
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
        path = "android.resource://" + getPackageName() + "/" + allVideos[activityPosition][0];

        playVideo(path, true);

        switch (activityPosition){
            case 0: {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(images[activityPosition]);

            }
            break;
            case 1: {
                select_iv1.setVisibility(View.VISIBLE);
                select_iv1.setImageResource(images[activityPosition]);

            }
                break;
            case 2: {
                select_iv1.setVisibility(View.VISIBLE);
                select_iv2.setVisibility(View.VISIBLE);
                select_iv1.setImageResource(R.drawable.appal2);
                select_iv2.setImageResource(R.drawable.banana);

            }
            break;


        }

        runnable = new Runnable() {
            @Override
            public void run() {
                positionImage();
                handler.postDelayed(runnable, 2200);

            }
        };
        if (activityPosition==0){
            handler.post(runnable);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + allVideos[activityPosition][1];

                handler.removeCallbacks(runnable);

                playVideo(path, true);
            }
        });
        select_iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + allVideos[activityPosition][1];

                handler.removeCallbacks(runnable);

                select_iv2.setVisibility(View.GONE);
                playVideo(path, true);
            }
        });
       /* select_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + allVideos[activityPosition][1];

                handler.removeCallbacks(runnable);

                playVideo(path, true);
            }
        });*/

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + allVideos[activityPosition][2];
                if (activityPosition==0) {
                    handler.removeCallbacks(runnable);
                    handler.post(runnable);
                }
                select_iv2.setVisibility(View.GONE);
                select_iv1.setVisibility(View.GONE);


                playVideo(path, false);
            }
        });
        /*replatAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private static final String TAG = "MainActivity";


    public void playVideo(String path, boolean isSuccess) {
        videoView.setVisibility(View.VISIBLE);
//        container.setVisibility(View.INVISIBLE);
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (!isSuccess) {
                    if (activityPosition==2) {
                        select_iv1.setVisibility(View.VISIBLE);
                        select_iv2.setVisibility(View.VISIBLE);
                    }
//                    replatAgain.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public void positionImage() {

//        DisplayMetrics metrics = container.getw;
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int DeviceTotalWidth = container.getWidth();
        int DeviceTotalHeight = container.getHeight();

        float randX = getRandomPositionX(DeviceTotalWidth);
        float randY = getRandomPositionY(DeviceTotalHeight);

        Log.d(TAG, "positionImage: totalX: " + DeviceTotalWidth + " totalY: " + DeviceTotalHeight);
        Log.d(TAG, "positionImage: ranX: " + randX + " ranY: " + randY);
        iv.animate()
                .x(randX)
                .y(randY)
                .setDuration(2200)
                .start();
    }

    public float getRandomPositionX(float deviceTotalWidth) {
        Random random = new Random();
        float randX = random.nextInt((int) deviceTotalWidth - (int) getImageSizeinPixels());
        return randX;
    }

    public float getRandomPositionY(float DeviceTotalHeight) {
        Random random = new Random();
        float randY = random.nextInt((int) DeviceTotalHeight - (int) getImageSizeinPixels());
        return randY;
    }

    public float getImageSizeinPixels() {
        // Converts 65 dip into its equivalent px
        float imageSize = 65f;
        float extraPadding = 40f;
        float dip = imageSize + extraPadding;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        return px;
    }
}