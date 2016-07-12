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
    private float x = 0;
    private float y = 0;


    public VortexView(Context context) {
        super(context);
        renderer = new VortexRenderer();
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x = event.getX();
            y = event.getY();
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            final float xdiff = x - event.getX();
            final float ydiff = y - event.getY();

            queueEvent(new Runnable() {
                @Override
                public void run() {
                    //renderer.setColor(event.getX() / getWidth(), event.getY() / getHeight(), 1.0f);
                    //renderer.setAngle(event.getX());
                    renderer.setxAngle(renderer.getxAngle() + ydiff);
                    renderer.setyAngle(renderer.getyAngle() + xdiff);
                }
            });
            x = event.getX();
            y = event.getY();
        }
        //return super.onTouchEvent(event);
        return true;
    }
}
