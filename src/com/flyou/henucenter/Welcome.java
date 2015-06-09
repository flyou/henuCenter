package com.flyou.henucenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;

import cn.jpush.android.api.JPushInterface;

import com.flyou.utils.NetUtils;
import com.flyou.utils.SPUtils;

public class Welcome extends Activity {
  // 第一屏停留时间（毫秒）
  private static long SPLASH_DISPLAY_LENGHT = 2000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 设置满屏，不含系统状态栏
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // 设置满屏，不含软件标题栏
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);

    if (NetUtils.isConnected(Welcome.this)) {
      long startTime = SystemClock.elapsedRealtime();
      // 进行一些耗时操作 初始化数据 如数据库拷贝 联网升级 等
      // runOnUiThread(new Runnable() {
      //
      // @Override
      // public void run() {
      // try {
      // List<Bitmap> urlImages = UrlImageUtil.getURLImages();
      //
      // } catch (Exception e) {
      // e.printStackTrace();
      // }
      // }
      // });


      long endTime = SystemClock.elapsedRealtime();
      if (endTime - startTime > 2000) {
        SPLASH_DISPLAY_LENGHT = 0;
      } else {
        SPLASH_DISPLAY_LENGHT = 2000 - (endTime - startTime);
      }
      new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {
          if ((Boolean) SPUtils.get(Welcome.this, "isFirst", true)) {
            SPUtils.put(Welcome.this, "isFirst", false);
            Intent intent = new Intent();
            intent.setClass(Welcome.this, WelcomeViewPaper.class);
            Welcome.this.startActivity(intent);
            Welcome.this.finish();

          } else {
            Intent intent = new Intent();
            intent.setClass(Welcome.this, HomeActivity.class);
            Welcome.this.startActivity(intent);
            Welcome.this.finish();

          }
        }
      }, SPLASH_DISPLAY_LENGHT);

    } else {
      NetUtils.openSetting(Welcome.this);
     
    }

  }
  @Override
  protected void onResume() {
      super.onResume();
      JPushInterface.onResume(this);
  }
  @Override
  protected void onPause() {
      super.onPause();
      JPushInterface.onPause(this);
  }
}
