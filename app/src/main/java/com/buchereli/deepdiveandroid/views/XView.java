package com.buchereli.deepdiveandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by buche on 8/29/2017.
 */

public class XView extends View {

    final private Paint paint = new Paint();

    public XView(Context context) {
        super(context);
    }

    public XView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(width / 10);
        canvas.drawLine(0, 0, width, height, paint);
        canvas.drawLine(width, 0, 0, height, paint);
    }
}
