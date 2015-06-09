package com.flyou.henucenter;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�HenuCenterApplication
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-21 ����10:45:58
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class HenuCenterApplication extends Application {
  public static final String TAG = "HenuCenterApplication";

  @Override
  public void onCreate() {
    super.onCreate();
    // ������֤��ʼ��
    SMSSDK.initSDK(this, "76cc1e760bc4", "a31e8ddd2941412b924fb38ba84c740c");
    //�������ͳ�ʼ��
    System.out.println("��ʼ���˼������͵�sdk!!");
    JPushInterface.setDebugMode(true); // ���õ���ģʽ
    JPushInterface.init(this); // ��ʼ��sdk
    

  }
  

}
