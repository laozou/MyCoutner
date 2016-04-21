package test3.mycoutner;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;


/**
 * Created by Administrator on 2016/1/24.
 */
public class TestCoutnerWheel extends ProgressBar {
    private Paint mPaint;
    private Rect mRect;
    private int mBarUnreachColor;
    private int mBarReachColor;
    private float mBarUnreachHeight;
    private float mBarReachHeight;
    private float mTextSize;
    private int mTextColor;
    private float mRadius;

    //TextCoutnerWheel 构造器
    public TestCoutnerWheel(Context context) {
        this(context, null);
    }

    public TestCoutnerWheel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestCoutnerWheel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mRect = new Rect();
        setMax(600);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestCoutnerWheel);

        mBarUnreachColor = typedArray.getColor(R.styleable.TestCoutnerWheel_progress_unreach_color, Color.WHITE);
        mBarReachColor = typedArray.getColor(R.styleable.TestCoutnerWheel_progress_reach_color, Color.BLUE);
        mBarUnreachHeight = typedArray.getDimension(R.styleable.TestCoutnerWheel_progress_unreach_height,11);
        mBarReachHeight = typedArray.getDimension(R.styleable.TestCoutnerWheel_progress_reach_height,11);
        mTextSize = typedArray.getDimension(R.styleable.TestCoutnerWheel_progress_text_size,10);
        mTextColor = typedArray.getColor(R.styleable.TestCoutnerWheel_progress_text_color,Color.WHITE);
        mRadius = typedArray.getDimension(R.styleable.TestCoutnerWheel_radius,100);

        typedArray.recycle();


    }



    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text =String.valueOf(getProgress()*1.0/10);


        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());

        //绘制unreachcircle
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mBarUnreachColor);
        mPaint.setStrokeWidth(mBarUnreachHeight);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);

        //绘制reachcicle
        mPaint.setColor(mBarReachColor);
        mPaint.setStrokeWidth(mBarReachHeight);
        float sweepAngle = getProgress() * 1.0f / getMax()*360;
        RectF rectF=new RectF(getWidth() / 2-mRadius, getHeight() / 2-mRadius, getWidth() / 2+mRadius, getHeight() / 2+mRadius);

        canvas.drawArc(rectF, 270, sweepAngle, false, mPaint);

        //绘制text
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.getTextBounds(text, 0, text.length(), mRect);
        int textWidth = mRect.width();

        canvas.drawText(text, getWidth() / 2 - textWidth, getHeight() / 2, mPaint);


        canvas.restore();

    }
}


