package com.example.customviewgroupdemo;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyRelativeLayout extends ViewGroup {

    final ViewGroup myViewGroup;
    final ViewGroup myViewGroupLogo;
    final int width;
    final int height;
    private static Button button;
    private boolean okButton;

    public MyRelativeLayout(final Context context, final int width, final int height, boolean okButton){
        super(context);
        this.width = width;
        this.height = height;
        this.okButton = okButton;
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
                        textView.layout(0, 0 ,600, 200);
                        textView.setGravity(Gravity.CENTER);
                    }

                    if (getChildAt(i) instanceof ImageView){
                        imageView.layout(0, 0, 100, 100);
                    }
                    if (getChildAt(i) instanceof  Button){
                        button.layout(500, 100, 600, 200);
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

        if (okButton){
            button = new Button(context);
            button.setText("Turn On");
            myViewGroup.addView(button);
        }

        addView(myViewGroup);


        //addView(myViewGroupLogo);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (okButton){
            myViewGroup.layout(100, 500, width-200, height-1300);
        }else{
            myViewGroup.layout(100, 1500, width-200, height-400);
        }
        //myViewGroupLogo.layout(l, t, width-200, height-500);
    }

    static class OrientationListener extends OrientationEventListener {
        final int ROTATION_O    = 1;
        final int ROTATION_90   = 2;
        final int ROTATION_180  = 3;
        final int ROTATION_270  = 4;

        private int rotation = 0;
        private Context context;
        public OrientationListener(Context context) {
            super(context);
            this.context = context;
        }

        @Override public void onOrientationChanged(int orientation) {
            if( (orientation < 35 || orientation > 325) && rotation!= ROTATION_O){ // PORTRAIT
                rotation = ROTATION_O;
                Toast.makeText(context, "ROTATION_O", Toast.LENGTH_LONG).show();
                button.setRotation(0);
            }
            else if( orientation > 145 && orientation < 215 && rotation!=ROTATION_180){ // REVERSE PORTRAIT
                rotation = ROTATION_180;
                //menuButton.startAnimation(toPortAnim);
                Toast.makeText(context, "ROTATION_180", Toast.LENGTH_LONG).show();
                button.setRotation(180);
            }
            else if(orientation > 55 && orientation < 125 && rotation!=ROTATION_270){ // REVERSE LANDSCAPE
                rotation = ROTATION_270;
                button.setRotation(270);
            }
            else if(orientation > 235 && orientation < 305 && rotation!=ROTATION_90){ //LANDSCAPE
                rotation = ROTATION_90;
                button.setRotation(90);
            }
        }
    }
}
