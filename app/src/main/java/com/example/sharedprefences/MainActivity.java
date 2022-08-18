package com.example.sharedprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
getSupportFragmentManager().beginTransaction().add(R.id.main_container,new FragmentA()).commit();
    }
}