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
  // ��һ��ͣ��ʱ�䣨���룩
  private static long SPLASH_DISPLAY_LENGHT = 2000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // ��������������ϵͳ״̬��
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // �����������������������
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);

    if (NetUtils.isConnected(Welcome.this)) {
      long startTime = SystemClock.elapsedRealtime();
      // ����һЩ��ʱ���� ��ʼ������ �����ݿ⿽�� �������� ��
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
