package com.codinginflow.bigots;

import android.view.MotionEvent;

public class SwipeGestureHelper {
    private static final int MIN_SWIPE_DISTANCE = 300;
    private static final int MIN_VELOCITY = 200;
    private float startX;
    private boolean isScrolling = false;

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                isScrolling = false;
                return true;

            case MotionEvent.ACTION_MOVE:
                float deltaX = Math.abs(event.getX() - startX);
                if (deltaX > 30) { // Küçük bir eşik değeri
                    isScrolling = true;
                }
                return true;

            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                deltaX = endX - startX;

                if (Math.abs(deltaX) > MIN_SWIPE_DISTANCE) {
                    if (deltaX > 0) {
                        return onSwipeRight();
                    } else {
                        return onSwipeLeft();
                    }
                }
                isScrolling = false;
                return false;
        }
        return false;
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    protected boolean onSwipeLeft() {
        return false;
    }

    protected boolean onSwipeRight() {
        return false;
    }
}