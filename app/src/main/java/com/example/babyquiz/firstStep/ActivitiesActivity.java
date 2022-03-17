package com.example.babyquiz.firstStep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.babyquiz.R;

public class ActivitiesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ActivitiesAdapter activitiesAdapter;
    int lvl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        recyclerView = findViewById(R.id.activity_recycler);
        recyclerView.setHasFixedSize(true);
        activitiesAdapter = new ActivitiesAdapter();
        recyclerView.setAdapter(activitiesAdapter);
        lvl = getIntent().getIntExtra("lvl", 1);
        activitiesAdapter.setOnItemClickListener(position ->
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("lvl", lvl).putExtra("actvty", position)));
    }
}