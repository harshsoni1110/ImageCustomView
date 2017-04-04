/**
 * Created by HSoni on 3/20/2017.
 */

package harsh.com.imagecustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewThumbnail extends ImageView {

    private float mRadius = 0f;

    public ImageViewThumbnail(Context context) {
        super(context);
    }

    public ImageViewThumbnail(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageViewThumbnail,
                0,0
        );

        try {
            mRadius = a.getFloat(R.styleable.ImageViewThumbnail_circleRadius,18.0f);
            if (mRadius < 0){
                mRadius = 0;
            }
        }
        finally {
            a.recycle();
        }
    }

    public ImageViewThumbnail(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Clipping not supported on ICS and lower devices
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            super.onDraw(canvas);
            return;
        }
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());

        clipPath.addRoundRect(rect, mRadius, mRadius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    public float getmRadius() {
        return mRadius;
    }

    public void setmRadius(float mRadius) {
        this.mRadius = mRadius;
        invalidate();
        requestLayout();
    }
}