package fmuntenescu.vectordrawablevspng;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ImageView that measures the time took to draw.
 */
public class MeasurableImageView extends ImageView {

    private ViewRedrawnListener mViewRedrawnListener;

    public MeasurableImageView(final Context context) {
        super(context);
    }

    public MeasurableImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasurableImageView(final Context context, final AttributeSet attrs,
                               final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewRedrawnListener(@NonNull final ViewRedrawnListener viewRedrawnListener) {
        mViewRedrawnListener = viewRedrawnListener;
    }

    /**
     * Measure the time that the {@link ImageView#onDraw(Canvas)} method took and then notify
     * the listeners when the re-draw has finished, with the duration of the draw.
     */
    @Override
    protected void onDraw(final Canvas canvas) {
        long startTime = System.nanoTime();

        super.onDraw(canvas);

        long endTime = System.nanoTime();
        notifyDraw(startTime, endTime);
    }

    private void notifyDraw(final double startTimeNano, final double endTimeNano) {
        double durationMiliseconds = (endTimeNano - startTimeNano) / 1000000;

        if (mViewRedrawnListener != null) {
            mViewRedrawnListener.onDrawFinished(durationMiliseconds);
        }
    }
}
