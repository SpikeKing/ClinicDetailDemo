package org.wangchenlong.clinicdetaildemo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 比例控件
 * <p>
 * Created by wangchenlong on 16/8/3.
 */
public class RateBarView extends View {

    private float mRate = 1f;

    private int mColorBkg = 0xffeae9e9;

    private int mColorSrc = 0xff76d139;

    private RectF mDrawingRect;

    private float mRadius;

    private Paint mPaint;

    public RateBarView(Context context) {
        this(context, null);
    }

    public RateBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RateBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        allocAndInit(context);
    }

    private void allocAndInit(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mDrawingRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mColorBkg);
        mDrawingRect.set(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(mDrawingRect, mRadius, mRadius, mPaint);

        mPaint.setColor(mColorSrc);
        mDrawingRect.set(0, 0, getWidth() * mRate, getHeight());
        canvas.drawRoundRect(mDrawingRect, mRadius, mRadius, mPaint);
    }

    /**
     * 设置百分比，0~1之间的浮点值
     *
     * @param rate 进度条的百分比数
     */
    public void setRate(float rate) {
        mRate = rate;
    }

    public void setColorBkg(int colorBkg) {
        mColorBkg = colorBkg;
    }

    public void setColorSrc(int colorSrc) {
        mColorSrc = colorSrc;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }
}

