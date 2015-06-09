package com.flyou.henucenter.utils;

import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�UpdateUtil
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-6-8 ����10:40:10
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class UpdateUtil {
  public static final String TAG = "UpdateUtil";

  public static String upDateInfo() {
    HttpUtils httpUtils = new HttpUtils();
    try {
      ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, "");
      String result = responseStream.readString();
      return result;

    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }

}
