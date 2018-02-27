package com.finalproj.finley.thyroidtracker;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //ActionBar actionbBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Fragment_ID", 1);
        this.startActivity(intent);
        finish();
        return true;
    }
}

