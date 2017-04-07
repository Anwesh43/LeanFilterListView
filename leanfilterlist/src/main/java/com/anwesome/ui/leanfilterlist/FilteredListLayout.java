package com.anwesome.ui.leanfilterlist;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class FilteredListLayout extends ViewGroup{
    private HorizontalScrollView horizontalScrollView;
    private ScrollView scrollView;
    private int w,h;
    public void onMeasure(int wspec,int hspec) {
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
        }
        setMeasuredDimension(w,h);
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {

        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            if(child instanceof HorizontalScrollView) {
                child.layout(0,h/10,w,h/10+child.getMeasuredHeight());
            }
            else if(child instanceof  ScrollView) {
                child.layout(0,3*h/10,w,3*h/10+child.getMeasuredHeight());
            }

        }
    }
    public FilteredListLayout(Context context) {
        super(context);
        initViews();
    }
    public FilteredListLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        initViews();
    }
    public void initViews() {
        horizontalScrollView = new HorizontalScrollView(getContext());
        scrollView = new ScrollView(getContext());
        addView(horizontalScrollView,new LayoutParams(w,h/10));
        addView(scrollView,new LayoutParams(w,3*h/5));
        DisplayManager displayManager = (DisplayManager)getContext().getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;


        }
    }
}
