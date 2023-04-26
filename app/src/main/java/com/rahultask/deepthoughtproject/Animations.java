package com.rahultask.deepthoughtproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class Animations {
    public void vibrateAnimation(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX",
                -30f, 30f, -20f, 20f, -10f, 10f, 0f, 10f);
        animator.setDuration(1000);

        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public void scaleAnimView(View VIEW_TO_SCALE) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        VIEW_TO_SCALE.startAnimation(scaleAnimation);
    }

}
