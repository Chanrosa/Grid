package com.example.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Screen extends View {

    private final Paint paint;

    public Screen(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(6f);
    }

    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        int h = getHeight();
        int w = getWidth();
        int grid_size = 5;
        //paint.setStrokeWidth(w *0.1f);
        float grid_w = w * 0.7f;
        float grid_h = w * 0.7f;
        float cell_w = grid_w / grid_size;
        float cell_h = grid_h / grid_size;

        float x = (w - grid_w) /2;
        float y = (h - grid_h) /2;

        for (int i = 0; i <= grid_size; i++){
            float vertical = x + (i * cell_w);
            float horizontal = y + (i * cell_h);
            c.drawLine(vertical, y, vertical, y + grid_h, paint);
            c.drawLine(x, horizontal, x + grid_w, horizontal, paint);
        }

    }
}
