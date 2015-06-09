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
 * �����ƣ�UserRegist
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-28 ����1:03:19
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class UserRegist {
  public static final String TAG = "UserRegist";
  private    String FLAG=null;
  public  String regist(String userName, String password, String email, String phone) {
    
    String md5password = Md5Parser.md5Parser(password);
    HttpUtils httpUtils=new HttpUtils();
    
 try {
  ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, Constants.HENU_BASE_URL+"Regist?userName="+userName+"&password="+md5password+"&email="+email+"&phone="+phone);
  FLAG=responseStream.readString();
} catch (Exception e) {
 e.printStackTrace();
}
 /**
    httpUtils.send(HttpMethod.GET, Constants.HENU_BASE_URL+"Regist?userName="+userName+"&password="+md5password+"&email="+email+"&phone="+phone, new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException arg0, String arg1) {
      FLAG="1";
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
        
        FLAG = responseInfo.result;
       
      }
    });
    
    */
 
    return FLAG;
  }
}
