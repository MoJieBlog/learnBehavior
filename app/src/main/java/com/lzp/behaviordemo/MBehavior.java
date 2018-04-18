package com.lzp.behaviordemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Li Xiaopeng on 18/4/18.
 */

public class MBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "MBehavior";
    private Context context;
    private int originW = 0;
    private View childView;

    public MBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float scaleY = Math.abs(dependency.getY()) / dependency.getHeight();
        child.setTranslationY(dependency.getY());
        if (childView==null) childView = child;
        if (originW==0)originW = child.getWidth();
        if (childView!=null&&originW!=0)doAnimation(scaleY);
        return true;
    }

    private void doAnimation(float scaleY) {
        int width = (int) (originW * (1-scaleY));
        ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
        layoutParams.height = childView.getHeight();
        layoutParams.width = width>childView.getHeight()?width:childView.getHeight();
        childView.setLayoutParams(layoutParams);
    }

}
