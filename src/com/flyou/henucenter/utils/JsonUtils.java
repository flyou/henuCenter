package com.flyou.henucenter.utils;

import java.net.URLEncoder;

import com.flyou.henucenter.domain.Data;
import com.flyou.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * ============================================================ 项目名称：Finger
 * 
 * 类名称：Json2String
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-8 上午12:14:23
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class JsonUtils {
  public static final String TAG = "Json2String";
  public static final String BASEURL="http://www.tuling123.com/openapi/api?key=";
  public static final String APIKEY="91ef9ce0baa4f93d6a13a6a5dc266a88";
  public static final String SPERATOR="&info=";
  private static String jsonString=null;
  public static Data msg2Domain(String msg) {
    
    try {
      jsonString = HttpUtils.doGet(BASEURL+APIKEY+SPERATOR+URLEncoder.encode(msg, "utf-8"));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    
    Gson gson=new Gson();
    Data data = gson.fromJson(jsonString, Data.class);
    return data;
  }
}
