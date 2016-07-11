package com.shishanqing.demo1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by shishanqing on 16-7-11.
 */
public class VortexView extends GLSurfaceView {
    private static final String LOG_TAG = VortexView.class.getSimpleName();
    private VortexRenderer renderer;

    public VortexView(Context context) {
        super(context);
        renderer = new VortexRenderer();
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                renderer.setColor(event.getX() / getWidth(), event.getY() / getHeight(), 1.0f);
                renderer.setAngle(event.getX() / 10);
            }
        });
        return super.onTouchEvent(event);
    }
}
