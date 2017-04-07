package com.anwesome.ui.leanfilterlist;

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
    private float colorScale = 0f;
    private int color = Color.parseColor("#00BCD4");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public FilterButton(Context context,String category) {
        super(context);
        this.category = category;
    }
    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight()/2;
        canvas.save();
        canvas.translate(w/2,h/2);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(w/30);
        canvas.drawRoundRect(new RectF(-w*0.4f,-h*0.4f,w*0.4f,h*0.4f),0.2f*w,0.2f*h,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.scale(colorScale,colorScale);
        canvas.drawRoundRect(new RectF(-w*0.4f,-h*0.4f,w*0.4f,h*0.4f),0.2f*w,0.2f*h,paint);
        canvas.restore();
        paint.setColor(Color.BLACK);
        canvas.drawText(category,-paint.measureText(category)/2,-paint.getTextSize()/2,paint);
        canvas.restore();
    }
    public void setColorScale(float colorScale) {
        this.colorScale = colorScale;
        postInvalidate();
    }
}
