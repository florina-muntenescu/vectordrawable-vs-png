# VectorDrawable vs PNG

This is a test project to check the duration of an `ImageView#onDraw` method when what we need to draw is a VectorDrawable or a PNG image. The app allows selecting using either a ViewDrawable or a PNG that will be displayed on the entire size of the screen and shows the draw duration on screen. The PNG and the VectorDrawable used are versions of the same SVG file.

The `ImageView` class is extended in the `MeasurableImageView` class and the `onDraw` method is overriden. The `System.nanoTime` is used to get the time before and after the `onDraw` of the base `ImageView` class.

{% highlight java %}
@Override
protected void onDraw(final Canvas canvas) {
    long startTime = System.nanoTime();

    super.onDraw(canvas);

    long endTime = System.nanoTime();
    notifyDraw(startTime, endTime);
}
{% endhighlight %}

The test app proves the following:
* The 1st rendering of a VectorDrawable takes a long time. The next renderings take considerably less time.
* The PNG is rendered in a similar amount of time at every rendering.
* When changing the orientation, the drawing of the VectorDrawable takes longer, for the 1st rendering.
