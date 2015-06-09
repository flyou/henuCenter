package com.flyou.henucenter.receiver;

import org.json.JSONException;
import org.json.JSONObject;

import com.flyou.henucenter.DaitalNewsWebViewActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.R.string;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.s;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�MyPushReceiver
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-21 ����10:54:11
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class MyPushReceiver extends BroadcastReceiver {
  public static final String TAG = "MyPushReceiver";
  private String url="http://www.baidu.com";

  @Override
  public void onReceive(Context context, Intent intent) {
    Bundle bundle = intent.getExtras();
    // ��ȡ���������͵ĸ�����Ϣ
    String title=bundle.getString(JPushInterface.EXTRA_ALERT);
    String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
   
      if (!TextUtils.isEmpty(extras)) {
        JSONObject jsonObject;
        try {
          jsonObject = new JSONObject(extras);
          url=jsonObject.getString("url");
        } catch (JSONException e) {
          e.printStackTrace();
        }
        
       
     
      }
      
    if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
      

      // ���Զ����Activity
      Intent i = new Intent(context, DaitalNewsWebViewActivity.class);
      i.putExtra("newsTitle", title);
      
      i.putExtra("newsUrl", url);
      i.putExtra("Departments_type", 300);
//      FLAG_ACTIVITY_CLEAR_TOP
      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP );
      context.startActivity(i);

    }
  }
}
