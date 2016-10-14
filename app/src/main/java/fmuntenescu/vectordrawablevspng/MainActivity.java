package fmuntenescu.vectordrawablevspng;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Activity that allows choosing between a VectorDrawable and a PNG resource as a source for
 * an ImageView. The Activity displays how long the {@link ImageView#onDraw(Canvas)} method took.
 */
public class MainActivity extends AppCompatActivity {

    private TextView mDurationText;

    private MeasurableImageView mMeasurableImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDurationText = (TextView) findViewById(R.id.draw_duration_text);
        mMeasurableImageView = (MeasurableImageView) findViewById(
                R.id.measurable_image);

        mMeasurableImageView.setViewRedrawnListener(this::updateDuration);

        RadioGroup group = (RadioGroup) findViewById(R.id.drawable_choice);
        group.setOnCheckedChangeListener((group1, checkedId) -> onImageTypeChanged(checkedId));
        group.check(R.id.vector_drawable_button);
    }

    private void updateDuration(final double miliseconds) {
        assert mDurationText != null;

        mDurationText.setText(getString(R.string.duration, miliseconds));
    }

    private void onImageTypeChanged(@IdRes final int checkedId) {
        switch (checkedId) {
            case R.id.vector_drawable_button:
                vectorDrawableSelected();
                break;
            case R.id.png_button:
                pngSelected();
                break;
        }
    }

    private void vectorDrawableSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder);
    }

    private void pngSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder_png);
    }
}
