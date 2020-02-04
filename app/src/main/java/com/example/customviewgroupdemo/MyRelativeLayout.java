package com.example.customviewgroupdemo;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyRelativeLayout extends ViewGroup {

    final ViewGroup myViewGroup;
    final ViewGroup myViewGroupLogo;
    final int width;
    final int height;

    public MyRelativeLayout(final Context context, final int width, final int height){
        super(context);
        this.width = width;
        this.height = height;
        final TextView textView = new TextView(context);
        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //textView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        //textView.setGravity(Gravity.CENTER);
        textView.setText("Hi This is Kapil");

        final TextView textView1 = new TextView(context);
        textView1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //textView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        //textView.setGravity(Gravity.CENTER);
        textView1.setText("Hi This is Kapil1");

        final ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.ic_launcher_foreground);

        final ImageView imageView1 = new ImageView(context);
        imageView1.setBackgroundResource(R.drawable.ic_launcher_foreground);

        myViewGroup = new ViewGroup(context) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {

                int child = getChildCount();

                for (int i=0; i<child; i++){
                    if (getChildAt(i) instanceof TextView){
                        textView.layout(0, 1280, width, 1350);
                        textView.setGravity(Gravity.CENTER);
                    }

                    if (getChildAt(i) instanceof ImageView){
                        imageView.layout(0, 1200, 200, 1400);
                    }
                }
            }
        };

        myViewGroupLogo = new ViewGroup(context) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {

                int child = getChildCount();

                for (int i=0; i<child; i++){
                    if (getChildAt(i) instanceof TextView){
                        textView1.layout(0, 1380, width, 1450);
                        textView1.setGravity(Gravity.CENTER);
                    }

                    if (getChildAt(i) instanceof ImageView){
                        imageView1.layout(0, 1300, 200, 1500);
                    }
                }
            }
        };

//        myViewGroup.setLayoutParams(new ViewGroup.LayoutParams(width, height));
//        myViewGroup.setTop(100);
//        myViewGroup.setLeft(100);
        myViewGroup.addView(textView);
        myViewGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        myViewGroup.addView(imageView);

        myViewGroupLogo.addView(textView1);
        myViewGroupLogo.addView(imageView1);
        myViewGroupLogo.setBackgroundColor(getResources().getColor(android.R.color.black));
        
        addView(myViewGroup);
        addView(myViewGroupLogo);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        myViewGroup.layout(l, t, width, height-300);
        myViewGroupLogo.layout(l, t, width-200, height-500);
    }
}
