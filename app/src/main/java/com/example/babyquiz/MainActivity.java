package com.example.babyquiz;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    Handler handler = new Handler();
    Runnable runnable;
    VideoView videoView;
    String path;
    LinearLayout container;
    Button replatAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.ballImage);
        container = findViewById(R.id.container);
        videoView = (VideoView) findViewById(R.id.VideoView);
        replatAgain=findViewById(R.id.reply);
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
        path = "android.resource://" + getPackageName() + "/" + R.raw.startballvedio;

        playVideo(path, false);

        runnable = new Runnable() {
            @Override
            public void run() {
                positionImage();
                handler.postDelayed(runnable, 2000);

            }
        };

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.success_touch_ball;

                handler.removeCallbacks(runnable);

                playVideo(path, true);
            }
        });
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.faild_touch_ball;

                handler.removeCallbacks(runnable);

                playVideo(path, false);
            }
        });
        replatAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private static final String TAG = "MainActivity";


    public void playVideo(String path, boolean isSuccess) {
        videoView.setVisibility(View.VISIBLE);
        container.setVisibility(View.INVISIBLE);
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (!isSuccess) {
                    videoView.setVisibility(View.INVISIBLE);
                    container.setVisibility(View.VISIBLE);
                    replatAgain.setVisibility(View.INVISIBLE);
                    handler.post(runnable);
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

        iv.setX(randX);
        iv.setY(randY);

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