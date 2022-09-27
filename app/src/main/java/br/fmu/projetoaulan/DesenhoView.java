package br.fmu.projetoaulan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DesenhoView extends View {
    private static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private final Paint paintLine;
    private final Paint paintScreen;
    private final Map<Integer, Path> pathMap = new HashMap<>();
    private final Map<Integer, Point> previousPointMap = new HashMap<>();
    public DesenhoView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        paintScreen = new Paint();
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLUE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(50);
        paintLine.setStrokeCap(Paint.Cap.ROUND);
    }
    @Override
    public void onSizeChanged(int w, int h, int OldW, int OldH) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0,paintScreen);
        for(Integer key: pathMap.keySet()) {
            canvas.drawPath(pathMap.get(key), paintLine);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();
        if( action==MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_POINTER_DOWN) {
            touchStarted(event.getX(actionIndex), event.getY(actionIndex),
                    event.getPointerId(actionIndex));
        } else if( action == MotionEvent.ACTION_POINTER_UP || action==MotionEvent.ACTION_UP ) {
            touchEnded(event.getPointerId(actionIndex));
        } else {
            touchMoved(event);
        }
        invalidate();
        return true;
    }
    private void touchStarted(float x, float y, int lineId) {
        Path path;
        Point point;
        if ( pathMap.containsKey(lineId)) {
            path = pathMap.get(lineId);
            path.reset();
            point = previousPointMap.get(lineId);
        } else {
            path = new Path();
            pathMap.put(lineId, path);
            point = new Point();
            previousPointMap.put(lineId,point);
        }
        path.moveTo(x,y);
        point.x = (int)x;
        point.y = (int)y;
    }
    private void touchMoved(MotionEvent event) {
        int i = 0;
        int pointerId = event.getPointerId(i);
        int pointerIndex = event.findPointerIndex(pointerId);
        if (pathMap.containsKey(pointerId)) {
            float newX = event.getX(pointerIndex);
            float newY = event.getY(pointerIndex);
            Path path = pathMap.get(pointerId);
            Point point = previousPointMap.get(pointerId);
            float deltaX = Math.abs(newX-point.x);
            float deltaY = Math.abs(newY-point.y);
            if(deltaX>=TOUCH_TOLERANCE||deltaY>=TOUCH_TOLERANCE) {
                path.quadTo(point.x,point.y,(newX+point.x)/2, (newY+point.y)/2);
                point.x = (int) newX;
                point.y = (int) newY;
            }
        }

    }
    private void touchEnded(int lineId) {
        Path path = pathMap.get(lineId);
        bitmapCanvas.drawPath(path,paintLine);
        path.reset();
    }
}
