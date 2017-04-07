package com.anwesome.ui.leanfilterlist;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class FilteredListLayout extends ViewGroup{
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout filterButtonLayout,listComponentLayout;
    private float fbX = 0,lcY = 0;
    private List<String> categories = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ScrollView scrollView;
    private int w,h;
    public void onMeasure(int wspec,int hspec) {
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
        }
        setMeasuredDimension(w,h);
    }
    public void addFilterButton(final String category) {
        int viewW = (int)(paint.measureText(category)*3)/2;
        final FilterButton filterButton = new FilterButton(getContext(),category);
        filterButton.setPaint(paint);
        filterButton.setX(viewW);
        filterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                categories.add(category);
                filterButton.start();
            }
        });
        filterButtonLayout.addView(filterButton,new LayoutParams(viewW,h/10));
        filterButtonLayout.requestLayout();
        fbX+=(viewW*5)/4;
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
        filterButtonLayout = new RelativeLayout(getContext());
        listComponentLayout = new RelativeLayout(getContext());
        paint.setTextSize(h/15);
        horizontalScrollView = new HorizontalScrollView(getContext());
        scrollView = new ScrollView(getContext());
        addView(horizontalScrollView,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        addView(scrollView,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        DisplayManager displayManager = (DisplayManager)getContext().getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
        horizontalScrollView.addView(filterButtonLayout,new LayoutParams(LayoutParams.WRAP_CONTENT,h/10));
        scrollView.addView(listComponentLayout,new LayoutParams(w,LayoutParams.WRAP_CONTENT));
    }
}
