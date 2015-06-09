package com.flyou.henucenter.utils;

import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：UpdateUtil
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-6-8 上午10:40:10
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
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
