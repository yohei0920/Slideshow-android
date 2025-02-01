package com.example.slideshow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageSwitcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageSwitcher mImageSwitcher;
    private int mPosition = 0;

    int[] mImageResources = {
            R.drawable.slide00,
            R.drawable.slide01,
            R.drawable.slide02,
            R.drawable.slide03,
            R.drawable.slide04,
            R.drawable.slide05,
            R.drawable.slide06,
            R.drawable.slide07,
            R.drawable.slide08,
            R.drawable.slide09,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setInAnimation(MainActivity.this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(MainActivity.this, android.R.anim.slide_out_right);

        mImageSwitcher.setFactory(new ImageSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return (ImageView) new ImageView(MainActivity.this);
            }
        });
        mImageSwitcher.setImageResource(R.drawable.slide00);

        Button prevButton = (Button) findViewById(R.id.prev_btn);
        Button nextButton = (Button) findViewById(R.id.next_btn);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveImagePosition(-1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveImagePosition(1);
            }
        });
    }

    private void moveImagePosition(int move) {
        mPosition = mPosition + move;
        if(mPosition >= mImageResources.length) {
            mPosition = 0;
        } else if(mPosition < 0) {
            mPosition = mImageResources.length - 1;
        }
        mImageSwitcher.setImageResource(mImageResources[mPosition]);
    };
}