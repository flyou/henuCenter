package com.flyou.henucenter;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：HenuCenterApplication
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-5-21 下午10:45:58
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class HenuCenterApplication extends Application {
  public static final String TAG = "HenuCenterApplication";

  @Override
  public void onCreate() {
    super.onCreate();
    // 短信验证初始化
    SMSSDK.initSDK(this, "76cc1e760bc4", "a31e8ddd2941412b924fb38ba84c740c");
    //极光推送初始化
    System.out.println("初始化了极光推送的sdk!!");
    JPushInterface.setDebugMode(true); // 设置调试模式
    JPushInterface.init(this); // 初始化sdk
    

  }
  

}
