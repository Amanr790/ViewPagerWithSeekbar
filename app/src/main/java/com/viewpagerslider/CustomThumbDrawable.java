package com.viewpagerslider;

/**
 * Created by admin1 on 2/1/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * seekbar background with text on it.
 *
 */
public class CustomThumbDrawable extends Drawable {
    /**
     * paints.
     */
    private Paint circlePaint;
    private float mRadius;
    Bitmap bitmap;

    public CustomThumbDrawable(int color, int thumbRadius, Drawable drawable) {
        mRadius = thumbRadius;
        bitmap=Utils.getBitmapFromDrawable(drawable);
        setColor(color, bitmap);
    }






    public void setColor(int color, Bitmap bitmap) {
        BitmapShader mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapShader.setLocalMatrix(new Matrix());
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor((0xA0 << 24) + (color & 0x00FFFFFF));
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

//        circlePaint = new Paint();
//        circlePaint.setShader(mBitmapShader);

        invalidateSelf();
    }


    public float getRadius() {
        return mRadius;
    }

    @Override
    protected final boolean onStateChange(int[] state) {
        invalidateSelf();
        return false;
    }

    @Override
    public final boolean isStateful() {
        return true;
    }

    @Override
    public final void draw(Canvas canvas) {
        int height = this.getBounds().centerY()/2;
        int width = this.getBounds().centerX();
        canvas.drawBitmap(bitmap,width,height,circlePaint);
       // canvas.drawCircle(width + mRadius, height, mRadius, circlePaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (mRadius * 2);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (mRadius * 2);
    }

    @Override
    public final int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }
}
