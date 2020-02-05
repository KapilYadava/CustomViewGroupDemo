package com.example.customviewgroupdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private OrientationEventListener orientationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
//        Display display = getWindowManager().getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        int width = point.x;
//        int height = point.y;
//        int width = getWindow().getDecorView().getWidth();
//        int height = getWindow().getDecorView().getHeight();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup relativeLayout = new MyRelativeLayout(this, width, height, true);
        //relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        addContentView(relativeLayout, params);
        orientationListener = new MyRelativeLayout.OrientationListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        orientationListener.enable();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orientationListener.disable();
    }

}
