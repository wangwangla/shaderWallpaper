package kw.learn.shaderwallpaper;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * @Auther jian xian si qi
 * @Date 2024/1/2 19:00
 */
public class ShaderWallpaperService extends WallpaperService {
    private static ShaderWallpaperEngine engine;



    public static boolean isRunning() {
        return engine != null;
    }

    public static void setRenderMode(int renderMode) {
        if (engine != null) {
            engine.setRenderMode(renderMode);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        engine = null;
    }

    @Override
    public Engine onCreateEngine() {
        engine = new ShaderWallpaperEngine();
        return engine;
    }

    private class ShaderWallpaperEngine
            extends Engine {
        private final Handler handler = new Handler();

        private ShaderWallpaperView view;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            view = new ShaderWallpaperView();
            setShader();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            view.destroy();
            view = null;
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible) {
                view.onResume();
            } else {
                view.onPause();
            }
        }

        @Override
        public void onTouchEvent(MotionEvent e) {
            super.onTouchEvent(e);
//			view.getRenderer().touchAt(e);
        }

        @Override
        public void onOffsetsChanged(
                float xOffset,
                float yOffset,
                float xStep,
                float yStep,
                int xPixels,
                int yPixels) {
            view.getRenderer().setOffset(xOffset, yOffset);
        }

        private ShaderWallpaperEngine() {
            super();
            setTouchEventsEnabled(true);
        }

        private void setRenderMode(int renderMode) {
            if (view == null) {
                return;
            }

            view.setRenderMode(renderMode);
        }

        private void setShader() {
            handler.postDelayed(this::setShader, 100);
            boolean randomShader = false;
        }

        private class ShaderWallpaperView extends ShaderView {
            public ShaderWallpaperView() {
                super(ShaderWallpaperService.this);
            }

            @Override
            public final SurfaceHolder getHolder() {
                return ShaderWallpaperEngine.this.getSurfaceHolder();
            }

            public void destroy() {
                super.onDetachedFromWindow();
            }
        }
    }

    private void enableComponent(ComponentName name, boolean enable) {
        getPackageManager().setComponentEnabledSetting(name,
                enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
