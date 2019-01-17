package com.examples.bugsmasher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TitleActivity extends Activity {
    Bitmap bmp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        bmp = null;
    }

    public class MyView extends View {
        boolean switchToGameScreen;
        public MyView(Context context) {
            super(context);
            switchToGameScreen = false;
        }
        @Override
        protected void onDraw (Canvas canvas) {
            // Load image if needed
            if (bmp == null)
                bmp = BitmapFactory.decodeResource (getResources(), R.drawable.titlescreen);
            // Draw the title full screen
            Rect dstRect = new Rect();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(bmp, null, dstRect, null);
            // On click switch to main (game) activity
            if (switchToGameScreen) {
                switchToGameScreen = false;
                startActivity(new Intent(TitleActivity.this, MainActivity.class));
                // Delete image (don't need it in memory if not using it)
                bmp = null;
            }
            else
                invalidate();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            // On click set flag to switch to main (game) activity
            if (event.getAction() == MotionEvent.ACTION_UP)
                switchToGameScreen = true;
            return true; // to indicate we have handled this event
        }
    }
}
