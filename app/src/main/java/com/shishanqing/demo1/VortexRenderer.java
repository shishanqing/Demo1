package com.shishanqing.demo1;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by shishanqing on 16-7-11.
 */
public class VortexRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = VortexRenderer.class.getSimpleName();

    private float red = 0f;
    private float green = 0f;
    private float blue = 0f;

    private ShortBuffer indexBuffer;
    private FloatBuffer vertexBuffer;
    private short[] indicesArray = { 0, 1, 2 };
    private int numberOfVertices = 3;

    private float angle;

    private void initTriangle() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(numberOfVertices * 3 * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();

        ByteBuffer ibb = ByteBuffer.allocateDirect(numberOfVertices * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();

        float[] coords = { -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0f, 0.5f, 0f };

        vertexBuffer.put(coords);
        indexBuffer.put(indicesArray);

        vertexBuffer.position(0);
        indexBuffer.position(0);
    }

    public void setColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        initTriangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(red, green, blue, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glRotatef(angle, 0f, 1f, 0f);
        gl.glColor4f(0.5f, 0f, 0f, 0.5f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, numberOfVertices, GL10.GL_UNSIGNED_SHORT, indexBuffer);
    }
}

