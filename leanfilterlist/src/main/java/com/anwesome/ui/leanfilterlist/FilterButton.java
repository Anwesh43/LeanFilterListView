package com.anwesome.ui.leanfilterlist;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class FilterButton extends View{
    private String category;
    private float colorScale = 0f,dir = 1;
    private int color = Color.parseColor("#AA00BCD4");
    private ColorFillListener colorFillListener = new ColorFillListener();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public FilterButton(Context context,String category) {
        super(context);
        this.category = category;
    }
    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public void onDraw(Canvas canvas) {

        int w = canvas.getWidth(),h = canvas.getHeight();
        paint.setTextSize(h/2);
        float r = Math.max(w,h)/10;
        canvas.save();
        canvas.translate(w/2,h/2);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(h/20);
        canvas.drawRoundRect(new RectF(-w*0.4f,-h*0.4f,w*0.4f,h*0.4f),r,r,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.scale(colorScale,colorScale);
        canvas.drawRoundRect(new RectF(-w*0.4f,-h*0.4f,w*0.4f,h*0.4f),r,r,paint);
        canvas.restore();
        paint.setColor(Color.BLACK);
        canvas.drawText(category,-paint.measureText(category)/2,+paint.getTextSize()/4,paint);
        canvas.restore();
    }
    public void setColorScale(float colorScale) {
        this.colorScale = colorScale;
        postInvalidate();
    }
    public void start() {
        colorFillListener.start();
    }
    private class ColorFillListener implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener {
        public void start() {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
            if(dir == -1) {
                valueAnimator = ValueAnimator.ofFloat(1,0);
            }
            valueAnimator.setDuration(500);
            valueAnimator.addUpdateListener(this);
            valueAnimator.addListener(this);
            valueAnimator.start();
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            setColorScale((float)valueAnimator.getAnimatedValue());
        }
        public void onAnimationEnd(Animator animator) {
            dir*=-1;
        }
        public void onAnimationRepeat(Animator animator) {

        }
        public void onAnimationCancel(Animator animator) {

        }public void onAnimationStart(Animator animator) {

        }

    }
}
