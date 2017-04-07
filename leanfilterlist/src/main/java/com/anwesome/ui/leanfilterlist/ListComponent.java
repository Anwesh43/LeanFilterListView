package com.anwesome.ui.leanfilterlist;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by anweshmishra on 07/04/17.
 */
public class ListComponent extends View {
    private List<String> categories = new ArrayList<>();
    private Bitmap bitmap;
    private String title,subTitle;
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public ListComponent(Context context,Bitmap bitmap, String title, String subTitle) {
        super(context);
        this.title = title;
        this.bitmap = bitmap;
        this.subTitle = subTitle;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        canvas.drawColor(Color.WHITE);
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w/10,w/10,true);
        }
        canvas.save();
        Path path = new Path();
        path.addCircle(w/5,h/2,w/20, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,w/5-w/20,h/2-w/20,paint);
        canvas.restore();
        paint.setTextSize(h/5);
        canvas.drawText(getMeasuredText(w,title),w/3,h/3-paint.getTextSize()/2,paint);
        paint.setTextSize(h/8);
        canvas.drawText(getMeasuredText(w,title),w/3,2*h/3-paint.getTextSize()/2,paint);
        time++;
    }
    private String getMeasuredText(int w,String text) {
        String measuredText = "";
        for(int i=0;i<text.length();i++) {
            if(paint.measureText(measuredText+text.charAt(i))<3*w/4-w/20) {
                measuredText += text.charAt(i);
            }
            else {
                measuredText+="...";
                break;
            }

        }
        return measuredText;
    }
    public void matchCategory(String newCategory) {
        boolean matched = false;
        for(String category:categories) {
            if(category.equals(newCategory)) {
                matched = true;
                break;
            }
        }
        if(matched) {
            setVisibility(VISIBLE);
        }
        else {
            setVisibility(INVISIBLE);
        }
    }
}
