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
    //private ShortBuffer indexBufferStatic;
    private FloatBuffer vertexBuffer;
    //private FloatBuffer vertexBufferStatic;
    private FloatBuffer colorBuffer;
    private short[] indicesArray = { 0, 1, 2 };
    private int numberOfVertices = 0;//3;

    private float angle;
    private float xAngle;
    private float yAngle;

    private void initTriangle() {
        float[] coords = {
                //coodinates
                -0.5f, -0.5f, 0.5f,//0
                0.5f, -0.5f, 0.5f,//1
                0f, -0.5f, -0.5f,//2
                0f, 0.5f, 0f//3
                //-0.5f, -0.5f, 0f,//(x1,y1,z1)
                //0.5f, -0.5f, 0f,//(x2,y2,z2)
                //-0.5f, 0.5f, 0f//(x3,y3,z3)
        };

        numberOfVertices = coords.length;

        float[] colors = {
                //colors
                1f,0f,0f,1f,//point0  red
                0f,1f,0f,1f,//point1  green
                0f,0f,1f,1f,//point2  blue
                1f,1f,1f,1f //point3  white
        };

        short[] indices = new short[]{
                //indices
                0,1,3,//rwg
                0,2,1,//rbg
                0,3,2,//rwb
                1,2,3 //gbw
        };

        //ByteBuffer vbb = ByteBuffer.allocateDirect(numberOfVertices * 3 * 4);
        ByteBuffer vbb = ByteBuffer.allocateDirect(coords.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();

        //ByteBuffer ibb = ByteBuffer.allocateDirect(numberOfVertices * 2);
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();

        //ByteBuffer cbb = ByteBuffer.allocateDirect(4 * numberOfVertices * 4);
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();



        vertexBuffer.put(coords);
        //indexBuffer.put(indicesArray);
        indexBuffer.put(indices);
        colorBuffer.put(colors);

        vertexBuffer.position(0);
        indexBuffer.position(0);
        colorBuffer.position(0);
    }

    /*private void initStaticTriangle() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(numberOfVertices * 3 * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBufferStatic = vbb.asFloatBuffer();

        ByteBuffer ibb = ByteBuffer.allocateDirect(numberOfVertices * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBufferStatic = ibb.asShortBuffer();

        float[] coords = { -0.8f, -0.8f, 0f,
                0.8f, -0.8f, 0f,
                0f, 0.8f, 0f
        };

        vertexBufferStatic.put(coords);
        indexBufferStatic.put(indicesArray);

        vertexBufferStatic.position(0);
        indexBufferStatic.position(0);
    }*/

    public void setColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public void setxAngle(float angle){
        xAngle = angle;
    }

    public void setyAngle(float angle){
        yAngle = angle;
    }

    public float getxAngle(){
        return xAngle;
    }

    public float getyAngle(){
        return yAngle;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //preparation
        //enable the differentiation of which side may be visable
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glFrontFace(GL10.GL_CCW);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        initTriangle();
        //initStaticTriangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //gl.glClearColor(red, green, blue, 1.0f);
        gl.glClearColor(0f, 0f, 0f, 1.0f);
        gl.glLoadIdentity();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gl.glRotatef(xAngle,1f,0f,0f);
        gl.glRotatef(yAngle,0f,1f,0f);
        //gl.glRotatef(angle, 0f, 1f, 0f);
        //gl.glTranslatef(-1.5f,0.0f,6.0f);
        //gl.glColor4f(0f, 0.5f, 0f, 0.5f);
        //gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBufferStatic);
        //gl.glDrawElements(GL10.GL_TRIANGLES, numberOfVertices, GL10.GL_UNSIGNED_SHORT, indexBufferStatic);

        //gl.glRotatef(angle, 0f, 1f, 0f);
        //gl.glTranslatef(-1.5f,0.0f,6.0f);
        //gl.glColor4f(0.5f, 0f, 0f, 0.5f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4,GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, numberOfVertices, GL10.GL_UNSIGNED_SHORT, indexBuffer);
    }
}

