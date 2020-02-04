package com.example.customviewgroupdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

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
        ViewGroup relativeLayout = new MyRelativeLayout(this, width, height);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        addContentView(relativeLayout, params);
    }
}
