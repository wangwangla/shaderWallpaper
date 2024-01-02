package kw.learn.shaderwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new ShaderView(this));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConstraintLayout mainLayout = findViewById(R.id.main_layout);
        mainLayout.setPadding(0,(int)getStatusBarHight(),0,0);
    }

    public float getStatusBarHight(){
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    public void setWallpaper(View view) {
//        Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
//        intent.setClassName(
//                "com.android.wallpaper.livepicker",
//                "com.android.wallpaper.livepicker.LiveWallpaperActivity");
//        startActivity(intent);

        Intent wallpaperIntent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        ComponentName wallpaperComponent = new ComponentName(this, ShaderWallpaperService.class);
        wallpaperIntent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, wallpaperComponent);
        startActivity(wallpaperIntent);
    }
}