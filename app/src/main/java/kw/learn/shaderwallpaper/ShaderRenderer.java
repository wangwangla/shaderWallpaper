package kw.learn.shaderwallpaper;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Auther jian xian si qi
 * @Date 2024/1/2 17:15
 */
public class ShaderRenderer implements GLSurfaceView.Renderer {
    public ShaderRenderer(Context context) {


    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClearColor(0.0f,0.3f,0.3f,0.3f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
