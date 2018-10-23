package com.codebakery.joan.mydrawpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private  SingleTouchView drawView;
    private ImageButton currentPaint;
    private LinearLayout paintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintLayout = findViewById(R.id.llPaint) ;
        currentPaint = (ImageButton) paintLayout.getChildAt(0);
        drawView = findViewById(R.id.singleTouchView);
    }

    public void onClicked(View view) {
        if(view != currentPaint) {
            String color = view.getTag().toString();
            drawView.setColor(color);
            currentPaint = (ImageButton) view;
        }
    }
}
