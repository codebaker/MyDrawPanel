package com.codebakery.joan.mydrawpanel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class SingleTouchView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private int paintColor = 0xFF000000;

    private Paint canvasPaint;
    private  Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public SingleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(path,paint);
                path.reset();
                break;
                default:return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(path,paint);
    }



    public void setColor(String newColor){
        paintColor = Color.parseColor(newColor);
        paint.setColor(paintColor);
    }


}
