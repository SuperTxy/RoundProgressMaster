package com.example.txy.roundprogressmaster;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by txy on 2016/11/13.
 * 自定义圆形进度条
 */

public class RoundProgress extends View {
    /**
     * 圆环的颜色
     */
    private int ringColor;
    //    圆环进度的颜色
    private int ringProgressColor;
    //    圆环的宽度
    private int ringWidth;
    //    字体大小
    private int textSize;
    //    字体颜色
    private int textColor ;
    //    画笔
    private Paint paint;
    //    得到控件宽度
    private int width;
    //    最大进度
    private int max;
    //    当前进度
    private int progress;

    /**
     * 此构造方法用于java代码创建对象
     * @param context
     */
    public RoundProgress(Context context) {
        this(context, null);
    }

    /**
     * 此构造方法用于布局文件加载该View，必须有，不然会崩溃
     * @param context
     * @param attrs
     */
    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
//        设置抗锯齿
        paint.setAntiAlias(true);
//        得到所有自定义属性的数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        ringColor = typedArray.getColor(R.styleable.RoundProgress_ringColor,Color.GRAY);
        ringProgressColor = typedArray.getColor(R.styleable.RoundProgress_ringProgressColor, Color.RED);
        ringWidth = (int) typedArray.getDimension(R.styleable.RoundProgress_ringWidth,20);
        textSize = (int) typedArray.getDimension(R.styleable.RoundProgress_textSize,40);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor,Color.RED);
        max = typedArray.getInteger(R.styleable.RoundProgress_max,100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress,60);
//        不要忘记回收
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        圆心坐标和半径
        float circleX = width / 2;
        float circleY = width / 2;
        float radius = width / 2 - ringWidth / 2;
//       1、 绘制圆环
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth);
        paint.setColor(ringColor);
        canvas.drawCircle(circleX, circleY, radius, paint);

//       2、 绘制圆弧
        RectF oval = new RectF(ringWidth / 2, ringWidth / 2, width - ringWidth / 2, width - ringWidth / 2);
        paint.setColor(ringProgressColor);
        canvas.drawArc(oval, 0, progress * 360 / max, false, paint);
//        3、绘制文本
        String text = progress * 100 / max + "%";
        paint.setColor(textColor);
        paint.setTextSize(textSize);
//        注意此处一定要重新设置宽度为0
        paint.setStrokeWidth(0);
//        得到指定文本的边界矩形大小
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, width / 2 - bounds.width() / 2, width / 2 + bounds.height() / 2, paint);
    }
}
