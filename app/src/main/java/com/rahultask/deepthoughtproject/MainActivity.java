package com.rahultask.deepthoughtproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rahultask.deepthoughtproject.fragment.HomeFrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView homeCv = findViewById(R.id.mainHomeCv);
        homeCv.setOnClickListener(view -> {
            if (getSupportFragmentManager().findFragmentById(R.id.mainFragCv) instanceof HomeFrag) {
                Toast.makeText(this, "Already in Home", Toast.LENGTH_SHORT).show();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragCv, new HomeFrag()).commit();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragCv, new HomeFrag()).commit();

    }


}