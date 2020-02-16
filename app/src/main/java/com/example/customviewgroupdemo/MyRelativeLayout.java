package com.example.customviewgroupdemo;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyRelativeLayout extends RelativeLayout {

    final int width;
    final int height;
    private Button button;
    private static boolean okButton;
    private static View view;

    public MyRelativeLayout(final Context context, final int width, final int height, boolean okButton){
        super(context);
        this.width = width;
        this.height = height;
        this.okButton = okButton;
        view  = LayoutInflater.from(context).inflate(R.layout.activity_main, null, false);
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(24, 1400, 24, 0);
        //params.addRule(CENTER_IN_PARENT);
        view.setLayoutParams(params);
        addView(view);

        init();
    }

    public void init(){

        button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.description);
        textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                textView.getViewTreeObserver().removeOnPreDrawListener(this);

                RelativeLayout.LayoutParams params1= (RelativeLayout.LayoutParams) button.getLayoutParams();
                RelativeLayout.LayoutParams params2= (RelativeLayout.LayoutParams) textView.getLayoutParams();
                if (textView.getLineCount() > 1){
                    params1.addRule(RelativeLayout.BELOW, R.id.description);
                    params1.setMargins(0, 100, 0, 0);
                    params2.removeRule(RelativeLayout.LEFT_OF);
                    button.setLayoutParams(params1);
                }

                return true;
            }
        });

        if (okButton){
            button.setVisibility(VISIBLE);
            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams)view.getLayoutParams();
            params.addRule(CENTER_IN_PARENT);
        }else{
            button.setVisibility(GONE);
        }

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
                view.setRotation(0);
                if (!okButton){
                    view.setTranslationY(200);
                    view.setTranslationX(0);
                }else{
                    RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams)view.getLayoutParams();
                    params.addRule(CENTER_IN_PARENT);
                }
            }
            else if( orientation > 145 && orientation < 215 && rotation!=ROTATION_180){ // REVERSE PORTRAIT
                rotation = ROTATION_180;
                //menuButton.startAnimation(toPortAnim);
                Toast.makeText(context, "ROTATION_180", Toast.LENGTH_LONG).show();
                //view.setRotation(180);
            }
            else if(orientation > 55 && orientation < 125 && rotation!=ROTATION_270){ // REVERSE LANDSCAPE
                rotation = ROTATION_270;
                view.setRotation(270);
                if (okButton){
                    RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams)view.getLayoutParams();
                    params.addRule(CENTER_IN_PARENT);
                }else {
                    view.setTranslationY(-300);
                    view.setTranslationX(-200);
                }

            }
            else if(orientation > 235 && orientation < 305 && rotation!=ROTATION_90){ //LANDSCAPE
                rotation = ROTATION_90;
                view.setRotation(90);
                if (okButton){
                    RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams)view.getLayoutParams();
                    params.addRule(CENTER_IN_PARENT);
                }else {
                    view.setTranslationY(-300);
                    view.setTranslationX(-200);
                }
            }
        }
    }
}
