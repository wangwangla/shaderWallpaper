package kw.learn.shaderwallpaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/**
 * @Auther jian xian si qi
 * @Date 2024/1/2 17:14
 */

public class ShaderView extends GLSurfaceView {
    private ShaderRenderer renderer;

    public ShaderView(Context context) {
        super(context);
        init(context, GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Click handling is implemented in renderer.
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    private void init(Context context, int renderMode) {
        renderer = new ShaderRenderer(context);

        // On some devices it's important to setEGLContextClientVersion()
        // even if the docs say it's not used when setEGLContextFactory()
        // is called. Not doing so will crash the app (e.g. on the FP1).
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setRenderMode(renderMode);
    }

    public ShaderRenderer getRenderer() {
        return renderer;
    }
}
