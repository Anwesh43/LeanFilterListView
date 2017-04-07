package com.anwesome.ui.leanfilterlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
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
    private HorizontalButtonLayout filterButtonLayout;
    private VerticalListView listComponentLayout;
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
        int viewW = (int)(paint.measureText(category)*2);
        final FilterButton filterButton = new FilterButton(getContext(),category);
        filterButton.setPaint(paint);
        filterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                categories.add(category);
                filterButton.start();
            }
        });
        filterButtonLayout.addView(filterButton,new LayoutParams(viewW,h/20));
        filterButtonLayout.requestLayout();
        horizontalScrollView.requestLayout();
    }
    public void addListComponent(Bitmap bitmap,String title,String subtTitle,List<String> categories) {
        ListComponent listComponent = new ListComponent(getContext(),bitmap,title,subtTitle);
        listComponent.setCategories(categories);
        listComponentLayout.addView(listComponent,new LayoutParams(9*w/10,h/8));
        listComponentLayout.requestLayout();
        scrollView.requestLayout();
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
        setBackgroundColor(Color.parseColor("#ECEFF1"));
        filterButtonLayout = new HorizontalButtonLayout(getContext());
        listComponentLayout = new VerticalListView(getContext());

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
        paint.setTextSize(h/30);
        horizontalScrollView.addView(filterButtonLayout,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        scrollView.addView(listComponentLayout,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        requestLayout();
        horizontalScrollView.requestLayout();
        scrollView.requestLayout();
    }
    private class HorizontalButtonLayout extends ViewGroup {
        public HorizontalButtonLayout(Context context) {
            super(context);
        }
        public void onMeasure(int wspec,int hspec) {
            int w = 0;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                w+=(child.getMeasuredWidth()*6)/5;
            }
            setMeasuredDimension(w,h/20);
        }
        public void onLayout(boolean reloaded,int a,int b,int w,int h) {
            int x = 0;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(x,0,x+child.getMeasuredWidth(),child.getMeasuredHeight());
                x+=(child.getMeasuredWidth()*6)/5;
            }
        }
    }
    private class VerticalListView extends ViewGroup {
        public VerticalListView(Context context) {
            super(context);
        }

        public void onMeasure(int wspec, int hspec) {
            int h = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                measureChild(child, wspec, hspec);
                h += (child.getMeasuredHeight() * 6) / 5;
            }
            setMeasuredDimension(w, h);
        }

        public void onLayout(boolean reloaded, int a, int b, int w, int h) {
            int y = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.layout(w/20, y, w/20+child.getMeasuredWidth(), y + child.getMeasuredHeight());
                y += (child.getMeasuredHeight() * 6) / 5;
            }
        }
    }
}
