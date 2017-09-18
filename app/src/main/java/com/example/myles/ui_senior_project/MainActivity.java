package com.example.myles.ui_senior_project;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button homeButton, cameraButton, closetButton, outfitsButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button References
        homeButton = (Button) findViewById(R.id.home_Button);
        cameraButton = (Button)findViewById(R.id.camera_Button);
        closetButton = (Button) findViewById(R.id.closet_Button);
        outfitsButton = (Button) findViewById(R.id.outfits_Button);

        //On Click listeners
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading Fragment
                loadFragment(new Home());
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading Fragment
                Intent nScreen = new Intent(MainActivity.this, Camera.class);
                startActivity(nScreen);
            }
        });

        closetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading Fragment
                loadFragment(new Closet());
            }
        });

        outfitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading Fragment
                loadFragment(new Outfits());
            }
        });
    }

    private void loadFragment(Fragment fragment){
        //Create Fragment Manager
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuFragment, fragment);
        fragmentTransaction.commit();
    }
}

