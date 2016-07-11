package com.shishanqing.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class OpenGL extends Activity {
    private static final String TAG = OpenGL.class.getSimpleName();
    private VortexView vortexView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vortexView = new VortexView(this);
        setContentView(vortexView);
        Log.d(TAG,"---------OpenGL oncreate-----------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        vortexView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vortexView.onResume();
    }
}
