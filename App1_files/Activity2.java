package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    Intent intentA2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentA2 = getIntent();
        Toast.makeText(getApplicationContext(),
                "In Activity 2 of App1 ",
                Toast.LENGTH_SHORT).show();


    }
}
