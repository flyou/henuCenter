package com.flyou.henucenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.SPUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPagerAriPlanceActivity extends Activity {
  private static final String TAG = "AddPagerAriPlanceActivity";
  @ViewInject(R.id.pager_ari_plance_content)
  private EditText content;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_pager_ari_plance);
    ViewUtils.inject(this);

  }

  public void fly(View view) {
    String phone = null;
    String ariPlanceContent = null;
    try {
      phone = URLEncoder.encode((String) SPUtils.get(AddPagerAriPlanceActivity.this, "UserName", ""), "utf-8");
      ariPlanceContent = URLEncoder.encode(content.getText().toString().trim(), "utf-8").trim();
   
    } catch (UnsupportedEncodingException e) {
     e.printStackTrace();
    }
    

    if (!TextUtils.isEmpty(ariPlanceContent)) {
      HttpUtils httpUtils = new HttpUtils();
      httpUtils.configResponseTextCharset("UTF-8");
      httpUtils.configHttpCacheSize(0);
      httpUtils.send(HttpMethod.GET, Constants.HENU_ADD_PAGER_ARIPLANCE+"?phone="+phone+"&content="+ariPlanceContent, new RequestCallBack<String>() {

        @Override
        public void onFailure(HttpException error, String msg) {
        
        Toast.makeText(AddPagerAriPlanceActivity.this, "起航失败，"+msg, 0).show();
        }

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
          if ("1".equals(responseInfo.result.trim())) {
            Toast.makeText(AddPagerAriPlanceActivity.this, "起航成功", 0).show();
            
            finish();
          }
        }
      });

    }

    else {
      Toast.makeText(AddPagerAriPlanceActivity.this, "飞机上什么也没有，我才不会让你起飞呢", 1).show();
      content.setText("");
    }
  }
  public void cancle(View view){
    
    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
    
  }
}
