package com.example.babyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.babyquiz.firstStep.ActivitiesActivity;

public class ChooseLevelActivity extends AppCompatActivity implements View.OnClickListener {

    Button lvl1;
    Button lvl2;
    Button lvl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        lvl1 = findViewById(R.id.lvl1);
        lvl1.setOnClickListener(this);
        lvl2 = findViewById(R.id.lvl2);
        lvl2.setOnClickListener(this);
        lvl3 = findViewById(R.id.lvl3);
        lvl3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int lvl;
        if (view == lvl1) lvl = 1;
        else if (view == lvl2) lvl = 2;
        else lvl = 3;
        startActivity(new Intent(this, ActivitiesActivity.class).putExtra("lvl", lvl));
    }
}