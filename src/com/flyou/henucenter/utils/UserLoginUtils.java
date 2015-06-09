package com.flyou.henucenter.utils;

import com.flyou.utils.Md5Parser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�UserLoginUtils
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-28 ����12:02:43
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class UserLoginUtils {
  public static final String TAG = "UserLoginUtils";
  public int flag=400;

  public int Login(String userName, String password) {
    String md5Password = Md5Parser.md5Parser(password);
    HttpUtils httpUtils = new HttpUtils();
    try {
      ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET , Constants.HENU_BASE_URL + "Login?userName=" + userName + "&password=" + md5Password);
      String readString = responseStream.readString();
      flag = Integer.parseInt(readString.trim().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
//    httpUtils.send(HttpMethod.GET, Constants.HENU_BASE_URL + "Login?userName=" + userName + "&password=" + md5Password,
//        new RequestCallBack<String>() {
//
//          @Override
//          public void onFailure(HttpException arg0, String arg1) {
//            flag = 3;
//          }
//
//          @Override
//          public void onSuccess(ResponseInfo<String> responseInfo) {
//            flag = Integer.parseInt(responseInfo.result.trim().toString());
//            System.out.println("UserLoginUtils" + flag);
//          }
//          
//          
//         
//        } );
//  
    return flag;
  }
}
